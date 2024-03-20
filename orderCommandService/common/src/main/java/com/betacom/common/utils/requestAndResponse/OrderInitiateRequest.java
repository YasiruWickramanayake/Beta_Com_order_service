package com.betacom.common.utils.requestAndResponse;

import com.betacom.common.valueObjects.DeliveryAddress;
import com.betacom.common.valueObjects.Money;
import com.betacom.common.valueObjects.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderInitiateRequest {

    private String customerNumber;
    private Money orderGrossAmount;
    private Money orderNetAmount;
    private Money orderDiscount;
    private DeliveryAddress deliveryAddress;
    private List<Product> productList;
}
