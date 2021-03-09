package de.viadee.parkhaus.manager.resource;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class ParkticketResourceIT {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    public void createIT() throws Exception {
        HttpResponse<String> response =  client.toBlocking().exchange(HttpRequest.POST("/parkticket?entered=2020-01-31T18:00", ""));
        assertEquals(HttpStatus.OK, response.getStatus());
    }

}