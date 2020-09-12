package com.philippe.app.service.streams;

import com.philippe.app.config.ProductsConfiguration;
import com.philippe.app.domain.ApigeeUsage;
import com.philippe.app.domain.PaidItem;
import com.philippe.app.domain.Usage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.stream.Collectors.groupingBy;

@AllArgsConstructor
@Service
public class GroupingService {
    private static final String DESC_PATTERN = "%s_%s";
    private static final BigDecimal ZERO = BigDecimal.valueOf(0);

    private final ProductsConfiguration productsConfiguration;

    /**
     * Here, we group all the ApigeeUsages belonging to the same orgId. We then aggregate them into a single Usage object
     * per orgId.
     */
    public List<Usage> toUsageList(List<ApigeeUsage> apigeeUsageList) {
        List<Usage> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(apigeeUsageList)) {
            result = apigeeUsageList
                    .stream()
                    .collect(groupingBy(ApigeeUsage::getOrganisationId))
                    .values().stream()
                    .map(this::aggregateList)
                    .collect(Collectors.toList());
        }

        return result;
    }

    /**
     * All ApigeeUsage objects in the input List (apigeeUsageList) belong to the same organisation. This is guaranteed
     * by the .collect(groupingBy(ApigeeUsage::getOrganisationId)) in toUsageList.
     *
     * Also, there is a guaranteed uniqueness of ApigeeUsage per serviceName+product.
     */
    private Usage aggregateList(List<ApigeeUsage> apigeeUsageList) {
        Usage usage = null;

        BigDecimal freeUsageTotal = ZERO;
        BigDecimal chargeableUsageTotal = ZERO;

        for (ApigeeUsage apigeeUsage : apigeeUsageList) {
            BigDecimal freeUsage = apigeeUsage.getFreeUsage();
            freeUsageTotal = freeUsageTotal.add(freeUsage);

            PaidItem paidItem = null;
            long chargeableTxCount = apigeeUsage.getChargeableTransactionCount();
            String description = getDescription(apigeeUsage.getServiceName(), apigeeUsage.getProduct());
            double txCostFromPriceList = getTxCostFromPriceList(description);
            if (chargeableTxCount > 0 && txCostFromPriceList > 0) {
                BigDecimal txCostInPence = BigDecimal.valueOf(txCostFromPriceList);
                BigDecimal totalCostInPence = txCostInPence.multiply(BigDecimal.valueOf(chargeableTxCount));

                paidItem = PaidItem.builder()
                        .description(description)
                        .transactionCount(chargeableTxCount)
                        .transactionCostInPence(txCostInPence)
                        .totalCostInPence(totalCostInPence)
                        .build();

                /**
                 * The chargeable total is recalculated as in DailyUsageImpl (getTotal & getTotalTxCount), some rounding occurs.
                 * Here, we ensure that the total worked out from paidItems is equal to the chargeable total.
                 */
                chargeableUsageTotal = chargeableUsageTotal.add(totalCostInPence);
            }

            if (usage == null) {
                usage = new Usage(apigeeUsage.getOrganisationId(), freeUsageTotal, chargeableUsageTotal);
                Set<PaidItem> paidItems = new HashSet<>();
                if (paidItem != null) {
                    paidItems.add(paidItem);
                }
                usage.setPaidItems(paidItems);
            } else {
                usage.setFreeUsageTotal(freeUsageTotal);
                if (paidItem != null) {
                    usage.setChargeableUsageTotal(chargeableUsageTotal);
                    usage.getPaidItems().add(paidItem);
                }
            }
        }

        return usage;
    }

    private String getDescription(String serviceName, String product) {
        if (serviceName == null) {
            return null;
        }

        String result = serviceName.toLowerCase();
        if (product != null) {
            result = format(DESC_PATTERN, result, product);
        }
        return result;
    }

    private double getTxCostFromPriceList(String description) {
        double result = 0;

        if (description != null) {
            Double priceFromMap = productsConfiguration.getPriceMap().get(description);
            if (priceFromMap != null) {
                result = priceFromMap.doubleValue();
            }
        }

        return result;
    }
}
