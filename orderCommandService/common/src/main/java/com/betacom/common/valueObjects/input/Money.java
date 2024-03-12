package com.betacom.common.valueObjects.input;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


public class Money {
    private BigDecimal amount;
    private String currency;

    public BigDecimal getAmount() {
        return amount;
    }

    public Money setAmount(double amount) {
        this.amount = BigDecimal.valueOf(amount);
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public Money setCurrency(String currency) {
        this.currency = currency;
        return this;
    }
}
