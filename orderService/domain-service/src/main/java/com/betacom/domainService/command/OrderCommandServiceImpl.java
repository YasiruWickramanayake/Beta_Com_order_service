package com.betacom.domainService.command;

import com.betacom.domain.entity.Order;
import com.betacom.domainService.command.input.OrderCommandService;
import org.springframework.stereotype.Service;

@Service
class OrderCommandServiceImpl implements OrderCommandService {
    @Override
    public void createOrder() {
        Order order = new Order();
    }
    //Order Command Service Implementation


}
