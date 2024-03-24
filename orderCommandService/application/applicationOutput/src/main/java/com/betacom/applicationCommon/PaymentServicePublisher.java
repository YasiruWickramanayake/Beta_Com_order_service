package com.betacom.applicationCommon;

import com.betacom.common.valueObjects.OrderDto;
import com.betacom.common.valueObjects.OrderPaymentPublishResponse;

public interface PaymentServicePublisher {

    public OrderPaymentPublishResponse publishOrderPayment(OrderDto orderDto);
}
