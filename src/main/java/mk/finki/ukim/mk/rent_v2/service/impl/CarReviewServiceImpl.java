package mk.finki.ukim.mk.rent_v2.service.impl;

import mk.finki.ukim.mk.rent_v2.model.Car;
import mk.finki.ukim.mk.rent_v2.model.CarReview;
import mk.finki.ukim.mk.rent_v2.model.User;
import mk.finki.ukim.mk.rent_v2.model.exceptions.InvalidCarIdException;
import mk.finki.ukim.mk.rent_v2.model.exceptions.InvalidUserIdException;
import mk.finki.ukim.mk.rent_v2.repository.CarRepository;
import mk.finki.ukim.mk.rent_v2.repository.CarReviewRepository;
import mk.finki.ukim.mk.rent_v2.repository.UserRepository;
import mk.finki.ukim.mk.rent_v2.service.CarReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CarReviewServiceImpl implements CarReviewService {
    private final CarReviewRepository carReviewRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public CarReviewServiceImpl(CarReviewRepository carReviewRepository, CarRepository carRepository, UserRepository userRepository) {
        this.carReviewRepository = carReviewRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CarReview addReview(Long carId, String username, int rating) {
        Car car = this.carRepository.findById(carId).orElseThrow(InvalidCarIdException::new);
        User user = this.userRepository.findByUsername(username).orElseThrow(InvalidUserIdException::new);

        CarReview carReview = new CarReview(car, user, rating, LocalDate.now());

        return this.carReviewRepository.save(carReview);
    }

    @Override
    public void deleteReview(Long reviewId) {
        this.carReviewRepository.deleteById(reviewId);
    }

    public List<CarReview> getReviewsByCarId(Long carId) {
        return carReviewRepository.findByCarId(carId);
    }

    public double getAverageRatingByCarId(Long carId) {
        List<CarReview> reviews = getReviewsByCarId(carId);
        if (reviews.isEmpty()) {
            return 0.0;
        }
        return reviews.stream()
                .mapToInt(CarReview::getRating)
                .average()
                .orElse(0.0);
    }

    @Override
    public void saveRating(Long carId, int rating, Long userId) {
        CarReview review = new CarReview();
        Car car = this.carRepository.findById(carId).orElseThrow(() -> new IllegalArgumentException("Invalid car id:" + carId));
        review.setCar(car);
        review.setRating(rating);
        review.setReviewDate(LocalDate.now());

        User user = this.userRepository.findById(userId).orElseThrow(InvalidUserIdException::new);
        review.setUser(user);

        this.carReviewRepository.save(review);
    }
}
