package com.betacom.applicationCommon;

import com.betacom.applicationInput.OrderCommandApplicationService;
import com.betacom.common.exception.OrderServiceException;
import com.betacom.common.utils.OrderStatus;
import com.betacom.common.utils.requestAndResponse.OrderInitiateRequest;
import com.betacom.common.utils.requestAndResponse.OrderProcessRequest;
import com.betacom.common.utils.requestAndResponse.OrderResponse;
import com.betacom.common.valueObjects.OrderDto;
import com.betacom.common.valueObjects.OrderPaymentPublishResponse;
import com.betacom.domainApplication.service.OrderCommandDomainService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
class OrderCommandApplicationServiceImpl implements OrderCommandApplicationService {

    @Autowired
    private OrderCommandDomainService orderCommandDomainService;
    @Autowired
    private OrderCommandRepository orderCommandRepository;
    @Autowired
    private PaymentServicePublisher paymentServicePublisher;
    @Override
    public OrderResponse initiateTheOrder(OrderInitiateRequest orderInitiateRequest) {
        try {
            OrderDto orderDto = orderCommandDomainService.initiateOrder(orderInitiateRequest);
            orderDto = orderCommandRepository.saveOrder(orderDto);
            return generateOrderResponse(orderDto.getOrderStatus(), orderDto.getMessage(), orderDto.getOrderTrackingId());
        } catch (OrderServiceException ex) {
            return generateOrderResponse(ex.getExceptionCode(), ex.getMessage(), null);
        }
    }

    @Override
    public OrderResponse placeOrder(OrderProcessRequest orderProcessRequest) {
       try {
           // get the order from DB
           OrderDto orderByOrderId = orderCommandRepository.getOrderByOrderId(orderProcessRequest.getOrderId());
           // approve the order
           OrderDto orderDto = orderCommandDomainService.approveOrder(orderByOrderId);
           // Order pass to the payment
           OrderPaymentPublishResponse orderPaymentPublishResponse = paymentServicePublisher.publishOrderPayment(orderDto);
           if (orderPaymentPublishResponse.getPaymentMessagePublishStatus() == OrderStatus.ORDER_PAYMENT_INITIATE_SUCCESS.getStatusCode()) {
               orderCommandRepository.saveOrder(orderDto);
           }
           return null;
       }catch (Exception ex){
           return null;
       }
    }

    @Override
    public void OrderPaymentResponse() {

    }


    public String testKafka() {
        paymentServicePublisher.publishOrderPayment(null);
        return "call eka giyadoo";
    }

    private OrderResponse generateOrderResponse(Integer responseCode, String responseMessage, String orderTrackingId){
        return OrderResponse.builder()
                .orderTrackingId(orderTrackingId)
                .responseCode(responseCode)
                .responseMessage(responseMessage)
                .build();
    }
}
