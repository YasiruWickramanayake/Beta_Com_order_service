package com.betacom.controller;

import com.betacom.applicationInput.OrderCommandApplicationService;
import com.betacom.common.utils.requestAndResponse.OrderInitiateRequest;
import com.betacom.common.utils.requestAndResponse.OrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderCommandApplicationService orderCommandApplicationService;

    @PostMapping("/initiate-order")
    public OrderResponse initiateOrder(OrderInitiateRequest orderInitiateRequest){
        return orderCommandApplicationService.initiateTheOrder(orderInitiateRequest);
    }

//    @GetMapping("/test-kafka")
//    public String testKafka(){
//       return orderCommandApplicationService.testKafka();
//    }
}
