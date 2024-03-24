package com.betacom.publisher;

import com.betacom.applicationCommon.PaymentServicePublisher;
import com.betacom.common.valueObjects.OrderDto;
import com.betacom.common.valueObjects.OrderPaymentPublishResponse;
import org.springframework.stereotype.Component;

@Component
public class OrderPaymentEventPublisher implements PaymentServicePublisher {
    @Override
    public OrderPaymentPublishResponse publishOrderPayment(OrderDto orderDto) {
        return null;
    }
}
