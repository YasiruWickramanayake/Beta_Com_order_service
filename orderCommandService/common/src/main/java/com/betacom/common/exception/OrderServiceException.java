package com.betacom.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderServiceException extends RuntimeException{
    private int exceptionCode;
    private String message;
}
