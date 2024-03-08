package com.betacom.coreDomain.entity;

public class OrderItem {
    private final String orderItemCode;
    private final String orderTrackingId;

    public OrderItem(String orderItemCode, String orderTrackingId) {
        this.orderItemCode = orderItemCode;
        this.orderTrackingId = orderTrackingId;
    }



    public String getOrderItemCode() {
        return orderItemCode;
    }

    public String getOrderTrackingId() {
        return orderTrackingId;
    }
}
