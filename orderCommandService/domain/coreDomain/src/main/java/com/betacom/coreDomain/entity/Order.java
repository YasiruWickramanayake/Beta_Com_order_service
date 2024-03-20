package com.betacom.coreDomain.entity;

import com.betacom.common.exception.OrderServiceException;
import com.betacom.common.utils.ErrorCodes;
import com.betacom.common.utils.OrderStatus;
import com.betacom.common.valueObjects.DeliveryAddress;
import com.betacom.common.valueObjects.Money;
import com.betacom.common.valueObjects.Product;
import com.betacom.common.valueObjects.OrderDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        this.orderStatus = OrderStatus.ORDER_INITIATE.getStatusCode();
        this.originalOrderTrackingId = null;
        this.orderMessage = OrderStatus.ORDER_INITIATE.getStatusMessage();
        this.orderItemList = generateOrderItemList(products);
        this.deliveryAddress = generateDeliveryAddress(deliveryAddress);
        this.customerNumber = customerNumber;
        this.orderGrossAmount = orderGrossAmount;
        this.orderNetAmount = orderNetAmount;
        this.orderDiscount = orderDiscount;
        isValidOrder(this);
    }

    public Order(Integer orderId,
            String trackingId,
                 Integer orderStatus,
                 String originalOrderTrackingId,
                 String orderMessage,
                 List<OrderItem> orderItemList,
                 OrderDeliveryAddress orderDeliveryAddress,
                 String customerNumber,
                 Money orderGrossAmount,
                 Money orderNetAmount,
                 Money orderDiscount) {
        this.orderId = orderId;
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

    //This constructor can be used to when map the entity data to the Order aggrerate root
    public Order(String trackingId,
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




    public Order cancelOrder(){
        isValidStateToCancelOrder();
        this.orderStatus = OrderStatus.ORDER_CANCEL.getStatusCode(); //set cancel Status;
        return this;
    }

    public Order approveOrder(){
        //confirm the order
        isValidStateToApproveOrder();
        this.orderStatus = OrderStatus.ORDER_APPROVED.getStatusCode();
        return this;
    }

    public Order readyToPay(){
        isValidStateToPayOrder();
        this.orderStatus = OrderStatus.ORDER_READY_TO_PAY.getStatusCode();
        return this;
    }

    public Order paymentSuccess(){
        this.orderStatus = OrderStatus.ORDER_PAYMENT_SUCCESS.getStatusCode();
        return this;
    }

    private void isValidOrder(Order order) {
        BigDecimal orderItemPrices = BigDecimal.valueOf(0);
        for(OrderItem orderItem : order.orderItemList){
            orderItemPrices = orderItemPrices.add(orderItem.getNetAmount().getAmount());
        }
        if(!order.orderNetAmount.getAmount().equals(orderItemPrices)){
            throw new OrderServiceException(ErrorCodes.ORDER_ITEM_PRICES_ARE_INCORRECT.getErrorCode()
                    , ErrorCodes.ORDER_ITEM_PRICES_ARE_INCORRECT.getErrorMessage());
        }
    }

    private void isValidStateToPayOrder() {
        if(this.orderStatus != OrderStatus.ORDER_READY_TO_PAY.getStatusCode()){
            throw new OrderServiceException(ErrorCodes.ORDER_PAYMENT_PROCEED_IN_INCORRECT_STATUS.getErrorCode(),
                    ErrorCodes.ORDER_PAYMENT_PROCEED_IN_INCORRECT_STATUS.getErrorMessage());
        }
    }

    private void isValidStateToApproveOrder() {
        if(this.orderStatus != OrderStatus.ORDER_PAYMENT_SUCCESS.getStatusCode()){
            throw new OrderServiceException(ErrorCodes.ORDER_IS_NOT_VALID_STATE_TO_APPROVE.getErrorCode(),
                    ErrorCodes.ORDER_ITEM_PRICES_ARE_INCORRECT.getErrorMessage());
        }
    }

    private void isValidStateToAddProduct() {
        if(this.orderStatus != OrderStatus.ORDER_INITIATE.getStatusCode()){
            throw new OrderServiceException(ErrorCodes.ORDER_ITEM_CANT_BE_ADDED_AT_THIS_STATE.getErrorCode(),
                    ErrorCodes.ORDER_ITEM_CANT_BE_ADDED_AT_THIS_STATE.getErrorMessage());
        }
    }

    private void isValidStateToCancelOrder(){
        if(this.orderStatus != OrderStatus.ORDER_INITIATE.getStatusCode()){
            throw new OrderServiceException(ErrorCodes.ORDER_CANCEL_IN_INCORRECT_STATE.getErrorCode(),
                    ErrorCodes.ORDER_CANCEL_IN_INCORRECT_STATE.getErrorMessage());
        }
    }

    private void isValidStateToChangeAddress() {
        if(this.orderStatus != OrderStatus.ORDER_INITIATE.getStatusCode()){
            throw new OrderServiceException(ErrorCodes.ORDER_ADDRESS_CHANGE_IN_INCORRECT_STATE.getErrorCode(),
                    ErrorCodes.ORDER_ADDRESS_CHANGE_IN_INCORRECT_STATE.getErrorMessage());
        }
    }


    private List<OrderItem> generateOrderItemList(List<Product> products){
        return products.stream().map(product -> new OrderItem(this.orderId, this.trackingId,
                product.getAmountOfEachQuantity(),
                product.getProductCode(),
                product.getGrossValue(), product.getNetValue(), product.getDiscountValue())).collect(Collectors.toList());
    }

    private List<OrderItem> generateAmendedItemList(List<Product> products, List<OrderItem> orderItemList){
        List<OrderItem> newOrderItems = products.stream().map(product -> new OrderItem(null, this.trackingId,
                product.getAmountOfEachQuantity(),
                product.getProductCode(),
                product.getGrossValue(), product.getNetValue(), product.getDiscountValue())).collect(Collectors.toList());
        return Stream.of(orderItemList, newOrderItems).flatMap(orderItems -> newOrderItems.stream()).collect(Collectors.toList());
    }

    private OrderDeliveryAddress generateDeliveryAddress(DeliveryAddress deliveryAddress) {
        OrderDeliveryAddress orderDeliveryAddress = new OrderDeliveryAddress(
                deliveryAddress.getBuildingNumber(),
                deliveryAddress.getStreetAddress(),
                deliveryAddress.getCity(),
                deliveryAddress.getPostalCode());

        return orderDeliveryAddress;
    }


    public Integer getOrderId() {
        return orderId;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public String getOriginalOrderTrackingId() {
        return originalOrderTrackingId;
    }

    public String getOrderMessage() {
        return orderMessage;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public OrderDeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public Money getOrderGrossAmount() {
        return orderGrossAmount;
    }

    public Money getOrderNetAmount() {
        return orderNetAmount;
    }

    public Money getOrderDiscount() {
        return orderDiscount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", trackingId='" + trackingId + '\'' +
                ", orderStatus=" + orderStatus +
                ", originalOrderTrackingId='" + originalOrderTrackingId + '\'' +
                ", orderMessage='" + orderMessage + '\'' +
                ", orderItemList=" + orderItemList +
                ", deliveryAddress=" + deliveryAddress +
                ", customerNumber='" + customerNumber + '\'' +
                ", orderGrossAmount=" + orderGrossAmount.toString() +
                ", orderNetAmount=" + orderNetAmount.toString() +
                ", orderDiscount=" + orderDiscount.toString() +
                '}';
    }
}
