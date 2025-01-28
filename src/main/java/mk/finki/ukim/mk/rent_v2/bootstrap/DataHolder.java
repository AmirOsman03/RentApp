package mk.finki.ukim.mk.rent_v2.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.rent_v2.model.Car;
import mk.finki.ukim.mk.rent_v2.model.Location;
import mk.finki.ukim.mk.rent_v2.model.User;
import mk.finki.ukim.mk.rent_v2.model.enumerations.CarType;
import mk.finki.ukim.mk.rent_v2.model.enumerations.Role;
import mk.finki.ukim.mk.rent_v2.repository.CarRepository;
import mk.finki.ukim.mk.rent_v2.repository.LocationRepository;
import mk.finki.ukim.mk.rent_v2.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<User> userList = new ArrayList<>();
    public static List<Car> carList = new ArrayList<>();
    public static List<Location> locationList = new ArrayList<>();

    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final LocationRepository locationRepository;
    private final PasswordEncoder passwordEncoder;

    public DataHolder(UserRepository userRepository,
                      CarRepository carRepository, LocationRepository locationRepository,
                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;

        this.carRepository = carRepository;
        this.locationRepository = locationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        if (this.userRepository.count() == 0) {
            userList.add(new User("amir", passwordEncoder.encode("amir"), "Amir", "Amir", Role.ROLE_USER));
            userList.add(new User("admin", passwordEncoder.encode("admin"), "admin", "admin", Role.ROLE_ADMIN));
            this.userRepository.saveAll(userList);
        }

        Location skopje = new Location("Skopje", "Macedonia St", "100", "Macedonia");
        Location bitola = new Location("Bitola", "Central Square", "120", "Macedonia");
        Location ohrid = new Location("Ohrid", "Lake Side", "90", "Macedonia");
        Location strumica = new Location("Strumica", "Town Center", "80", "Macedonia");
        Location kumanovo = new Location("Kumanovo", "Main St", "110", "Macedonia");
        Location veles = new Location("Veles", "Vardar St", "95", "Macedonia");
        Location gostivar = new Location("Gostivar", "Bistrica River", "2300", "Macedonia");
        Location tetovo = new Location("Tetovo", "Street 2", "150", "Macedonia");

        if (this.carRepository.count() == 0) {
            // Car(String brand, String model, int year, double pricePerDay, boolean available, CarCategory category, Location location)
            Car car1 = new Car("BMW", "X5", 2023, 250.0, true, CarType.SUV, skopje, 4); // SUV in Skopje
            Car car2 = new Car("Audi", "A8", 2023, 100.0, true, CarType.SEDAN, tetovo, 3); // Sedan in Tetovo
            Car car3 = new Car("Porsche", "911", 2023, 400.0, true, CarType.SPORT, skopje, 4); // Sport in Skopje
            Car car4 = new Car("Mercedes", "S500", 2020, 240.0, true, CarType.SEDAN, skopje, 5); // Sedan in Skopje
            Car car5 = new Car("Toyota", "Corolla", 2022, 150.0, true, CarType.SEDAN, ohrid, 5); // Sedan in Ohrid
            Car car6 = new Car("Honda", "Civic", 2021, 120.0, true, CarType.SEDAN, bitola, 3); // Sedan in Bitola
            Car car7 = new Car("Audi", "Q7", 2023, 300.0, true, CarType.SUV, tetovo, 1); // SUV in Tetovo
            Car car8 = new Car("Ford", "Mustang", 2023, 350.0, true, CarType.SPORT, ohrid, 3); // Sport in Prilep
            Car car9 = new Car("Chevrolet", "Tahoe", 2022, 220.0, true, CarType.SUV, veles, 2); // SUV in Veles
            Car car10 = new Car("Tesla", "Model X", 2023, 500.0, true, CarType.SUV, kumanovo, 5); // SUV in Kumanovo
            Car car11 = new Car("Mazda", "CX-5", 2022, 180.0, true, CarType.SUV, gostivar, 5); // SUV in Gostivar
            Car car12 = new Car("BMW", "M4", 2023, 450.0, true, CarType.SPORT, strumica, 5); // Sport in Strumica

            carList.add(car1);
            carList.add(car2);
            carList.add(car3);
            carList.add(car4);
            carList.add(car5);
            carList.add(car6);
            carList.add(car7);
            carList.add(car8);
            carList.add(car9);
            carList.add(car10);
            carList.add(car11);
            carList.add(car12);

            this.carRepository.saveAll(carList);
        }

        if (this.locationRepository.count() == 0) {
            locationList.add(skopje);
            locationList.add(tetovo);
            locationList.add(ohrid);
            locationList.add(strumica);
            locationList.add(kumanovo);
            locationList.add(veles);
            locationList.add(gostivar);
            locationList.add(tetovo);
            this.locationRepository.saveAll(locationList);
        }
    }
}
