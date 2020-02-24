package com.company.uster.domain.model.vehicle;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends PagingAndSortingRepository<Vehicle, Long> {

    @Query(nativeQuery = true,
            value = "select v.* from vehicle v " +
                    "minus " +
                    "select v.* from vehicle v " +
                    "inner join trip t on v.id = t.vehicle_id " +
                    "where t.date = ?1")
    List<Vehicle> findFindFreeVehiclesAtDate(LocalDate date);

    @Query(nativeQuery = true,
            value = "select v.* from vehicle v where v.id = ?2 " +
                    "minus " +
                    "select v.* from vehicle v " +
                    "inner join trip t on v.id = t.vehicle_id " +
                    "where t.date = ?1")
    Optional<Vehicle> findFindFreeVehiclesAtDateById(LocalDate date, Long vehicleId);
}
