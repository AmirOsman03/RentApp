package mk.finki.ukim.mk.rent_v2.service;

import java.time.LocalDate;

public interface ReservationService {
    // Reservation(User user, Car car, LocalDate startDate, LocalDate endDate, double totalPrice)
    mk.finki.ukim.mk.rent_v2.model.Reservation placeBooking(Long carId, String username, LocalDate startDate, LocalDate endDate, double totalPrice);
}
