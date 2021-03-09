package de.viadee.parkhaus.manager.service;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
public class ParkticketServiceTest {

    @Autowired
    ParkticketService parkticketService;

    @Test
    public void createTest() {
        assertNotNull(parkticketService.create(LocalDateTime.now()));
    }

}