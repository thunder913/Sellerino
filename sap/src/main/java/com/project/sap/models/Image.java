package com.project.sap.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="Image")
public class Image {
    public Image(String url){
        this.setUrl(url);
    }

    public Image(){
        
    }
    @NotNull
    @Id
    @Column(unique = true, name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="url", nullable = false, length = 1027)
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
