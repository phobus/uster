package com.hiberus.uster.domain.model.vehicle;

import com.hiberus.uster.application.util.SampleDataGenerator;
import com.hiberus.uster.domain.model.DomainTestKit;
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
public class VehicleRepositoryIntegrationTest {

    @Autowired
    private VehicleRepository vehicleRepository;

//    @Autowired
//    private SampleDataGenerator sampleDataGenerator;

    @Before
    public void setup() {
//        sampleDataGenerator.generate();
    }

    @Test
    public void when_findFreeDriver_Expect_Result() {
        List<Vehicle> vehicles = vehicleRepository.findFindFreeVehiclesAtDate(
                DomainTestKit.TRIP_DATE
        );
        Vehicle vehicle0 = DomainTestKit.findById(vehicles, DomainTestKit.VEHICLE_ID_0);
        Vehicle vehicle1 = DomainTestKit.findById(vehicles, DomainTestKit.VEHICLE_ID_1);
        Vehicle vehicle2 = DomainTestKit.findById(vehicles, DomainTestKit.VEHICLE_ID_2);

        Assert.assertEquals(DomainTestKit.AMOUNT_OF_FREE_VEHICLES, vehicles.size());
        DomainTestKit.assertEqualsVehicle0(vehicle0);
        DomainTestKit.assertEqualsVehicle1(vehicle1);
        DomainTestKit.assertEqualsVehicle2(vehicle2);
    }
}