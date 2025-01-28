package mk.finki.ukim.mk.rent_v2.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Идентификатор на локацијата

    @Column(nullable = false)
    private String name; // Име на локацијата (на пример: Skopje Airport, Ohrid City Centre, итн.)

    @Column(nullable = false)
    private String address; // Адреса на локацијата

    @Column(nullable = false)
    private String city; // Град

    @Column(nullable = false)
    private String country; // Држава

    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY) // Се поврзува со Car
    private List<Car> cars;

    public Location(String name, String address, String city, String country) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
    }

    public Location() {}
}
