package com.company.uster.interfaces.manager.facade.internal.assembler;

import com.company.uster.interfaces.manager.facade.dto.VehicleDTO;
import com.company.uster.domain.model.vehicle.Vehicle;

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
