package com.betacom.common.valueObjects;

import com.betacom.common.valueObjects.Money;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class OrderItemDto {
    private final Integer orderId;
    private final String orderItemRefNumber;
    private final String OrderTrackingId;
    private final Money amountPerQuantity;
    private final Money itemGrossAmount;
    private final Money itemNetAmount;
    private final Money itemDiscountAmount;
    private final String productCode;
}
