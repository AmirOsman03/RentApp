package mk.finki.ukim.mk.rent_v2.repository;

import mk.finki.ukim.mk.rent_v2.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Може да додадеш методи за пребарување на резервации по корисник или датум
    List<Reservation> findByUserId(Long userId);
}
