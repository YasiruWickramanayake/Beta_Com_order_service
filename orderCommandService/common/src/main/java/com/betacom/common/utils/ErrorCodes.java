package com.betacom.common.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {
    ORDER_ITEM_PRICES_ARE_INCORRECT(400, "Order Item prices are incorrect"),
    ORDER_ITEM_CANT_BE_ADDED_AT_THIS_STATE(401, "Order Item can't be amended at this time"),
    ORDER_CANCEL_IN_INCORRECT_STATE(402, "Order can't be cancelled at this status"),
    ORDER_ADDRESS_CHANGE_IN_INCORRECT_STATE(403, "Order address can't be changed at this status"),
    ORDER_PAYMENT_PROCEED_IN_INCORRECT_STATUS(404, "Order payment can't be changed at this status"),
    ORDER_IS_NOT_VALID_STATE_TO_APPROVE(410, "Order is not valid status to approve");

    private int errorCode;
    private String errorMessage;
}
