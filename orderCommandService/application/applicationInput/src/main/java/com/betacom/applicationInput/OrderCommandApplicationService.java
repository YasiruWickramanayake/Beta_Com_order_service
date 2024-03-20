package com.betacom.applicationInput;

import com.betacom.common.utils.requestAndResponse.OrderInitiateRequest;
import com.betacom.common.utils.requestAndResponse.OrderProcessRequest;
import com.betacom.common.utils.requestAndResponse.OrderResponse;

public interface OrderCommandApplicationService {

    public OrderResponse initiateTheOrder(OrderInitiateRequest orderInitiateRequest);

    public OrderResponse placeOrder(OrderProcessRequest orderProcessRequest);
}
