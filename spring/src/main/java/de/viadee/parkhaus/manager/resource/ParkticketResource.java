package de.viadee.parkhaus.manager.resource;

import de.viadee.parkhaus.manager.entity.Parkticket;
import de.viadee.parkhaus.manager.service.ParkticketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;

@RestController
@RequestMapping("/parkticket")
public class ParkticketResource {

    private ParkticketService parkticketService;

    @Autowired
    public ParkticketResource(ParkticketService parkticketService) {
        this.parkticketService = parkticketService;
    }

    @PostMapping
    public String create(@RequestParam("entered") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime entered) {
        return parkticketService.create(entered);
    }

    @GetMapping
    public Parkticket get(String id) {
        return parkticketService.get(id);
    }

   @GetMapping(path = "{id}/getPaymentAmount")
    public Double getPaymentAmount(@PathVariable("id") String id) {
        return parkticketService.getPaymentAmount(id);
    }

    @PutMapping(value = "{id}/makePayment", consumes = MediaType.TEXT_PLAIN_VALUE)
    public Boolean makePayment(@RequestBody String payment, @PathVariable String id) {
        return parkticketService.makePayment(payment, id);
    }

    @GetMapping(path = "getAll")
    public Collection<Parkticket> getAll() {
        return parkticketService.getAll();
    }

    @GetMapping(path = "/{id}/isAllowedToExit")
    public Boolean isAllowedToExit(@PathVariable("id") String id) {
        return parkticketService.isAllowedToExit(id);
    }
}
