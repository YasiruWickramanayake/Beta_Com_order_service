package com.betacom.common.valueObjects;

import com.betacom.common.valueObjects.Money;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String productCode;
    private Money amountOfEachQuantity;
    private long quantity;
    private Money netValue;
    private Money grossValue;
    private Money discountValue;
}
