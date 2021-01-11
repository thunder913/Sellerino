package com.project.sap.models.Dto;


import java.util.List;

public class LaptopDto {

    public LaptopDto(){

    }

    private long id;

    private String manufacturer;

    private String price;

    private String model;

    private long processorId;

    private long screenId;

    private long videoCardId;

    private List<Long> ramIds;

    private List<Long> storageIds;

    private List<String> imageUrls;

    public long getProcessorId() {
        return processorId;
    }

    public void setProcessorId(long processorId) {
        this.processorId = processorId;
    }

    public long getScreenId() {
        return screenId;
    }

    public void setScreenId(long screenId) {
        this.screenId = screenId;
    }

    public long getVideoCardId() {
        return videoCardId;
    }

    public void setVideoCardId(long videoCardId) {
        this.videoCardId = videoCardId;
    }

    public List<Long> getRamIds() {
        return ramIds;
    }

    public void setRamIds(List<Long> ramIds) {
        this.ramIds = ramIds;
    }

    public List<Long> getStorageIds() {
        return storageIds;
    }

    public void setStorageIds(List<Long> storageIds) {
        this.storageIds = storageIds;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
