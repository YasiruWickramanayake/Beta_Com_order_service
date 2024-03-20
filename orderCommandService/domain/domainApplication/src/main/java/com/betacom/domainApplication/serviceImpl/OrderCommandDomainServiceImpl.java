package com.betacom.domainApplication.serviceImpl;

import com.betacom.common.utils.requestAndResponse.OrderInitiateRequest;
import com.betacom.common.valueObjects.DeliveryAddress;
import com.betacom.common.valueObjects.OrderDto;
import com.betacom.common.valueObjects.OrderItemDto;
import com.betacom.coreDomain.entity.Order;
import com.betacom.coreDomain.entity.OrderDeliveryAddress;
import com.betacom.coreDomain.entity.OrderItem;
import com.betacom.domainApplication.service.OrderCommandDomainService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
class OrderCommandDomainServiceImpl implements OrderCommandDomainService {


    public OrderDto initiateOrder(OrderInitiateRequest orderInitiateRequest){
        Order order = new Order(orderInitiateRequest.getCustomerNumber(),
                orderInitiateRequest.getProductList(),
                orderInitiateRequest.getDeliveryAddress(),
                orderInitiateRequest.getOrderGrossAmount(),
                orderInitiateRequest.getOrderNetAmount(),
                orderInitiateRequest.getOrderDiscount());
        return populateOrderDto(order);
    }

    @Override
    public OrderDto approveOrder(OrderDto orderDto) {
        Order order = populateOrderBean(orderDto);
        order.approveOrder();
        return populateOrderDto(order);
    }


    private OrderDto populateOrderDto(Order order){
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .orderTrackingId(order.getTrackingId())
                .orderStatus(order.getOrderStatus())
                .orderGrossAmount(order.getOrderGrossAmount())
                .orderNetAmount(order.getOrderNetAmount())
                .orderDiscountAmount(order.getOrderDiscount())
                .orderItemDtoList(null)
                .message(order.getOrderMessage())
                .build();
    }

    private List<OrderItemDto> populateOrderItemDto(List<OrderItem> orderItemList){
        orderItemList.stream().map(orderItem -> OrderItemDto.builder()
                .orderId(orderItem.getOrderId())
                .OrderTrackingId(orderItem.getOrderTrackingId())
                .orderItemRefNumber())

    }


    private Order populateOrderBean(OrderDto orderDto){
        List<OrderItem> orderItems = populateOrederItemList(orderDto.getOrderItemDtoList());
        OrderDeliveryAddress orderDeliveryAddress = populateOrderDeliveryAddress(orderDto.getDeliveryAddress());
        return new Order(orderDto.getOrderId(),
                orderDto.getOrderTrackingId(),
                orderDto.getOrderStatus(),
                orderDto.getOriginalOrderTrackingId(),
                orderDto.getMessage(),
                orderItems,
                orderDeliveryAddress,
                orderDto.getCustomerNumber(),
                orderDto.getOrderGrossAmount(),
                orderDto.getOrderNetAmount(),
                orderDto.getOrderDiscountAmount());
    }

    private List<OrderItem> populateOrederItemList(List<OrderItemDto> orderItemDtoList) {
        return orderItemDtoList.stream().map(orderItemDto -> new OrderItem(orderItemDto.getOrderId(),
                        orderItemDto.getOrderTrackingId(),
                        orderItemDto.getAmountPerQuantity(),
                        orderItemDto.getProductCode(),
                        orderItemDto.getItemGrossAmount(),
                        orderItemDto.getItemNetAmount(),
                        orderItemDto.getItemDiscountAmount()))
                .collect(Collectors.toList());
    }

    private OrderDeliveryAddress populateOrderDeliveryAddress(DeliveryAddress deliveryAddress){
        OrderDeliveryAddress orderDeliveryAddress = new OrderDeliveryAddress(deliveryAddress.getBuildingNumber(),
                deliveryAddress.getStreetAddress(), deliveryAddress.getCity(), deliveryAddress.getPostalCode());
        return orderDeliveryAddress;
    }


}
