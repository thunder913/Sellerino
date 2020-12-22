package com.project.sap.models;

import com.sun.istack.NotNull;

import javax.persistence.*;


@Entity
@Table(name="RAM")
public class RAM {
    @NotNull
    @Id
    @Column(unique = true, name="Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="manufacturer", nullable = false, length = 63)
    private String manufacturer;

    @Column(name="Memory", nullable = false)
    private int memory;

    @Column(name="Name", nullable = false)
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

