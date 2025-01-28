package mk.finki.ukim.mk.rent_v2.service;

import mk.finki.ukim.mk.rent_v2.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    List<Location> findAll();

    Optional<Location> findById(Long id);

    Optional<Location> findByName(String name);
}
