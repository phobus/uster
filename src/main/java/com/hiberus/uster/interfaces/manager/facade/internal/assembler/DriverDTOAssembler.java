package com.hiberus.uster.interfaces.manager.facade.internal.assembler;

import com.hiberus.uster.domain.model.driver.Driver;
import com.hiberus.uster.interfaces.manager.facade.dto.DriverDTO;

public class DriverDTOAssembler extends BaseAssembler<Driver, DriverDTO> {

    @Override
    public Driver fromDto(DriverDTO driverDTO) {
        final Driver entity = new Driver(
                driverDTO.getId(),
                driverDTO.getName(),
                driverDTO.getSurname(),
                driverDTO.getLicense()
        );
        return entity;
    }

    @Override
    public DriverDTO toDto(Driver entity) {
        final DriverDTO dto = new DriverDTO(
                entity.getId(),
                entity.getName(),
                entity.getSurname(),
                entity.getLicense()
        );
        return dto;
    }
}
