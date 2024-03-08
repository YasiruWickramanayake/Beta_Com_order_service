package com.betacom.domain.entity;

import lombok.Data;

import java.util.List;
import java.util.UUID;


public class Order {
    private final String orderTrackingNumber;
    private Integer orderStatus;
    private final List<OrderItem> orderItems;
    private String orderMessage;
    private final String originalOrderTrackingNumber;
    public Order() {
            this.orderTrackingNumber = UUID.randomUUID().toString();
            this.orderStatus = null;
            this.orderItems = createOrderItems();
            this.orderMessage = null;
            this.originalOrderTrackingNumber = null;
            validateOrderPrice();
    }

    private Order(String orderTrackingNumber,
                  Integer orderStatus,
                  List<OrderItem> orderItems,
                  String orderMessage,
                  String originalOrderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
        this.orderStatus = orderStatus;
        this.orderItems = orderItems;
        this.orderMessage = orderMessage;
        this.originalOrderTrackingNumber = originalOrderTrackingNumber;
        validateOrderPrice();
    }

    protected Order amendOrder(){
        this.orderStatus = 4;
        Order newOrder = new Order(this.orderTrackingNumber,
                this.orderStatus,
                createOrderItems(),
                null,
                null);
        return newOrder;
    }


    Order getCurrentOrder(){
        return this;
    }

    private void generateOrder(){

    }

    private List<OrderItem> createOrderItems(){
        return null;
    }

    private void validateOrderPrice(){

    }

    public String getOrderTrackingNumber() {
        return orderTrackingNumber;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }


}
