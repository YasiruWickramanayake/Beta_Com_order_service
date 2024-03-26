package com.betacom.publisher;

import com.betacom.applicationCommon.PaymentServicePublisher;
import com.betacom.common.utils.OrderStatus;
import com.betacom.common.valueObjects.OrderDto;
import com.betacom.common.valueObjects.OrderPaymentPublishResponse;
import com.betacom.publisher.publisherMessages.OrderPaymentPublisherMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
class OrderPaymentEventPublisher implements PaymentServicePublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String TOPIC_NAME= "payment-initiate-event";

    public OrderPaymentEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public OrderPaymentPublishResponse publishOrderPayment(OrderDto orderDto) {
        try {
            String message = populateOrderPaymentPublisherMessage(orderDto);
//            kafkaTemplate.send(TOPIC_NAME, message);
            System.out.println("Message " + message +
                    " has been sucessfully sent to the topic: " + TOPIC_NAME);
            return OrderPaymentPublishResponse.builder()
                    .paymentMessagePublishStatus(OrderStatus.ORDER_PAYMENT_INITIATE_SUCCESS.getStatusCode())
                    .message(OrderStatus.ORDER_PAYMENT_INITIATE_SUCCESS.getStatusMessage())
                    .build();
        }catch (Exception ex){
            return OrderPaymentPublishResponse.builder()
                    .paymentMessagePublishStatus(OrderStatus.ORDER_PAYMENT_INITIATE_FAILED.getStatusCode())
                    .message(OrderStatus.ORDER_PAYMENT_INITIATE_FAILED.getStatusMessage())
                    .build();
        }
    }

    private String populateOrderPaymentPublisherMessage(OrderDto orderDto){
        try {
            OrderPaymentPublisherMessage messageObject = OrderPaymentPublisherMessage.builder()
                    .orderTrackingId(orderDto.getOrderTrackingId())
                    .paymentValue(orderDto.getOrderNetAmount().getAmount())
                    .paymentCurrency(orderDto.getOrderNetAmount().getCurrency()).build();
            ObjectWriter messageCreator = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return messageCreator.writeValueAsString(messageObject);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
