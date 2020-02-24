package com.company.uster.interfaces.manager.facade.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DriverDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @Size(max = 1)
    @NotBlank
    private String license;

    public DriverDTO() {
    }

    public DriverDTO(Long id, String name, String surname, String license) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.license = license;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getFullName() {
        return name + ' ' + surname;
    }
}
