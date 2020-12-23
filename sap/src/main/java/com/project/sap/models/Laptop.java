package com.project.sap.models;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Currency;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Laptop")
public class Laptop {
    @NotNull
    @Id
    @Column(unique = true, name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="Manufacturer", nullable = false)
    private String manufacturer;

    @Column(name="Price", nullable = false)
    private float price;

    @Column(name="Quantity", nullable=false)
    private int quantity;

    @Column(name="Model", nullable = false)
    private String model;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="laptop_images",
                    joinColumns = { @JoinColumn(name="laptop_id")},
                    inverseJoinColumns={@JoinColumn(name="images_id")})
    private Set<Image> images = new HashSet<Image>();

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Processor processor;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Screen Screen;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private VideoCard videoCard;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="laptop_ram",
            joinColumns = { @JoinColumn(name="laptop_id")},
            inverseJoinColumns={@JoinColumn(name="ram_id")})
    private Set<RAM> ram = new HashSet<RAM>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="laptop_storage",
            joinColumns = { @JoinColumn(name="laptop_id")},
            inverseJoinColumns={@JoinColumn(name="storage_id")})
    private Set<Storage> storage = new HashSet<Storage>();

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

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Set<RAM> getRam() {
        return ram;
    }

    public void setRam(Set<RAM> ram) {
        this.ram = ram;
    }

    public Set<Storage> getStorage() {
        return storage;
    }

    public void setStorage(Set<Storage> storage) {
        this.storage = storage;
    }
}
