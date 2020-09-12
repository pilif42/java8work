package com.philippe.app.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class ApigeeUsage {
    private UUID organisationId;

    private String serviceName;
    private String product;

    private long freeUsage;
    private long freeTansactionCount;

    private long chargeableUsage;
    private long chargeableTransactionCount;
}
