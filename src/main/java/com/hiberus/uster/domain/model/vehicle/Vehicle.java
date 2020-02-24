package com.hiberus.uster.domain.model.vehicle;

import com.hiberus.uster.domain.share.BaseEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Vehicle extends BaseEntity {

    private String brand;
    private String model;

    @NotBlank
    private String plate;

    @Size(max = 1)
    @NotBlank
    private String licenseRequired;

    public Vehicle() {
    }

    public Vehicle(Long id, String brand, String model, String plate, String licenseRequired) {
        setId(id);
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.licenseRequired = licenseRequired;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getLicenseRequired() {
        return licenseRequired;
    }

    public void setLicenseRequired(String licenseRequired) {
        this.licenseRequired = licenseRequired;
    }

}
