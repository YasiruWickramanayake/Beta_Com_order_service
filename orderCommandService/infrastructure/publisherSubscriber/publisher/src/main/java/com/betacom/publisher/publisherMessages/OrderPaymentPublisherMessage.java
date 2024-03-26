package com.betacom.publisher.publisherMessages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class OrderPaymentPublisherMessage {

    private String orderTrackingId;
    private BigDecimal paymentValue;
    private String paymentCurrency;
}
