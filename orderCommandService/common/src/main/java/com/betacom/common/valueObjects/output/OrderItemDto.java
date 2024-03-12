package com.betacom.common.valueObjects.output;

import com.betacom.common.valueObjects.input.Money;
import lombok.Builder;

@Builder
public class OrderItemDto {
    private final String orderItemRefNumber;
    private final String OrderTrackingId;
    private final Money itemAmount;

}
