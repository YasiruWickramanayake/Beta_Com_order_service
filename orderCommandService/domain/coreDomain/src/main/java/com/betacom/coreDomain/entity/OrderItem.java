package com.betacom.coreDomain.entity;

import com.betacom.common.valueObjects.Money;

import java.util.UUID;

public class OrderItem {
    private final Integer orderId;
    private final String orderItemCode;
    private final String orderTrackingId;
    private final Money priceOfEachQuantity;
    private final String productCode;
    private final Money grossAmount;
    private final Money netAmount;
    private final Money discountValue;

    public OrderItem(Integer orderId,
                     String orderTrackingId,
                     Money priceOfEachQuantity,
                     String productCode,
                     Money grossAmount,
                     Money netAmount,
                     Money discountValue) {
        this.orderId = orderId;
        this.orderItemCode = UUID.randomUUID().toString();
        this.orderTrackingId = orderTrackingId;
        this.priceOfEachQuantity = priceOfEachQuantity;
        this.productCode = productCode;
        this.grossAmount = grossAmount;
        this.netAmount = netAmount;
        this.discountValue = discountValue;
    }

    public String getOrderItemCode() {
        return orderItemCode;
    }

    public String getOrderTrackingId() {
        return orderTrackingId;
    }

    public Money getPriceOfEachQuantity() {
        return priceOfEachQuantity;
    }

    public String getProductCode() {
        return productCode;
    }

    public Money getGrossAmount() {
        return grossAmount;
    }

    public Money getNetAmount() {
        return netAmount;
    }

    public Money getDiscountValue() {
        return discountValue;
    }

    public Integer getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemCode='" + orderItemCode + '\'' +
                ", orderTrackingId='" + orderTrackingId + '\'' +
                ", priceOfEachQuantity=" + priceOfEachQuantity +
                ", productCode='" + productCode + '\'' +
                ", grossAmount=" + grossAmount.toString() +
                ", netAmount=" + netAmount.toString() +
                ", discountValue=" + discountValue.toString() +
                '}';
    }
}
