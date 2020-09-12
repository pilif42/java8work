package com.philippe.app.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode
@Getter
@Setter
public class Usage {
    private UUID organisationId;

    private long freeUsageTotal;
    private BigDecimal chargeableUsageTotal;

    private Set<PaidItem> paidItems;

    public Usage(UUID organisationId, long freeUsageTotal, BigDecimal chargeableUsageTotal) {
        this.organisationId = organisationId;
        this.freeUsageTotal = freeUsageTotal;
        this.chargeableUsageTotal = chargeableUsageTotal;
    }
}
