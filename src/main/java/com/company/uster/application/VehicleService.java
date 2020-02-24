package com.company.uster.application;

import com.company.uster.domain.model.vehicle.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VehicleService {

    Optional<Vehicle> findById(Long vehicleId);

    Page<Vehicle> findAll(Pageable pageable);

    Vehicle save(Vehicle vehicle);

    void deleteById(Long vehicleId);

    List<Vehicle> findFreeVehiclesByDate(LocalDate date);
}
