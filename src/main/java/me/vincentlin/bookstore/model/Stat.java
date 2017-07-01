package me.vincentlin.bookstore.model;

import java.math.BigDecimal;

public class Stat {
    Long saleCount;
    BigDecimal saleAmount;

    public Long getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Long saleCount) {
        this.saleCount = saleCount;
    }

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }
}
