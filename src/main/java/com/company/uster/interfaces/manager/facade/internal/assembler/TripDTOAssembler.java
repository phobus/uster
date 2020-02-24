package com.company.uster.interfaces.manager.facade.internal.assembler;

import com.company.uster.domain.model.trip.Trip;
import com.company.uster.interfaces.manager.facade.dto.TripDTO;

public class TripDTOAssembler extends BaseAssembler<Trip, TripDTO> {

    @Override
    public Trip fromDto(TripDTO tripDTO) {
        final DriverDTOAssembler driverAssembler = new DriverDTOAssembler();
        final VehicleDTOAssembler vehicleAssembler = new VehicleDTOAssembler();
        final Trip entity = new Trip(
                tripDTO.getId(),
                driverAssembler.fromDto(tripDTO.getDriver()),
                vehicleAssembler.fromDto(tripDTO.getVehicle()),
                tripDTO.getDate()
        );
        return entity;
    }

    @Override
    public TripDTO toDto(Trip entity) {
        final DriverDTOAssembler driverAssembler = new DriverDTOAssembler();
        final VehicleDTOAssembler vehicleAssembler = new VehicleDTOAssembler();
        final TripDTO dto = new TripDTO(
                entity.getId(),
                driverAssembler.toDto(entity.getDriver()),
                vehicleAssembler.toDto(entity.getVehicle()),
                entity.getDate()
        );
        return dto;
    }
}
