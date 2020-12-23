package com.project.sap.models;


import java.util.List;

public class LaptopDto {

    private long id;

    private String manufacturer;

    private float price;

    private int quantity;

    private String model;

    private int processorId;

    private int screenId;

    private int videoCardId;

    private List<Integer> ramIds;

    private List<Integer> storageIds;

    private List<String> imageUrls;

    public int getProcessorId() {
        return processorId;
    }

    public void setProcessorId(int processorId) {
        this.processorId = processorId;
    }

    public int getScreenId() {
        return screenId;
    }

    public void setScreenId(int screenId) {
        this.screenId = screenId;
    }

    public int getVideoCardId() {
        return videoCardId;
    }

    public void setVideoCardId(int videoCardId) {
        this.videoCardId = videoCardId;
    }

    public List<Integer> getRamIds() {
        return ramIds;
    }

    public void setRamIds(List<Integer> ramIds) {
        this.ramIds = ramIds;
    }

    public List<Integer> getStorageIds() {
        return storageIds;
    }

    public void setStorageIds(List<Integer> storageIds) {
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
