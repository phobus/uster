package com.hiberus.uster.application;

import com.hiberus.uster.domain.model.trip.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TripService {

    Page<Trip> findAll(Pageable pageable);

    Trip save(Trip trip) throws Exception;
}
