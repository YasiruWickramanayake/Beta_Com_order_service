package com.betacom.common.valueObjects.input;

import com.betacom.common.valueObjects.input.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private String productCode;
    private Money amountOfEachQuantity;
    private Money quantity;
    private Money netValue;
    private Money grossValue;
    private Money discountValue;
}
