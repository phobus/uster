package com.hiberus.uster.interfaces.manager.facade.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class VehicleDTO {

    private Long id;
    private String brand;
    private String model;

    @NotBlank
    private String plate;

    @Size(max = 1)
    @NotBlank
    private String licenseRequired;

    public VehicleDTO() {
    }

    public VehicleDTO(Long id, String brand, String model, String plate, String licenseRequired) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.licenseRequired = licenseRequired;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getBranModel() {
        return brand + ' ' + model;
    }
}
