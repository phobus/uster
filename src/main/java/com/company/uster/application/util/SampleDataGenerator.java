package com.company.uster.application.util;

import com.company.uster.domain.model.driver.Driver;
import com.company.uster.domain.model.trip.TripRepository;
import com.company.uster.domain.model.driver.DriverRepository;
import com.company.uster.domain.model.vehicle.Vehicle;
import com.company.uster.domain.model.vehicle.VehicleRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class SampleDataGenerator {

    private final Log logger = LogFactory.getLog(getClass());

    public static final String EXAMPLE_LICENSES = "ABC";

    public static final int AMOUNT_OF_DRIVERS = 6;
    public static final int AMOUNT_OF_VEHICLES = 3;

    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final TripRepository tripRepository;

    public SampleDataGenerator(DriverRepository driverRepository, VehicleRepository vehicleRepository, TripRepository tripRepository) {
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
        this.tripRepository = tripRepository;
    }

    public void generate() {
        long vehicleCount = vehicleRepository.count();
        long driverCount = driverRepository.count();
        if (vehicleCount != 0 || driverCount != 0) {
            logger.info("No sample data required");
            return;
        }

        populateDrivers();
        populateVehicles();
    }

    private void populateDrivers() {
        for (int i = 0; i < AMOUNT_OF_DRIVERS; i++) {
            int index = i % EXAMPLE_LICENSES.length();
            final Driver driver = new Driver();
            driver.setName("Name" + i);
            driver.setSurname("Surname" + i);
            driver.setLicense(EXAMPLE_LICENSES.substring(index, index + 1));

            driverRepository.save(driver);
        }
        logger.info("Populate drivers");
    }

    private void populateVehicles() {
        for (int i = 0; i < AMOUNT_OF_VEHICLES; i++) {
            int index = i % EXAMPLE_LICENSES.length();
            final Vehicle vehicle = new Vehicle();
            vehicle.setBrand("brand" + i);
            vehicle.setModel("model" + i);
            vehicle.setLicenseRequired(EXAMPLE_LICENSES.substring(index, index + 1));
            vehicle.setPlate(vehicle.getLicenseRequired() + "-" + i + i + i + i);

            vehicleRepository.save(vehicle);
        }
        logger.info("Populate vehicles");
    }
}
