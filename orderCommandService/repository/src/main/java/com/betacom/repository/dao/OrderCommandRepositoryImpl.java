package com.betacom.repository.dao;

import com.betacom.applicationCommon.OrderCommandRepository;
import com.betacom.common.valueObjects.OrderDto;
import com.betacom.repository.entity.OrderEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class OrderCommandRepositoryImpl implements OrderCommandRepository {


    @Autowired
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
