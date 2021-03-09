package de.viadee.parkhaus.manager.service;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
public class ParkticketServiceTest {

   @Inject
   ParkticketService parkticketService;

   @Test
   public void createTest(){
         assertNotNull(parkticketService.create(LocalDateTime.now()));
   }

}