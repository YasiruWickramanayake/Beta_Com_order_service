package com.betacom.common.valueObjects;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class OrderDto {
    private final Integer orderId;
    private final String orderTrackingId;
    private final Money orderNetAmount;
    private final Money orderGrossAmount;
    private final Money orderDiscountAmount;
    private final List<OrderItemDto> orderItemDtoList;
    private final Integer orderStatus;
    private final String message;
    private final String originalOrderTrackingId;
    private final DeliveryAddress deliveryAddress;
    private final String customerNumber;

}
