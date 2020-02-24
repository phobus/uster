package com.hiberus.uster.interfaces.manager.facade.internal;

import com.hiberus.uster.application.DriverService;
import com.hiberus.uster.application.TripService;
import com.hiberus.uster.application.VehicleService;
import com.hiberus.uster.domain.model.driver.Driver;
import com.hiberus.uster.domain.model.trip.Trip;
import com.hiberus.uster.domain.model.vehicle.Vehicle;
import com.hiberus.uster.interfaces.manager.facade.ManagerServiceFacade;
import com.hiberus.uster.interfaces.manager.facade.dto.DriverDTO;
import com.hiberus.uster.interfaces.manager.facade.dto.TripDTO;
import com.hiberus.uster.interfaces.manager.facade.dto.VehicleDTO;
import com.hiberus.uster.interfaces.manager.facade.internal.assembler.DriverDTOAssembler;
import com.hiberus.uster.interfaces.manager.facade.internal.assembler.TripDTOAssembler;
import com.hiberus.uster.interfaces.manager.facade.internal.assembler.VehicleDTOAssembler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ManagerServiceFacadeImpl implements ManagerServiceFacade {

    private final DriverService driverService;
    private final VehicleService vehicleService;
    private final TripService tripService;

    public ManagerServiceFacadeImpl(DriverService driverService, VehicleService vehicleService, TripService tripService) {
        this.driverService = driverService;
        this.vehicleService = vehicleService;
        this.tripService = tripService;
    }

    @Override
    public Page<DriverDTO> driverFindAll(Pageable pageable) {
        final Page<Driver> driverPage = driverService.findAll(pageable);
        final DriverDTOAssembler assembler = new DriverDTOAssembler();
        return assembler.toDtoPage(driverPage);
    }

    @Override
    public DriverDTO driverSave(DriverDTO driverDTO) {
        final DriverDTOAssembler assembler = new DriverDTOAssembler();
        Driver driver = assembler.fromDto(driverDTO);
        driverService.save(driver);
        driverDTO.setId(driver.getId());
        return driverDTO;
    }

    @Override
    public DriverDTO driverFindById(Long driverId) {
        Driver driver = driverService.findById(driverId).get();
        final DriverDTOAssembler assembler = new DriverDTOAssembler();
        return assembler.toDto(driver);
    }

    @Override
    public void driverDeleteById(Long driverId) {
        driverService.deleteById(driverId);
    }

    @Override
    public Page<VehicleDTO> vehicleFindAll(Pageable pageable) {
        final Page<Vehicle> driverPage = vehicleService.findAll(pageable);
        final VehicleDTOAssembler assembler = new VehicleDTOAssembler();
        return assembler.toDtoPage(driverPage);
    }

    @Override
    public VehicleDTO vehicleSave(VehicleDTO vehicleDTO) {
        final VehicleDTOAssembler assembler = new VehicleDTOAssembler();
        Vehicle vehicle = assembler.fromDto(vehicleDTO);
        vehicleService.save(vehicle);
        vehicleDTO.setId(vehicle.getId());
        return vehicleDTO;
    }

    @Override
    public VehicleDTO vehicleFindById(Long vehicleId) {
        Vehicle vehicle = vehicleService.findById(vehicleId).get();
        final VehicleDTOAssembler assembler = new VehicleDTOAssembler();
        return assembler.toDto(vehicle);
    }

    @Override
    public void vehicleDeleteById(Long vehicleId) {
        vehicleService.deleteById(vehicleId);
    }

    @Override
    public Page<TripDTO> tripFindAll(Pageable pageable) {
        final Page<Trip> tripPage = tripService.findAll(pageable);
        final TripDTOAssembler assembler = new TripDTOAssembler();
        return assembler.toDtoPage(tripPage);
    }

    @Override
    public TripDTO tripSave(TripDTO tripDTO) throws Exception {
        final TripDTOAssembler assembler = new TripDTOAssembler();
        Trip trip = assembler.fromDto(tripDTO);
        tripService.save(trip);
        tripDTO.setId(trip.getId());
        return tripDTO;
    }

    @Override
    public List<VehicleDTO> findFreeVehiclesByDate(LocalDate date) {
        final List<Vehicle> vehicles = vehicleService.findFreeVehiclesByDate(date);
        final VehicleDTOAssembler assembler = new VehicleDTOAssembler();
        return assembler.toDtoList(vehicles);
    }

    @Override
    public List<DriverDTO> findFreeDriversByDateAndLicense(LocalDate date, String license) {
        final List<Driver> drivers = driverService.findFreeDriversByDateAndLicense(date, license);
        final DriverDTOAssembler assembler = new DriverDTOAssembler();
        return assembler.toDtoList(drivers);
    }
}
