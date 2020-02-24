package com.company.uster.domain.model.driver;

import com.company.uster.domain.model.trip.TripRepository;
import com.company.uster.domain.model.DomainTestKit;
import com.company.uster.domain.model.vehicle.VehicleRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DriverRepositoryIntegrationTest {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private TripRepository tripRepository;

    @Before
    public void setup() {
    }

    @Test
    public void when_findFreeDriver_Expect_Result() {
        List<Driver> drivers = driverRepository.findFreeDriversByDateAndLicense(
                DomainTestKit.TRIP_DATE, DomainTestKit.LICENSE_A
        );
        Driver driver0 = DomainTestKit.findById(drivers, DomainTestKit.DRIVER_ID_0);
        Driver driver3 = DomainTestKit.findById(drivers, DomainTestKit.DRIVER_ID_3);

        Assert.assertEquals(DomainTestKit.AMOUNT_OF_FREE_DRIVERS, drivers.size());
        DomainTestKit.assertEqualsDriver0(driver0);
        DomainTestKit.assertEqualsDriver3(driver3);
    }

//    @Test
//    public void when_saveTripAndFindFreeDriver_Expect_Result() {
//        Driver driver = driverRepository.findById(DomainTestKit.DRIVER_ID_0).get();
//        Vehicle vehicle = vehicleRepository.findById(DomainTestKit.VEHICLE_ID_0).get();
//        Trip trip = new Trip(null, driver, vehicle, DomainTestKit.TRIP_DATE);
//        tripRepository.save(trip);
//
//        List<Driver> drivers = driverRepository.findFreeDriversByDateAndLicense(
//                DomainTestKit.TRIP_DATE, DomainTestKit.LICENSE_A
//        );
//
//        Driver driver3 = DomainTestKit.findById(drivers, DomainTestKit.DRIVER_ID_3);
//        Assert.assertEquals(DomainTestKit.AMOUNT_OF_FREE_DRIVERS - 1, drivers.size());
//        DomainTestKit.assertEqualsDriver3(driver3);
//    }
}