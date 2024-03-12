package com.betacom.common.valueObjects.input;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequest {

    private String customerNumber;
    private Money orderGrossAmount;
    private Money orderNetAmount;
    private Money orderDiscount;
    private DeliveryAddress deliveryAddress;
}
