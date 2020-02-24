package com.hiberus.uster.domain.model.driver;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends PagingAndSortingRepository<Driver, Long> {

    @Query(nativeQuery = true,
            value = "select d.* from driver d where d.license = ?2 " +
                    "minus " +
                    "select d.* from driver d " +
                    "inner join trip t on d.id = t.driver_id " +
                    "where t.date = ?1")
    List<Driver> findFreeDriversByDateAndLicense(LocalDate date, String license);

    @Query(nativeQuery = true,
            value = "select d.* from driver d where d.license = ?2 and d.id = ?3 " +
                    "minus " +
                    "select d.* from driver d " +
                    "inner join trip t on d.id = t.driver_id " +
                    "where t.date = ?1")
    Optional<Driver> findFreeDriversByDateAndLicenseAndId(LocalDate date, String license, Long driverId);
}
