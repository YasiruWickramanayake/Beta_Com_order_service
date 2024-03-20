package com.betacom.common.valueObjects;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAddress {
    private String buildingNumber;
    private String streetAddress;
    private String city;
    private String postalCode;


}
