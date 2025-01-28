package mk.finki.ukim.mk.rent_v2.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Уникатен идентификатор за секоја резервација.

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Корисник кој ја направил резервацијата.

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car; // Изнајмено возило.

    @Column(nullable = false)
    private LocalDate startDate; // Датум кога почнува резервацијата.

    @Column(nullable = false)
    private LocalDate endDate; // Датум кога завршува резервацијата.

    @Column(nullable = false)
    private double totalPrice; // Вкупна цена за изнајмувањето.

    public Reservation(User user, Car car, LocalDate startDate, LocalDate endDate, double totalPrice) {
        this.user = user;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
    }

    public Reservation() {}

    public void setUser(User user) {
        this.user = user;
    }
}
