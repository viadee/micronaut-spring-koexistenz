package de.viadee.parkhaus.manager.repository;

import de.viadee.parkhaus.manager.entity.Parkticket;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkticketRepository extends CrudRepository<Parkticket, String> {

    public List<Parkticket> findAll();

    public Optional<Parkticket> findById(String id);
}
