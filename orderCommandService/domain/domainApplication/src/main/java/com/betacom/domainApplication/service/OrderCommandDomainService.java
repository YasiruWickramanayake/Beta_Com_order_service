package com.betacom.domainApplication.service;

import com.betacom.common.utils.requestAndResponse.OrderInitiateRequest;
import com.betacom.common.valueObjects.OrderDto;

public interface OrderCommandDomainService {

    public OrderDto initiateOrder(OrderInitiateRequest orderInitiateRequest);
    public OrderDto approveOrder(OrderDto orderDto);
}
