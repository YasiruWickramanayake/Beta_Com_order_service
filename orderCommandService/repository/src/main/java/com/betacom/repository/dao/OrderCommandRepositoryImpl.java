package com.betacom.repository.dao;

import com.betacom.applicationCommon.OrderCommandRepository;
import com.betacom.common.valueObjects.DeliveryAddress;
import com.betacom.common.valueObjects.OrderDto;
import com.betacom.common.valueObjects.OrderItemDto;
import com.betacom.repository.entity.DeliveryAddressEntity;
import com.betacom.repository.entity.OrderEntity;
import com.betacom.repository.entity.OrderItemEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
class OrderCommandRepositoryImpl implements OrderCommandRepository {


    @Autowired
    private OrderCommandJPARepo orderCommandJPARepo;
    @Override
    public OrderDto saveOrder(OrderDto orderDto) {
        OrderEntity orderEntity = mapOrderDtoToOrderEntity(orderDto);
        OrderEntity initiatedOrder = orderCommandJPARepo.save(orderEntity);
        return null;
    }

    @Override
    public OrderDto getOrderByOrderId(Integer orderId) {
        return null;
    }



    private OrderEntity mapOrderDtoToOrderEntity(OrderDto orderDto){
        OrderEntity orderEntity = populateOrderEntity(orderDto);
        List<OrderItemEntity> orderItemEntities = mapOrderItemDtoToOrderItemEntity(orderDto.getOrderItemDtoList(), orderEntity);
        //populate order items
        orderEntity.setOrderItemEntities(orderItemEntities);
        //populate delivery address
        DeliveryAddressEntity deliveryAddressEntity = populateDeliveryAddressEntity(orderDto.getDeliveryAddress(), orderEntity);
        orderEntity.setDeliveryAddressEntity(deliveryAddressEntity);
        return orderEntity;
    }

    private OrderEntity populateOrderEntity(OrderDto orderDto){
        return OrderEntity.builder()
                .orderId(orderDto.getOrderId())
                .orderTrackingNumber(orderDto.getOrderTrackingId())
                .orderGrossAmount(orderDto.getOrderGrossAmount().getAmount())
                .orderGrsAmntCur(orderDto.getOrderGrossAmount().getCurrency())
                .orderNetAmount(orderDto.getOrderNetAmount().getAmount())
                .orderNetAmntCur(orderDto.getOrderNetAmount().getCurrency())
                .orderDiscntAmount(orderDto.getOrderDiscountAmount().getAmount())
                .orderDiscntAmntCur(orderDto.getOrderDiscountAmount().getCurrency())
                .build();
    }

    private List<OrderItemEntity> mapOrderItemDtoToOrderItemEntity(List<OrderItemDto> orderItemDtoList, OrderEntity orderEntity){
        return orderItemDtoList.stream().map(orderItemDto -> OrderItemEntity.builder()
                .itemId(orderItemDto.getItemId())
                .orderEntity(orderEntity)
                .itemGrossAmount(orderItemDto.getItemGrossAmount().getAmount())
                .itemGrsAmntCur(orderItemDto.getItemGrossAmount().getCurrency())
                .itemNetAmount(orderItemDto.getItemNetAmount().getAmount())
                .itemNetAmntCur(orderItemDto.getItemNetAmount().getCurrency())
                .quantity(orderItemDto.getQuantity())
                .amountPerQuantity(orderItemDto.getAmountPerQuantity().getAmount())
                .amntCurPerQuantity(orderItemDto.getAmountPerQuantity().getCurrency())
                .build())
                .collect(Collectors.toList());
    }

    private DeliveryAddressEntity populateDeliveryAddressEntity(DeliveryAddress deliveryAddress, OrderEntity orderEntity){
        return DeliveryAddressEntity.builder()
                .buildingNumber(deliveryAddress.getBuildingNumber())
                .streetName(deliveryAddress.getStreetAddress())
                .cityName(deliveryAddress.getCity())
                .postalCode(deliveryAddress.getPostalCode())
                .orderEntity(orderEntity)
                .build();

    }

}
