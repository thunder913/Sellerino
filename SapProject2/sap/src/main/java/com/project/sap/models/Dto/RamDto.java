package com.project.sap.models.Dto;

import javax.persistence.Column;

public class RamDto {

    private String manufacturer;

    private String memory;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }


}
