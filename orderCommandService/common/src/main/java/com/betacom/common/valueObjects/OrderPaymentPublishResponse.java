package com.betacom.common.valueObjects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderPaymentPublishResponse {
    private Integer paymentMessagePublishStatus;
    private String message;
}
