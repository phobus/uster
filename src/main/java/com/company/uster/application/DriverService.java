package com.company.uster.application;

import com.company.uster.domain.model.driver.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DriverService {

    Optional<Driver> findById(Long driverId);

    Page<Driver> findAll(Pageable pageable);

    Driver save(Driver driver);

    void deleteById(Long driverId);

    List<Driver> findFreeDriversByDateAndLicense(LocalDate date, String license);
}
