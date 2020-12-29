package com.project.sap.models;

import java.math.BigDecimal;

public class Statistic {

    private BigDecimal totalCost;

    private int salesCount;

    private BigDecimal averagePrice;

    private Sale biggestSale;

    private Sale worstSale;

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public int getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(int salesCount) {
        this.salesCount = salesCount;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public Sale getBiggestSale() {
        return biggestSale;
    }

    public void setBiggestSale(Sale biggestSale) {
        this.biggestSale = biggestSale;
    }

    public Sale getWorstSale() {
        return worstSale;
    }

    public void setWorstSale(Sale worstSale) {
        this.worstSale = worstSale;
    }
}
