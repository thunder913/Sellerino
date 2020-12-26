package com.project.sap.models.Dto;

import com.project.sap.models.Client;
import com.project.sap.models.Laptop;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

public class SaleDto {

    private long id;

    private Date date;

    private long laptopId;

    private int quantity;

    private BigDecimal totalPrice;

    private BigDecimal priceForOne;

    private long buyerId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getPriceForOne() {
        return priceForOne;
    }

    public void setPriceForOne(BigDecimal priceForOne) {
        this.priceForOne = priceForOne;
    }

    public long getLaptopId() {
        return laptopId;
    }

    public void setLaptopId(long laptopId) {
        this.laptopId = laptopId;
    }

    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }
}
