package com.company.uster.domain.model.driver;

import com.company.uster.domain.model.DomainTestKit;
import org.junit.Assert;
import org.junit.Test;

class DriverTest {

    @Test
    void when_ConstructorWithoutArguments_Expect_ValuesAreEmpty() {
        Driver driver = new Driver();

        Assert.assertNull(driver.getId());
        Assert.assertNull(driver.getName());
        Assert.assertNull(driver.getSurname());
        Assert.assertNull(driver.getLicense());
    }

    @Test
    void when_ConstructorWithAllArguments_Expect_ValuesAreNotEmpty() {
        Driver driver = DomainTestKit.buildDriver(DomainTestKit.DRIVER_ID_0);

        DomainTestKit.assertEqualsDriver0(driver);
    }
}