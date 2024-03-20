package com.betacom.repository.dao;

import com.betacom.applicationCommon.OrderCommandRepository;
import com.betacom.common.valueObjects.OrderDto;
import com.betacom.repository.entity.OrderEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class OrderCommandRepositoryImpl implements OrderCommandRepository {


    private OrderCommandJPARepo orderCommandJPARepo;
    @Override
    public OrderDto initiateOrderRequestSave(OrderDto orderDto) {
        OrderEntity orderEntity = new OrderEntity();
        OrderEntity initiatedOrder = orderCommandJPARepo.save(orderEntity);
        return null;
    }

    @Override
    public OrderDto getOrderByOrderId(Integer orderId) {
        return null;
    }


}
