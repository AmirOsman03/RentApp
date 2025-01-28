package mk.finki.ukim.mk.rent_v2.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.finki.ukim.mk.rent_v2.model.enumerations.CarType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Data
@NoArgsConstructor
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarType type;

    private String brand;
    private String model;

    @Column(name = "\"year\"") // Поставување на "year" во двојни наводници
    private int year;

    private double pricePerDay;

    @Column(nullable = false)
    private boolean available;

    private int rating;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    @Cascade(CascadeType.PERSIST) // Користи Hibernate CascadeType овде
    private Location location;

    public Car(String brand, String model, int year, double pricePerDay, boolean available, CarType type, Location location, int rating) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.pricePerDay = pricePerDay;
        this.available = available;
        this.type = type;
        this.location = location;
        this.rating = rating;
    }
}
