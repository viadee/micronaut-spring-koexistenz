package de.viadee.parkhaus.manager.repository;

import de.viadee.parkhaus.manager.entity.Parkticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ParkticketRepository extends JpaRepository<Parkticket, String> {

    public List<Parkticket> findAll();

    public Optional<Parkticket> findById(String id);
}
