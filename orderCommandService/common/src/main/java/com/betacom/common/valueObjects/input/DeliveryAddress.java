package com.betacom.common.valueObjects.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryAddress {
    private String buildingNumber;
    private String streetAddress;
    private String city;
    private String postalCode;


}
