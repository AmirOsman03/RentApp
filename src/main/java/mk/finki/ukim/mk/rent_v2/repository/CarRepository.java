package mk.finki.ukim.mk.rent_v2.repository;

import mk.finki.ukim.mk.rent_v2.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaSpecificationRepository<Car, Long> {
    Optional<Car> findByModel(String model);

    // метод за враќање на сите автомобили од дадена локација
    List<Car> findAllByLocation_Name(String name);

    void deleteById(Long id);
}
