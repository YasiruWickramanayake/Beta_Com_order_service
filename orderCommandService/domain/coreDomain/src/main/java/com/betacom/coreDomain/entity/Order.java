package com.betacom.coreDomain.entity;

import com.betacom.common.valueObjects.input.DeliveryAddress;
import com.betacom.common.valueObjects.input.Money;
import com.betacom.common.valueObjects.input.Product;
import com.betacom.common.valueObjects.output.OrderDto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Order {
    private final Integer orderId;
    private final String trackingId;
    private Integer orderStatus;
    private final String originalOrderTrackingId;
    private String orderMessage;
    private final List<OrderItem> orderItemList;
    private final OrderDeliveryAddress deliveryAddress;
    private final String customerNumber;
    private final Money orderGrossAmount;
    private final Money orderNetAmount;
    private final Money orderDiscount;

    // This constructor can be used when create a new order
    public Order(String customerNumber,
                 List<Product> products,
                 DeliveryAddress deliveryAddress,
                 Money orderGrossAmount,
                 Money orderNetAmount,
                 Money orderDiscount) {
        this.orderId = null;
        this.trackingId = UUID.randomUUID().toString();
        this.orderStatus = 0;
        this.originalOrderTrackingId = null;
        this.orderMessage = null;
        this.orderItemList = generateOrderItemList(products);
        this.deliveryAddress = generateDeliveryAddress(deliveryAddress);
        this.customerNumber = customerNumber;
        this.orderGrossAmount = orderGrossAmount;
        this.orderNetAmount = orderNetAmount;
        this.orderDiscount = orderDiscount;
        isValidOrder(this);
    }



    //This constructor can be used to when map the entity data to the Order aggrerate root
    protected Order(String trackingId,
                    Integer orderStatus,
                    String originalOrderTrackingId,
                    String orderMessage,
                    List<OrderItem> orderItemList,
                    OrderDeliveryAddress orderDeliveryAddress,
                    String customerNumber,
                    Money orderGrossAmount,
                    Money orderNetAmount,
                    Money orderDiscount) {
        this.orderId = null;
        this.trackingId = trackingId;
        this.orderStatus = orderStatus;
        this.originalOrderTrackingId = originalOrderTrackingId;
        this.orderMessage = orderMessage;
        this.orderItemList = orderItemList;
        this.deliveryAddress = orderDeliveryAddress;
        this.customerNumber = customerNumber;
        this.orderGrossAmount = orderGrossAmount;
        this.orderNetAmount = orderNetAmount;
        this.orderDiscount = orderDiscount;
    }

    public Order addProducts(Money orderGrossAmount,
                             Money orderNetAmount,
                             Money discount,
                             List<Product> newProducts){
        isValidStateToAddProduct();
        List<OrderItem> orderItems = generateAmendedItemList(newProducts, this.orderItemList);
        Order amendedOrder = new Order(UUID.randomUUID().toString(),
                this.orderStatus,
                this.trackingId,
                this.orderMessage,
                orderItems,
                this.deliveryAddress,
                this.customerNumber,
                orderGrossAmount,
                orderNetAmount,
                discount);
        isValidOrder(amendedOrder);
        return amendedOrder;
    }

    public Order mapOrderRepoValuesToOrder(OrderDto orderDto){
        return this;
    }

    public Order changeDeliveryAddress(DeliveryAddress deliveryAddress){
        isValidStateToChangeAddress();
        OrderDeliveryAddress orderDeliveryAddress = generateDeliveryAddress(deliveryAddress);
        Order addressChnagedOrder = new Order(UUID.randomUUID().toString(),
                this.orderStatus,
                this.trackingId,
                this.orderMessage,
                this.orderItemList,
                orderDeliveryAddress,
                this.customerNumber,
                this.orderGrossAmount,
                this.orderNetAmount,
                this.orderDiscount);
        cancelOrder();
        return addressChnagedOrder;
    }


    private void isValidStateToChangeAddress() {
    }

    public Order cancelOrder(){
        isValidStateToCancelOrder();
        this.orderStatus = null; //set cancel Status;
        return this;
    }

    public Order approveOrder(){
        isValidStateToApproveOrder();
        this.orderStatus = null;
        return this;
    }

    public Order pay(){
        isValidStateToPayOrder();
        this.orderStatus = null;
        return this;
    }

    private void isValidOrder(Order order) {
    }

    private void isValidStateToPayOrder() {
    }

    private void isValidStateToApproveOrder() {
    }

    private void isValidStateToAddProduct() {

    }

    private void isValidStateToCancelOrder(){

    }


    private List<OrderItem> generateOrderItemList(List<Product> products){
        return products.stream().map(product -> new OrderItem(this.trackingId,
                product.getAmountOfEachQuantity(),
                product.getProductCode(),
                product.getGrossValue(), product.getNetValue(), product.getDiscountValue())).collect(Collectors.toList());
    }

    private List<OrderItem> generateAmendedItemList(List<Product> products, List<OrderItem> orderItemDtoList){
        return null;
    }

    private OrderDeliveryAddress generateDeliveryAddress(DeliveryAddress deliveryAddress) {
        OrderDeliveryAddress orderDeliveryAddress = new OrderDeliveryAddress(
                deliveryAddress.getBuildingNumber(),
                deliveryAddress.getStreetAddress(),
                deliveryAddress.getCity(),
                deliveryAddress.getPostalCode());

        return orderDeliveryAddress;
    }

}
