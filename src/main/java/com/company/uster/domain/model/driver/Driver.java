package com.company.uster.domain.model.driver;

import com.company.uster.domain.share.BaseEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Driver extends BaseEntity {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @Size(max = 1)
    @NotBlank
    private String license;

    public Driver() {
    }

    public Driver(Long id, String name, String surname, String license) {
        setId(id);
        this.name = name;
        this.surname = surname;
        this.license = license;
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

}
