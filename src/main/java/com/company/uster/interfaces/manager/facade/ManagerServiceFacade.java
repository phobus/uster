package com.company.uster.interfaces.manager.facade;

import com.company.uster.interfaces.manager.facade.dto.VehicleDTO;
import com.company.uster.interfaces.manager.facade.dto.DriverDTO;
import com.company.uster.interfaces.manager.facade.dto.TripDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ManagerServiceFacade {

    Page<DriverDTO> driverFindAll(Pageable pageable);

    DriverDTO driverSave(DriverDTO driver);

    DriverDTO driverFindById(Long driverId);

    void driverDeleteById(Long driverId);

    Page<VehicleDTO> vehicleFindAll(Pageable pageable);

    VehicleDTO vehicleSave(VehicleDTO vehicleDTO);

    VehicleDTO vehicleFindById(Long vehicleId);

    void vehicleDeleteById(Long vehicleId);

    Page<TripDTO> tripFindAll(Pageable pageable);

    TripDTO tripSave(TripDTO tripDTO) throws Exception;

    List<VehicleDTO> findFreeVehiclesByDate(LocalDate date);

    List<DriverDTO> findFreeDriversByDateAndLicense(LocalDate date, String license);
}
