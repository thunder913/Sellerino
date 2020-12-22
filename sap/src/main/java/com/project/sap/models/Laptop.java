package com.project.sap.models;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Currency;
import java.util.List;

@Entity
@Table(name="Laptop")
public class Laptop {
    @NotNull
    @Id
    @Column(unique = true, name="Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="Manufacturer", nullable = false)
    private String manufacturer;

    @Column(name="Price", nullable = false)
    private float price;

    @Column(name="Quantity", nullable=false)
    private int quantity;

    @Column(name="Model", nullable = false)
    private String model;

    @ManyToMany(cascade = CascadeType.ALL)
    @NotNull
    private List<Image> images;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Processor processor;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Screen Screen;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private VideoCard videoCard;

    @ManyToMany(cascade = CascadeType.ALL)
    @NotNull
    private List<RAM> ram;

    @ManyToMany(cascade = CascadeType.ALL)
    @NotNull
    private List<Storage> storage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public com.project.sap.models.Screen getScreen() {
        return Screen;
    }

    public void setScreen(com.project.sap.models.Screen screen) {
        Screen = screen;
    }

    public VideoCard getVideoCard() {
        return videoCard;
    }

    public void setVideoCard(VideoCard videoCard) {
        this.videoCard = videoCard;
    }

    public List<RAM> getRam() {
        return ram;
    }

    public void setRam(List<RAM> ram) {
        this.ram = ram;
    }

    public List<Storage> getStorage() {
        return storage;
    }

    public void setStorage(List<Storage> storage) {
        this.storage = storage;
    }
}
