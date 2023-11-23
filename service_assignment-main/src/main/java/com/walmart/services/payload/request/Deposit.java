package com.walmart.services.payload.request;

import java.math.BigDecimal;

public class Deposit {
    public BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
