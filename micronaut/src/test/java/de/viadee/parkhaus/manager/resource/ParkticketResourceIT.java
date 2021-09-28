package de.viadee.parkhaus.manager.resource;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class ParkticketResourceIT {


   @Inject
   EmbeddedServer server;

   @Inject
   @Client("/")
   HttpClient client;

   /*
    * Integrationstest
    */
   @Test
   public void createIT(){
      HttpResponse<String> response =  client.toBlocking().exchange(HttpRequest.POST("/parkticket?entered=2020-01-31T18:00", ""));
      assertEquals(HttpStatus.OK, response.getStatus());
   }

}
