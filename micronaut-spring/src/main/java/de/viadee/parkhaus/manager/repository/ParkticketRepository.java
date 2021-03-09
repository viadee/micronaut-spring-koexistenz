package de.viadee.parkhaus.manager.repository;

import de.viadee.parkhaus.manager.entity.Parkticket;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkticketRepository extends JpaRepository<Parkticket, String> {

    public List<Parkticket> findAll();

    public Optional<Parkticket> findById(String id);
}
