package com.betacom.coreDomain.entity;

import java.util.List;
import java.util.UUID;

public class Order {
    private final Integer orderId;
    private final String trackingId;
    private Integer orderStatus;
    private final String originalOrderTrackingId;
    private String orderMessage;
    private final List<OrderItem> orderItemList;

    // This constructor can be used when create a new order
    public Order() {
        this.orderId = null;
        this.trackingId = UUID.randomUUID().toString();
        this.orderStatus = 0;
        this.originalOrderTrackingId = null;
        this.orderMessage = null;
        this.orderItemList = generateOrderItemList();
    }

    //This constructor can be used to when map the entity data to the Order aggrerate root


    protected Order(String trackingId,
                    Integer orderStatus,
                    String originalOrderTrackingId,
                    String orderMessage,
                    List<OrderItem> orderItemList) {
        this.orderId = null;
        this.trackingId = trackingId;
        this.orderStatus = orderStatus;
        this.originalOrderTrackingId = originalOrderTrackingId;
        this.orderMessage = orderMessage;
        this.orderItemList = orderItemList;
    }

    public Order addProducts(){
        isValidStateToAddProduct();
        Order amendedOrder = new Order(UUID.randomUUID().toString(),
                this.orderStatus, this.trackingId, this.orderMessage,
                generateOrderItemList());
        return amendedOrder;
    }

    public Order cancelOrder(){
        isValidStateToCancelOrder();
        this.orderStatus = null; //set cancel Status;
        return this;
    }

    private void isValidStateToAddProduct() {

    }

    private void isValidStateToCancelOrder(){

    }


    private List<OrderItem> generateOrderItemList(){
        return null;
    }

}
