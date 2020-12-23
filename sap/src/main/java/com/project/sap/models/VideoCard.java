package com.project.sap.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="Video_Card")
public class VideoCard {
    @NotNull
    @Id
    @Column(unique = true, name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="Name", nullable = false, length = 127)
    private String name;

    @Column(name="Manufacturer", nullable = false, length = 63)
    private String manufacturer;

    @Column(name="Model", nullable = false, length = 63)
    private String model;

    @Column(name="Technology", nullable = false, length = 31)
    private String technology;

    @Column(name="Memory", nullable = false, columnDefinition = "TINYINT")
    private int memory;

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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }
}
