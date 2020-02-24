package com.hiberus.uster.application.impl;

import com.hiberus.uster.application.TripService;
import com.hiberus.uster.domain.model.driver.Driver;
import com.hiberus.uster.domain.model.driver.DriverRepository;
import com.hiberus.uster.domain.model.trip.DateIsNullException;
import com.hiberus.uster.domain.model.trip.Trip;
import com.hiberus.uster.domain.model.trip.TripRepository;
import com.hiberus.uster.domain.model.trip.VehicleNotIsFreeException;
import com.hiberus.uster.domain.model.vehicle.Vehicle;
import com.hiberus.uster.domain.model.vehicle.VehicleRepository;
import com.hiberus.uster.domain.share.DomainException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;

    public TripServiceImpl(TripRepository tripRepository, DriverRepository driverRepository, VehicleRepository vehicleRepository) {
        this.tripRepository = tripRepository;
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Page<Trip> findAll(Pageable pageable) {
        return tripRepository.findAll(pageable);
    }

    @Override
    public Trip save(Trip trip) throws DomainException {
        validateDate(trip);
        Vehicle vehicle = getFreeVehicle(trip);
        Driver driver = getFreeDriver(trip);
        trip.setVehicle(vehicle);
        trip.setDriver(driver);
        return tripRepository.save(trip);
    }

    protected void validateDate(Trip trip) throws DateIsNullException {
        Optional.ofNullable(trip)
                .map(Trip::getDate)
                .orElseThrow(DateIsNullException::new);
    }

    protected Driver getFreeDriver(Trip trip) throws VehicleNotIsFreeException {
        return driverRepository.findFreeDriversByDateAndLicenseAndId(
                trip.getDate(),
                trip.getVehicle().getLicenseRequired(),
                trip.getDriver().getId()
        ).orElseThrow(VehicleNotIsFreeException::new);
    }

    protected Vehicle getFreeVehicle(Trip trip) throws VehicleNotIsFreeException {
        return vehicleRepository.findFindFreeVehiclesAtDateById(
                trip.getDate(),
                trip.getVehicle().getId()
        ).orElseThrow(VehicleNotIsFreeException::new);
    }


}
