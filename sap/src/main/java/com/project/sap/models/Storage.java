package com.project.sap.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="Storage")
public class Storage {
    @NotNull
    @Id
    @Column(unique = true, name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="Capacity", nullable = false)
    private int capacity;

    @Column(name="Type", nullable = false, length = 63)
    private String type;

    @Column(name="Connector", nullable = false, length = 63)
    private String connector;

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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }
}

