package mk.finki.ukim.mk.rent_v2.service.impl;

import mk.finki.ukim.mk.rent_v2.model.Car;
import mk.finki.ukim.mk.rent_v2.model.Reservation;
import mk.finki.ukim.mk.rent_v2.model.User;
import mk.finki.ukim.mk.rent_v2.model.exceptions.InvalidCarIdException;
import mk.finki.ukim.mk.rent_v2.model.exceptions.InvalidUserIdException;
import mk.finki.ukim.mk.rent_v2.repository.CarRepository;
import mk.finki.ukim.mk.rent_v2.repository.ReservationRepository;
import mk.finki.ukim.mk.rent_v2.repository.UserRepository;
import mk.finki.ukim.mk.rent_v2.service.ReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public ReservationServiceImpl(CarRepository carRepository, ReservationRepository reservationRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Reservation placeBooking(Long carId, String username, LocalDate startDate, LocalDate endDate, double totalPrice) {
        Car car = carRepository.findById(carId).orElseThrow(InvalidCarIdException::new);
        User user = userRepository.findByUsername(username).orElseThrow(InvalidUserIdException::new);

        return this.reservationRepository.save(new Reservation(user, car, startDate, endDate, totalPrice));
    }
}
