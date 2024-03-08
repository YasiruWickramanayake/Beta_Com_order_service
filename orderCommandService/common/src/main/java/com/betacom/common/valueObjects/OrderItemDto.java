package com.betacom.common.valueObjects;

import lombok.Builder;

@Builder
public class OrderItemDto {
    private final String orderItemRefNumber;
    private final String OrderTrackingId;
    private final Money itemAmount;

}
