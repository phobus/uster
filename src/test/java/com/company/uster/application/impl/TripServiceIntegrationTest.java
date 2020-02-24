package com.company.uster.application.impl;


import com.company.uster.domain.model.trip.DateIsNullException;
import com.company.uster.domain.model.trip.Trip;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TripServiceIntegrationTest {

    @Autowired
    protected TripServiceImpl tripService;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test(expected = DateIsNullException.class)
    public void when_tripIsNull_Expect_DateIsNullException() throws DateIsNullException {
        Trip trip = null;

        tripService.validateDate(trip);

        Assert.assertNull(trip);
    }

    @Test(expected = DateIsNullException.class)
    public  void when_tripDateIsNull_Expect_DateIsNullException() throws DateIsNullException {
        Trip trip = new Trip();
        trip.setDate(null);

        tripService.validateDate(trip);

        Assert.assertNull(trip.getDate());
    }
}