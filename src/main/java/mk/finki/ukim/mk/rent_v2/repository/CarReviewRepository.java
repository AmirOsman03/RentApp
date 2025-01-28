package mk.finki.ukim.mk.rent_v2.repository;

import mk.finki.ukim.mk.rent_v2.model.CarReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarReviewRepository extends JpaRepository<CarReview, Long> {
    // Метод за наоѓање на сите прегледи за одреден автомобил
    List<CarReview> findByCarId(Long carId);

    // Метод за наоѓање на прегледи од одреден корисник
    List<CarReview> findByUserUsername(String username);

    Optional<CarReview> findById(Long id);
}
