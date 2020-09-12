package com.philippe.app.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@EqualsAndHashCode
@Builder
@Getter
@Setter
public class PaidItem {
    private String description;
    private long transactionCount;
    private BigDecimal transactionCostInPence;
    private BigDecimal totalCostInPence;
}
