package com.philippe.app.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ApigeeUsage {
    private UUID organisationId;

    private String serviceName;
    private String product;

    private BigDecimal freeUsage;
    private long chargeableTransactionCount;
}
