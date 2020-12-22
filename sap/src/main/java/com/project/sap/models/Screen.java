package com.project.sap.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="Screen")
public class Screen {
    @NotNull
    @Id
    @Column(unique = true, name="Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="Name", nullable = false)
    private String name;

    @Column(name="Diagonal", nullable = false)
    private float diagonal;

    @Column(name="[Refresh Rate]", nullable = false)
    private int refreshRate;

    @Column(name="[Response Time]", nullable = false)
    private double responseTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(float diagonal) {
        this.diagonal = diagonal;
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(int refreshRate) {
        this.refreshRate = refreshRate;
    }

    public double getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(double responseTime) {
        this.responseTime = responseTime;
    }
}
