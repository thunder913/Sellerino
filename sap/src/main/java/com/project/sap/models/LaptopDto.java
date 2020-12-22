package com.project.sap.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class LaptopDto extends Laptop {
    @Transient
    private int processorId;
    @Transient
    private int screenId;
    @Transient
    private int videoCardId;
    @Transient
    private List<Integer> ramIds;
    @Transient
    private List<Integer> storageIds;
    @Transient
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
}
