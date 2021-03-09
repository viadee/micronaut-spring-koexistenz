package de.viadee.parkhaus.manager.service;

import de.viadee.parkhaus.manager.config.ParkhausConfig;
import de.viadee.parkhaus.manager.entity.Parkticket;
import de.viadee.parkhaus.manager.repository.ParkticketRepository;

import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;

@Singleton
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
        return parkticketRepository.findById(id).orElse(null);
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

    public Boolean makePayment(String id, Double payment) {
        Parkticket parkticket = parkticketRepository.findById(id).orElseThrow(NoSuchElementException::new);

        if (parkticket != null && getPaymentAmount(parkticket).equals(payment)) {
            parkticket.setPayment(LocalDateTime.now());
            parkticketRepository.update(parkticket);
            return true;
        } else {
            return false;
        }

    }

    public List<Parkticket> getAll() {
        return parkticketRepository.findAll();
    }

    public boolean isAllowedToExit(String id) {
        Parkticket parkticket = this.parkticketRepository.findById(id).orElseThrow(NoSuchElementException::new);
        LocalDateTime now = LocalDateTime.now();
        return parkticket.getEntered().isBefore(now)
                && parkticket.getPayment() != null
                && now.minusMinutes(parkhausConfig.getToleranceBtwPaymentAndExit()).isBefore(parkticket.getPayment());
    }


}
