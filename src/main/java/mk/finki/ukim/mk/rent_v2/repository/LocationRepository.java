package mk.finki.ukim.mk.rent_v2.repository;

import mk.finki.ukim.mk.rent_v2.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    // Метод за наоѓање на сите локации по град
    List<Location> findByCity(String city);

    // Метод за наоѓање на локации по земја
    List<Location> findByCountry(String country);

    Optional<Location> findByName(String name);
}
