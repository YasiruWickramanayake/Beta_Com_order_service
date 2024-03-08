package com.betacom.domain.entity;

import java.util.UUID;

public class OrderItem {
    private final String orderItemNumber;
    private final String orderTrackingNumber;

    public OrderItem(String orderTrackingNumber) {
        this.orderItemNumber = UUID.randomUUID().toString();
        this.orderTrackingNumber = orderTrackingNumber;
    }
}
