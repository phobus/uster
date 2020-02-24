package com.hiberus.uster.application.impl;

import com.hiberus.uster.application.VehicleService;
import com.hiberus.uster.domain.model.vehicle.Vehicle;
import com.hiberus.uster.domain.model.vehicle.VehicleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Optional<Vehicle> findById(Long vehicleId) {
        return vehicleRepository.findById(vehicleId);
    }

    @Override
    public Page<Vehicle> findAll(Pageable pageable) {
        return vehicleRepository.findAll(pageable);
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public void deleteById(Long vehicleId) {
        vehicleRepository.deleteById(vehicleId);
    }

    @Override
    public List<Vehicle> findFreeVehiclesByDate(LocalDate date) {
        return vehicleRepository.findFindFreeVehiclesAtDate(date);
    }
}
