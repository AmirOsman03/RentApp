package mk.finki.ukim.mk.rent_v2.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "car_reviews")
public class CarReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car; // Релација со возилото

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Релација со корисникот

    @Column(nullable = false)
    private int rating; // Оценка

    @Column(nullable = false)
    private LocalDate reviewDate; // Датум на оставена рецензија

    public CarReview(Car car, User user, int rating, LocalDate reviewDate) {
        this.car = car;
        this.user = user;
        this.rating = rating;
        this.reviewDate = reviewDate;
    }

    public CarReview() {}
}
