package com.company.uster.domain.model;

import com.company.uster.application.util.SampleDataGenerator;
import com.company.uster.domain.model.driver.Driver;
import com.company.uster.domain.model.trip.Trip;
import com.company.uster.domain.model.vehicle.Vehicle;
import com.company.uster.domain.share.BaseEntity;
import org.junit.Assert;

import java.time.LocalDate;
import java.util.List;

public class DomainTestKit {

    public final static int AMOUNT_OF_FREE_DRIVERS =
            SampleDataGenerator.AMOUNT_OF_DRIVERS / SampleDataGenerator.EXAMPLE_LICENSES.length();

    public final static int AMOUNT_OF_FREE_VEHICLES = SampleDataGenerator.AMOUNT_OF_VEHICLES;

    public static final String LICENSE_A = "A";
    public static final String LICENSE_B = "B";
    public static final String LICENSE_C = "C";

    public static final Long DRIVER_ID_0 = 1L;
    public static final String DRIVER_NAME_0 = "Name0";
    public static final String DRIVER_SURNAME_0 = "Surname0";

    public static final Long DRIVER_ID_1 = 2L;
    public static final String DRIVER_NAME_1 = "Name1";
    public static final String DRIVER_SURNAME_1 = "Surname1";

    public static final Long DRIVER_ID_2 = 3L;
    public static final String DRIVER_NAME_2 = "Name2";
    public static final String DRIVER_SURNAME_2 = "Surname2";

    public static final Long DRIVER_ID_3 = 4L;
    public static final String DRIVER_NAME_3 = "Name3";
    public static final String DRIVER_SURNAME_3 = "Surname3";

    public static final Long DRIVER_ID_4 = 5L;
    public static final String DRIVER_NAME_4 = "Name4";
    public static final String DRIVER_SURNAME_4 = "Surname4";

    public static final Long DRIVER_ID_5 = 6L;
    public static final String DRIVER_NAME_5 = "Name5";
    public static final String DRIVER_SURNAME_5 = "Surname5";

    public static final Long VEHICLE_ID_0 = 7L;
    public static final String VEHICLE_BRAND_0 = "brand0";
    public static final String VEHICLE_MODEL_0 = "model0";
    public static final String VEHICLE_PLATE_0 = "A-0000";

    public static final Long VEHICLE_ID_1 = 8L;
    public static final String VEHICLE_BRAND_1 = "brand1";
    public static final String VEHICLE_MODEL_1 = "model1";
    public static final String VEHICLE_PLATE_1 = "B-1111";

    public static final Long VEHICLE_ID_2 = 9L;
    public static final String VEHICLE_BRAND_2 = "brand2";
    public static final String VEHICLE_MODEL_2 = "model2";
    public static final String VEHICLE_PLATE_2 = "C-2222";

    public static final Long TRIP_ID = 10L;
    public static final LocalDate TRIP_DATE = LocalDate.of(2019, 12, 31);

    public static Driver buildDriver(Long driverId) {
        Driver driver = null;
        switch (driverId.intValue()) {
            case 0:
                driver = new Driver(DRIVER_ID_0, DRIVER_NAME_0, DRIVER_SURNAME_0, LICENSE_A);
            case 1:
                driver = new Driver(DRIVER_ID_1, DRIVER_NAME_1, DRIVER_SURNAME_1, LICENSE_B);
            case 2:
                driver = new Driver(DRIVER_ID_2, DRIVER_NAME_2, DRIVER_SURNAME_2, LICENSE_C);
            case 3:
                driver = new Driver(DRIVER_ID_3, DRIVER_NAME_3, DRIVER_SURNAME_3, LICENSE_A);
            case 4:
                driver = new Driver(DRIVER_ID_4, DRIVER_NAME_4, DRIVER_SURNAME_4, LICENSE_B);
            case 5:
                driver = new Driver(DRIVER_ID_5, DRIVER_NAME_5, DRIVER_SURNAME_5, LICENSE_C);
        }
        return driver;
    }

    public static Vehicle buildVehicle(Long vehicleIdA) {
        Vehicle vehicle = null;
        switch (vehicleIdA.intValue()) {
            case 6:
                vehicle = new Vehicle(VEHICLE_ID_0, VEHICLE_BRAND_0, VEHICLE_MODEL_0, VEHICLE_PLATE_0, LICENSE_A);
            case 7:
                vehicle = new Vehicle(VEHICLE_ID_1, VEHICLE_BRAND_1, VEHICLE_MODEL_1, VEHICLE_PLATE_1, LICENSE_B);
            case 8:
                vehicle = new Vehicle(VEHICLE_ID_2, VEHICLE_BRAND_2, VEHICLE_MODEL_2, VEHICLE_PLATE_2, LICENSE_C);
        }
        return vehicle;
    }

    public static Trip buildTrip(Driver driver, Vehicle vehicle) {
        return new Trip(TRIP_ID, driver, vehicle, TRIP_DATE);
    }

    public static <T extends BaseEntity> T findById(List<T> entities, Long id) {
//        System.out.println("entities.get(0).getId(): " + entities.get(0).getId());
//        System.out.println("id: " + id);
        return entities.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public static void assertEqualsDriver0(Driver driver) {
        Assert.assertEquals(DRIVER_ID_0, driver.getId());
        Assert.assertEquals(DRIVER_NAME_0, driver.getName());
        Assert.assertEquals(DRIVER_SURNAME_0, driver.getSurname());
        Assert.assertEquals(LICENSE_A, driver.getLicense());
    }

    public static void assertEqualsDriver3(Driver driver) {
        Assert.assertEquals(DRIVER_ID_3, driver.getId());
        Assert.assertEquals(DRIVER_NAME_3, driver.getName());
        Assert.assertEquals(DRIVER_SURNAME_3, driver.getSurname());
        Assert.assertEquals(LICENSE_A, driver.getLicense());
    }

    public static void assertEqualsVehicle0(Vehicle vehicle) {
        Assert.assertEquals(VEHICLE_ID_0, vehicle.getId());
        Assert.assertEquals(VEHICLE_BRAND_0, vehicle.getBrand());
        Assert.assertEquals(VEHICLE_MODEL_0, vehicle.getModel());
        Assert.assertEquals(VEHICLE_PLATE_0, vehicle.getPlate());
        Assert.assertEquals(LICENSE_A, vehicle.getLicenseRequired());
    }

    public static void assertEqualsVehicle1(Vehicle vehicle) {
        Assert.assertEquals(VEHICLE_ID_1, vehicle.getId());
        Assert.assertEquals(VEHICLE_BRAND_1, vehicle.getBrand());
        Assert.assertEquals(VEHICLE_MODEL_1, vehicle.getModel());
        Assert.assertEquals(VEHICLE_PLATE_1, vehicle.getPlate());
        Assert.assertEquals(LICENSE_B, vehicle.getLicenseRequired());
    }

    public static void assertEqualsVehicle2(Vehicle vehicle) {
        Assert.assertEquals(VEHICLE_ID_2, vehicle.getId());
        Assert.assertEquals(VEHICLE_BRAND_2, vehicle.getBrand());
        Assert.assertEquals(VEHICLE_MODEL_2, vehicle.getModel());
        Assert.assertEquals(VEHICLE_PLATE_2, vehicle.getPlate());
        Assert.assertEquals(LICENSE_C, vehicle.getLicenseRequired());
    }
}
