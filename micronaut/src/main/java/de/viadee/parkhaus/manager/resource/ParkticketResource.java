package de.viadee.parkhaus.manager.resource;

import de.viadee.parkhaus.manager.entity.Parkticket;
import de.viadee.parkhaus.manager.service.ParkticketService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller("/parkticket")
public class ParkticketResource {

    private ParkticketService parkticketService;

    public ParkticketResource(ParkticketService parkticketService) {
        this.parkticketService = parkticketService;
    }

    @Post(produces = MediaType.TEXT_PLAIN)
    public String create(@QueryValue("entered") LocalDateTime entered) {

        return parkticketService.create(entered);
    }

    @Get(produces = MediaType.APPLICATION_JSON)
    public Parkticket get(String id) {
        return parkticketService.get(id);
    }

    @Get(value = "{id}/getPaymentAmount", produces = MediaType.APPLICATION_JSON)
    public Double getPaymentAmount(@PathVariable("id") String id) {
        return parkticketService.getPaymentAmount(id);
    }

    @Put(value = "{id}/makePayment", consumes = MediaType.TEXT_PLAIN)
    public Boolean makePayment(@PathVariable("id") String id, @Body Double payment) {
        return parkticketService.makePayment(id, payment);
    }

    @Get(value = "getAll", produces = MediaType.APPLICATION_JSON)
    public List<Parkticket> getAll() {
        return parkticketService.getAll();
    }

    @Get(value = "{id}/isAllowedToExit", produces = MediaType.APPLICATION_JSON)
    public boolean isAllowedToExit(@PathVariable("id") String id) {
       return parkticketService.isAllowedToExit(id);
    }


}
