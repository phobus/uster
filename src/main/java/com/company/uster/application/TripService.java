package com.company.uster.application;

import com.company.uster.domain.model.trip.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TripService {

    Page<Trip> findAll(Pageable pageable);

    Trip save(Trip trip) throws Exception;
}
