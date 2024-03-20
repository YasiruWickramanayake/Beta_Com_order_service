package com.betacom.applicationCommon;

import com.betacom.applicationInput.OrderCommandApplicationService;
import com.betacom.common.exception.OrderServiceException;
import com.betacom.common.utils.requestAndResponse.OrderInitiateRequest;
import com.betacom.common.utils.requestAndResponse.OrderProcessRequest;
import com.betacom.common.utils.requestAndResponse.OrderResponse;
import com.betacom.common.valueObjects.OrderDto;
import com.betacom.domainApplication.service.OrderCommandDomainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class OrderCommandApplicationServiceImpl implements OrderCommandApplicationService {

    private OrderCommandDomainService orderCommandDomainService;
    private OrderCommandRepository orderCommandRepository;
    @Override
    public OrderResponse initiateTheOrder(OrderInitiateRequest orderInitiateRequest) {
        try {
            OrderDto orderDto = orderCommandDomainService.initiateOrder(orderInitiateRequest);
            orderDto = orderCommandRepository.initiateOrderRequestSave(orderDto);
            return generateOrderResponse(orderDto.getOrderStatus(), orderDto.getMessage(), orderDto.getOrderTrackingId());
        } catch (OrderServiceException ex) {
            return generateOrderResponse(ex.getExceptionCode(), ex.getMessage(), null);
        }
    }

    @Override
    public OrderResponse placeOrder(OrderProcessRequest orderProcessRequest) {
        // get the order from DB
        OrderDto orderByOrderId = orderCommandRepository.getOrderByOrderId(orderProcessRequest.getOrderId());
        // approve the order

        return null;
    }

    private OrderResponse generateOrderResponse(Integer responseCode, String responseMessage, String orderTrackingId){
        return OrderResponse.builder()
                .orderTrackingId(orderTrackingId)
                .responseCode(responseCode)
                .responseMessage(responseMessage)
                .build();
    }
}
