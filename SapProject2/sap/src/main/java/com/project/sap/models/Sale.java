package com.project.sap.models;

import com.sun.istack.NotNull;
import org.hibernate.jpa.internal.ManagedFlushCheckerLegacyJpaImpl;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="sale")
public class Sale {
    @NotNull
    @Id
    @Column(unique = true, name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="date", nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name="laptop_sales",
            joinColumns = { @JoinColumn(name="sales_id")},
            inverseJoinColumns={@JoinColumn(name="laptop_id")})
    private Laptop laptop;

    @Column(name="quantity", nullable = false)
    private int quantity;

    @Column(name="totalPrice", nullable = false)
    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name="client_sales",
            joinColumns = { @JoinColumn(name="sales_id")},
            inverseJoinColumns={@JoinColumn(name="client_id")})
    private Client buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name="auth_user_sales",
            joinColumns = { @JoinColumn(name="sales_id")},
            inverseJoinColumns={@JoinColumn(name="user_id")})
    private User seller;

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

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
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

    public Client getBuyer() {
        return buyer;
    }

    public void setBuyer(Client buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Sale() {
        this.setTotalPrice(new BigDecimal(0));
    }
}
