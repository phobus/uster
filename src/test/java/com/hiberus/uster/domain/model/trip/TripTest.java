package com.hiberus.uster.domain.model.trip;

import com.hiberus.uster.domain.model.DomainTestKit;
import com.hiberus.uster.domain.model.driver.Driver;
import com.hiberus.uster.domain.model.vehicle.Vehicle;
import org.junit.Assert;
import org.junit.Test;

class TripTest {

    @Test
    void when_ConstructorWithoutArguments_Expect_ValuesAreEmpty() {
        Trip trip = new Trip();

        Assert.assertNull(trip.getId());
        Assert.assertNull(trip.getDate());
        Assert.assertNull(trip.getVehicle());
        Assert.assertNull(trip.getDriver());
    }

    @Test
    void when_ConstructorWithAllArguments_Expect_ValuesAreNotEmpty() {
        Driver driver = DomainTestKit.buildDriver(DomainTestKit.DRIVER_ID_0);
        Vehicle vehicle = DomainTestKit.buildVehicle(DomainTestKit.VEHICLE_ID_0);

        Trip trip = DomainTestKit.buildTrip(driver, vehicle);

        Assert.assertEquals(DomainTestKit.TRIP_ID, trip.getId());
        Assert.assertEquals(driver, trip.getDate());
        Assert.assertEquals(vehicle, trip.getVehicle());
        Assert.assertEquals(DomainTestKit.TRIP_DATE, trip.getDriver());
    }
}