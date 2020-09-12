/**
 * This service is kept for the interesting stream work done in getDailyUsage.
 *
 *
package com.philippe.app.service.streams;

import com.philippe.app.domain.ApigeeUsage;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class DailyUsageService {
    private static final String APIGEE_STATS_URL = "https://api.enterprise.apigee.com/v1/organizations/{apigeeOrg}/environments/{apigeeEnv}/stats/developer_email,servicename,product,priceunit,chargeable,transactionunit";
    private static final String NULL = "null";
    private static final String NOT_SET = "(not set)";

    private static final int DEVELOPER_EMAIL_DIMENSION_IDX = 0;
    private static final int SERVICE_NAME_DIMENSION_INDEX = 1;
    private static final int PRODUCT_DIMENSION_INDEX = 2;
    private static final int PRICE_UNIT_DIMENSION_INDEX = 3;
    private static final int CHARGEABLE_FLAG_DIMENSION_INDEX = 4;
    private static final int TX_UNIT_DIMENSION_IDX = 5;

private final TrackingClient trackingClient;

private final TokenManager tokenManager;
private final Configuration config;
private final WebClient client;
private final DeveloperMap developerMap;

public DailyUsageImpl(TokenManager tokenManager,
Configuration config,
WebClientHelper helper,
DeveloperMap developerMap,
 @Nullable TrackingClient trackingClient) {
 this.tokenManager = tokenManager;
 this.config = config;
 this.developerMap = developerMap;
 this.trackingClient = trackingClient;

 Map<String, String> params = new HashMap<>();
 params.put("apigeeOrg", config.getApigeeOrganization());
 params.put("apigeeEnv", config.getApigeeEnvironment());

 client = helper.builder()
 .baseUrl(APIGEE_STATS_URL)
 .defaultUriVariables(params)
 .build();
 }

    public Mono<List<ApigeeUsage>> getDailyUsage(int day, int month, int year) {

        StringBuilder timeRangeBuilder = new StringBuilder();
        writeDate(timeRangeBuilder, year, month, day, 0, 0);
        timeRangeBuilder.append('~');
        writeDate(timeRangeBuilder, year, month, day, 23, 59);
        String timeRange = timeRangeBuilder.toString();
        log.info("Getting Apigee stats for time range: " + timeRange);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("select", Collections.singletonList("sum(price),sum(transactioncost)"));
        params.put("timeRange", Collections.singletonList(timeRange));
        params.put("filter", Collections.singletonList("(apiproxy in 'SAMPLE' and category in 'premium')"));

        String token;
        try {
            token = tokenManager.getToken();
        } catch(Exception e) {
            log.error("Caught exception getting a token", e);
            return Mono.error(e);
        }

        Instant start = Instant.now();
        return client.get()
                .uri(uriBuilder -> uriBuilder.queryParams(params).build())
                .header("Authorization", "Bearer " + token)
                .exchange()
                .doOnSuccess(response -> {
                    log.info("Got Apigee stats");
                    track(c -> c.trackDependency("GET", APIGEE_STATS_URL, start,
                            response.rawStatusCode(), response.statusCode().is2xxSuccessful()));
                })
                .doOnError(error -> {
                    log.error("Caught error querying apigee stats", error);
                    track(c -> c.trackDependency("GET", APIGEE_STATS_URL, start,
                            500, false));
                })
                .flatMap(response -> response.bodyToMono(StatsResponse.class))

                .map(stats -> stats.getEnvironments()
                        // Stream each of the environments
                        .stream()
                        // Then for each of those get the list of the dimensions
                        .map(StatsResponse.Environment::getDimensions)
                        // Flatten it so we have a stream of dimensions, not a stream of Lists
                        .flatMap(List::stream)
                        // Convert that to a list of usage instances (there will be duplicates at this stage)
                        .map(this::parseDimension)
                        .filter(usage -> usage != null && usage.getOrganisationId() != null)
                        // Group the stream by groupingKey
                        .collect(groupingBy(ApigeeUsage::getGroupingKey))
                        // Grab each of the usage lists and reduce each list to a single usage
                        .values().stream()
                        .map(list -> list.stream().reduce(null, (total, usage) -> {
                            if (total == null) {
                                return usage;
                            }
                            total.setFreeUsage(total.getFreeUsage() + usage.getFreeUsage());
                            total.setFreeTransactionCount(total.getFreeTransactionCount() + usage.getFreeTransactionCount());
                            total.setChargeableUsage(total.getChargeableUsage() + usage.getChargeableUsage());
                            total.setChargeableTransactionCount(total.getChargeableTransactionCount() + usage.getChargeableTransactionCount());
                            return total;
                        }))
                )

                // Find the mapping from developer email to organisation id, and apply the mapping to each member of the stream
                // Once we have done that, filter off any developers that we could not identify and collect the results.
                .flatMap(usageStream -> {
                    return developerMap.getDeveloperEmailToOrganisationIdMap()
                            .map(emailToOrganisationMap -> {
                                return usageStream
                                        .map(usage -> {
                                            String developerEmail = usage.getOrganisationId();
                                            usage.setOrganisationId(emailToOrganisationMap.get(developerEmail));
                                            return usage;
                                        })
                                        .filter(usage -> usage.getOrganisationId() != null)
                                        .collect(Collectors.toList());
                            });
                });
    }

    private ApigeeUsage parseDimension(StatsResponse.Dimension dim) {
        String name = dim.getName();
        String[] dimensionNames = splitDimensionName(name);
        if (dimensionNames.length > TX_UNIT_DIMENSION_IDX) {
            String developerEmail = dimensionNames[DEVELOPER_EMAIL_DIMENSION_IDX];

            String serviceName = dimensionNames[SERVICE_NAME_DIMENSION_INDEX];
            if (serviceName.equalsIgnoreCase(NULL) || serviceName.equalsIgnoreCase(NOT_SET)) {
                serviceName = null;
            }

            String product = dimensionNames[PRODUCT_DIMENSION_INDEX];
            if (product.equalsIgnoreCase(NULL) || product.equalsIgnoreCase(NOT_SET)) {
                product = null;
            }

            Double priceUnit = config.getUnitsPerPenny();
            try {
                priceUnit = Double.parseDouble(dimensionNames[PRICE_UNIT_DIMENSION_INDEX]);
            } catch (Exception e) {
                // We failed to parse the price unit, so use the default.
            }

            Boolean chargeable = false;
            try {
                chargeable = Boolean.parseBoolean(dimensionNames[CHARGEABLE_FLAG_DIMENSION_INDEX]);
            } catch (Exception e) {
                // We failed to parse the chargeable unit, so use the default.
            }

            Integer transactionUnit = config.getTransactionUnit();
            try {
                transactionUnit = Integer.parseInt(dimensionNames[TX_UNIT_DIMENSION_IDX]);
            } catch (Exception e) {
                // We failed to parse the price unit, so use the default.
            }

            List<StatsResponse.Metric> metrics = dim.getMetrics();
            if (!metrics.isEmpty()) {
                Long freeUsage = Long.valueOf(0);
                Long chargeableUsage = Long.valueOf(0);
                Long freeTransactionCount = Long.valueOf(0);
                Long chargeableTransactionCount = Long.valueOf(0);
                if (chargeable) {
                    chargeableUsage = getTotal(metrics.get(0), priceUnit);
                    chargeableTransactionCount = getTotalTxCount(metrics.get(1), transactionUnit);
                } else {
                    freeUsage = getTotal(metrics.get(0), priceUnit);
                    freeTransactionCount = getTotalTxCount(metrics.get(1), transactionUnit);
                }

                return new ApigeeUsage(developerEmail, serviceName, product, freeUsage, freeTransactionCount, chargeableUsage, chargeableTransactionCount);
            }
        }
        return null;
    }

    private Long getTotal(StatsResponse.Metric metric, Double priceUnit) {
        return (long) Math.floor(metric.getValues().get(0) / priceUnit);
    }

    private Long getTotalTxCount(StatsResponse.Metric metric, Integer transactionUnit) {
        return (long) Math.floor(metric.getValues().get(0) / transactionUnit);
    }

    private void writeDate(StringBuilder s, int year, int month, int day, int hour, int minute) {
        writeInt(s, month);
        s.append('/');
        writeInt(s, day);
        s.append('/');
        s.append(year);
        s.append(' ');
        writeInt(s, hour);
        s.append(':');
        writeInt(s, minute);
    }

    private void writeInt(StringBuilder s, int i) {
        if(i < 10) {
            s.append('0');
        }
        s.append(i);
    }

    private void track(Consumer<TrackingClient> consumer) {
        Optional.ofNullable(trackingClient).ifPresent(consumer);
    }

    private String[] splitDimensionName(String name) {
        return Stream.of(name.split(","))
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
    }

}
 */