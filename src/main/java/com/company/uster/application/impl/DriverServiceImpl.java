package com.company.uster.application.impl;

import com.company.uster.application.DriverService;
import com.company.uster.domain.model.driver.Driver;
import com.company.uster.domain.model.driver.DriverRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public Optional<Driver> findById(Long driverId) {
        return driverRepository.findById(driverId);
    }

    @Override
    public Page<Driver> findAll(Pageable pageable) {
        return driverRepository.findAll(pageable);
    }

    @Override
    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public void deleteById(Long driverId) {
        driverRepository.deleteById(driverId);
    }

    @Override
    public List<Driver> findFreeDriversByDateAndLicense(LocalDate date, String license) {
        return driverRepository.findFreeDriversByDateAndLicense(date, license);
    }
}
