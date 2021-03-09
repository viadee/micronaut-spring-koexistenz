package de.viadee.parkhaus.manager.service;

import de.viadee.parkhaus.manager.config.ParkhausConfig;
import de.viadee.parkhaus.manager.entity.Parkticket;
import de.viadee.parkhaus.manager.repository.ParkticketRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.NoSuchElementException;

@Component
public class ParkticketService {

    private ParkticketRepository parkticketRepository;

    private ParkhausConfig parkhausConfig;

    public ParkticketService(ParkticketRepository ParkticketRepository, ParkhausConfig parkhausConfig) {
        this.parkticketRepository = ParkticketRepository;
        this.parkhausConfig = parkhausConfig;
    }

    public String create(LocalDateTime entered) {

        Parkticket parkticket = new Parkticket(entered);

        parkticketRepository.save(parkticket);

        return parkticket.getId();
    }

    public Parkticket get(String id) {
        return parkticketRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Double getPaymentAmount(String id) {
        Parkticket parkticket = parkticketRepository.findById(id).orElseThrow(NoSuchElementException::new);

        return getPaymentAmount(parkticket);
    }

    private Double getPaymentAmount(Parkticket parkticket) {
        LocalDateTime to = LocalDateTime.now();

        LocalDateTime from = parkticket.getEntered();

        long parkingTime = ChronoUnit.HOURS.between(from, to);

        return parkhausConfig.getGebuehr() * parkingTime;
    }

    public Boolean makePayment(String payment, String id) {
        Parkticket parkticket = parkticketRepository.findById(id).orElseThrow(NoSuchElementException::new);

        if (getPaymentAmount(parkticket).equals(Double.valueOf(payment))) {
            parkticket.setPayment(LocalDateTime.now());
            parkticketRepository.save(parkticket);
            return true;
        } else {
            return false;
        }

    }

    public Collection<Parkticket> getAll() {
        return parkticketRepository.findAll();
    }

    public Boolean isAllowedToExit(String id) {
        Parkticket parkticket = parkticketRepository.findById(id).orElseThrow(NoSuchElementException::new);

        LocalDateTime now = LocalDateTime.now();
        return parkticket.getEntered().isBefore(now)
                && parkticket.getPayment() != null
                && now.minusMinutes(parkhausConfig.getToleranceBtwPaymentAndExit()).isBefore(parkticket.getPayment());
    }

}
