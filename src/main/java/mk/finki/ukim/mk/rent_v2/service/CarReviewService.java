package mk.finki.ukim.mk.rent_v2.service;

import mk.finki.ukim.mk.rent_v2.model.CarReview;

import java.util.List;
import java.util.Optional;

public interface CarReviewService {
    CarReview addReview(Long carId, String username, int rating);

    void deleteReview(Long reviewId);

    List<CarReview> getReviewsByCarId(Long carId);

    double getAverageRatingByCarId(Long carId);

    void saveRating(Long carId, int rating, Long userId);
}
