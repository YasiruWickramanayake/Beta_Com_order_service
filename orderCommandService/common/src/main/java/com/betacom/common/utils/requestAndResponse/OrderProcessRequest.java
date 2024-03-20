package com.betacom.common.utils.requestAndResponse;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProcessRequest {
    private Integer orderId;
    private String orderTrackingNumber;

}
