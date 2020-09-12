package com.philippe.app.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class Usage {
    private UUID organisationId;

    private BigDecimal freeUsageTotal;
    private BigDecimal chargeableUsageTotal;

    private Set<PaidItem> paidItems;

    public Usage(UUID organisationId, BigDecimal freeUsageTotal, BigDecimal chargeableUsageTotal) {
        this.organisationId = organisationId;
        this.freeUsageTotal = freeUsageTotal;
        this.chargeableUsageTotal = chargeableUsageTotal;
    }
}
