package com.project.sap.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="processor")
public class Processor {
        @NotNull
        @Id
        @Column(unique = true, name="Id")
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;

        @Column(name="manufacturer", nullable = false, length = 63)
        private String manufacturer;


        @Column(name="Name", nullable = false, length = 255)
        private String name;


        @Column(name="Model", nullable = false, length = 63)
        private String model;


        @Column(name="Speed", nullable = false)
        private double speed;


        @Column(name="Cores", nullable = false, columnDefinition = "TINYINT")
        private int cores;

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

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getModel() {
                return model;
        }

        public void setModel(String model) {
                this.model = model;
        }

        public double getSpeed() {
                return speed;
        }

        public void setSpeed(double speed) {
                this.speed = speed;
        }

        public int getCores() {
                return cores;
        }

        public void setCores(int cores) {
                this.cores = cores;
        }
}
