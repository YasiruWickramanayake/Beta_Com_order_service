package com.betacom.common.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    ORDER_INITIATE(201, "Order initiated"),
    ORDER_READY_TO_PAY(202, "Order payment can be initiated"),
    ORDER_PAYMENT_SUCCESS(203, "Order payment success"),
    ORDER_CANCEL(204, "Order cancel"),
    ORDER_APPROVED(205, "Order approved"),
    ORDER_COMPLETED(206, "Order completed"),
    ORDER_PAYMENT_INITIATE_SUCCESS(207, "Order payment initiated"),
    ORDER_PAYMENT_FAILED(301, "Order payment failed"),
    ORDER_FAILED(302, "Order failed"),
    ORDER_PAYMENT_INITIATE_FAILED(203, "Order payment initiation failed");

    private int statusCode;
    private String statusMessage;

}
