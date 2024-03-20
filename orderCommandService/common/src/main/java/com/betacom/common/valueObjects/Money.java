package com.betacom.common.valueObjects;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@NoArgsConstructor
public class Money {
    private BigDecimal amount;
    private String currency;

    public Money(double amount, String currency) {
        this.amount = BigDecimal.valueOf(amount);
        this.currency = currency;
    }

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

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
