package com.company.uster.domain.model.vehicle;

import com.company.uster.domain.model.DomainTestKit;
import org.junit.Assert;
import org.junit.Test;

class VehicleTest {

    @Test
    void when_ConstructorWithoutArguments_Expect_ValuesAreEmpty() {
        Vehicle vehicle = new Vehicle();

        Assert.assertNull(vehicle.getId());
        Assert.assertNull(vehicle.getBrand());
        Assert.assertNull(vehicle.getModel());
        Assert.assertNull(vehicle.getPlate());
        Assert.assertNull(vehicle.getLicenseRequired());

    }

    @Test
    void when_ConstructorWithAllArguments_Expect_ValuesAreNotEmpty() {
        Vehicle vehicle = DomainTestKit.buildVehicle(DomainTestKit.DRIVER_ID_0);

        DomainTestKit.assertEqualsVehicle0(vehicle);
    }
}