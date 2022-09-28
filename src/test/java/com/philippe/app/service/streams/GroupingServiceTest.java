package com.philippe.app.service.streams;

import com.philippe.app.config.ProductsConfiguration;
import com.philippe.app.domain.ApigeeUsage;
import com.philippe.app.domain.PaidItem;
import com.philippe.app.domain.Usage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GroupingServiceTest {
    private static final UUID ORG_ID_1 = UUID.fromString("10df9793-4c21-4ff0-bd99-923992c9f1ff");
    private static final UUID ORG_ID_2 = UUID.fromString("56a453f2-941d-47be-86b4-13875772140c");

    @Mock
    private ProductsConfiguration productsConfiguration;

    @InjectMocks
    private GroupingService groupingService;

    @Test
    public void toUsageList_nullInput_expectEmptyList() {
        assertEquals(emptyList(), groupingService.toUsageList(null));
    }

    @Test
    public void toUsageList_emptyListInput_expectEmptyList() {
        assertEquals(emptyList(), groupingService.toUsageList(new ArrayList<>()));
    }

    @Test
    public void toUsageList_inputListContainingFreeAndChargeableDataForTwoOrganisations_expectListOfSizeTwo() {
        // GIVEN
        Map<String, Double> priceMap = new HashMap<>();
        priceMap.put("cap", Double.valueOf(3));
        priceMap.put("tshirt", Double.valueOf(18));
        priceMap.put("short_sport", Double.valueOf(3));
        priceMap.put("short_smart", Double.valueOf(0.05));
        when(productsConfiguration.getPriceMap()).thenReturn(priceMap);

        List<ApigeeUsage> inputList = new ArrayList<>();
        ApigeeUsage vtsApigeeUsage = new ApigeeUsage(ORG_ID_1, "cap",null, 2L, 3L, 20L, 30L);
        inputList.add(vtsApigeeUsage);

        ApigeeUsage wfsApigeeUsage = new ApigeeUsage(ORG_ID_1, "tshirt",null, 4L, 6L, 40L, 60L);
        inputList.add(wfsApigeeUsage);

        ApigeeUsage wmtsLeisureApigeeUsage = new ApigeeUsage(ORG_ID_1, "short","smart", 8L, 12L, 80L, 120L);
        inputList.add(wmtsLeisureApigeeUsage);

        ApigeeUsage wmtsRoadLeisureApigeeUsage = new ApigeeUsage(ORG_ID_1, "short","sport", 16L, 24L, 160L, 240L);
        inputList.add(wmtsRoadLeisureApigeeUsage);

        ApigeeUsage wfsApigeeUsage_org2 = new ApigeeUsage(ORG_ID_2, "tshirt",null, 5L, 7L, 50L, 70L);
        inputList.add(wfsApigeeUsage_org2);

        ApigeeUsage wmtsLeisureApigeeUsage_org2 = new ApigeeUsage(ORG_ID_2, "short","smart", 9L, 13L, 90L, 130L);
        inputList.add(wmtsLeisureApigeeUsage_org2);

        // WHEN
        List<Usage> resultList = groupingService.toUsageList(inputList);

        // THEN
        assertEquals(2, resultList.size());

        List<Usage> expectedList = new ArrayList<>();
        Usage org1Usage = new Usage(ORG_ID_1, 30L, new BigDecimal("1896.00"));
        Set<PaidItem> paidItems = new HashSet<>();
        paidItems.add(PaidItem.builder().description("cap").transactionCostInPence(new BigDecimal("3.0")).transactionCount(30L).totalCostInPence(new BigDecimal("90.0")).build());
        paidItems.add(PaidItem.builder().description("tshirt").transactionCostInPence(new BigDecimal("18.0")).transactionCount(60L).totalCostInPence(new BigDecimal("1080.0")).build());
        paidItems.add(PaidItem.builder().description("short_smart").transactionCostInPence(new BigDecimal("0.05")).transactionCount(120L).totalCostInPence(new BigDecimal("6.00")).build());
        paidItems.add(PaidItem.builder().description("short_sport").transactionCostInPence(new BigDecimal("3.0")).transactionCount(240L).totalCostInPence(new BigDecimal("720.0")).build());
        org1Usage.setPaidItems(paidItems);
        expectedList.add(org1Usage);

        Usage org2Usage = new Usage(ORG_ID_2, 14L, new BigDecimal("1266.50"));
        paidItems = new HashSet<>();
        paidItems.add(PaidItem.builder().description("tshirt").transactionCostInPence(new BigDecimal("18.0")).transactionCount(70L).totalCostInPence(new BigDecimal("1260.0")).build());
        paidItems.add(PaidItem.builder().description("short_smart").transactionCostInPence(new BigDecimal("0.05")).transactionCount(130L).totalCostInPence(new BigDecimal("6.50")).build());
        org2Usage.setPaidItems(paidItems);
        expectedList.add(org2Usage);

        assertTrue(resultList.containsAll(expectedList));
    }
}
