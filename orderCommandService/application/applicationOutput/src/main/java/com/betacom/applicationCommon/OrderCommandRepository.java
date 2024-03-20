package com.betacom.applicationCommon;

import com.betacom.common.valueObjects.OrderDto;

public interface OrderCommandRepository {
    public OrderDto initiateOrderRequestSave(OrderDto orderDto);
    public OrderDto getOrderByOrderId(Integer orderId);
}
