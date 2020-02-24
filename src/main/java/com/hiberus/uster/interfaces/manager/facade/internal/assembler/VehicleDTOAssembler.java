package com.hiberus.uster.interfaces.manager.facade.internal.assembler;

import com.hiberus.uster.domain.model.vehicle.Vehicle;
import com.hiberus.uster.interfaces.manager.facade.dto.VehicleDTO;

public class VehicleDTOAssembler extends BaseAssembler<Vehicle, VehicleDTO> {
    @Override
    public Vehicle fromDto(VehicleDTO vehicleDTO) {
        final Vehicle entity = new Vehicle(
                vehicleDTO.getId(),
                vehicleDTO.getBrand(),
                vehicleDTO.getModel(),
                vehicleDTO.getPlate(),
                vehicleDTO.getLicenseRequired()
        );
        return entity;
    }

    @Override
    public VehicleDTO toDto(Vehicle entity) {
        final VehicleDTO dto = new VehicleDTO(
                entity.getId(),
                entity.getBrand(),
                entity.getModel(),
                entity.getPlate(),
                entity.getLicenseRequired()
        );
        return dto;
    }
}
