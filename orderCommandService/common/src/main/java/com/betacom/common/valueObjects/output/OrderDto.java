package com.betacom.common.valueObjects.output;

import com.betacom.common.valueObjects.input.Money;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class OrderDto {
    private final String orderTrackingId;
    private final Money orderAmount;
    private final List<OrderItemDto> orderItemDtoList;
    private final Integer orderStatus;

}
