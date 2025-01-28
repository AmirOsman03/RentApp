package mk.finki.ukim.mk.rent_v2.service;

import mk.finki.ukim.mk.rent_v2.model.Car;
import mk.finki.ukim.mk.rent_v2.model.enumerations.CarType;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> listAll();

    Optional<Car> findById(Long id);

    Optional<Car> findByModel(String model);

    // Car(String brand, String model, int year, double pricePerDay, boolean available, CarCategory category, Location location)
    Optional<Car> save(String brand, String model, int year, double pricePerDay, boolean available, Long locationId, CarType carType, int rating);

    void deleteById(Long id);

    Optional<Car> update(Long id, String brand, String model, int year, double pricePerDay, boolean available, Long locationId, CarType carType);

    List<Car> searchCarsByLocation_Name(String name);

    Page<Car> findPage(String brand, CarType carType, Integer pageNum, Integer pageSize);
}
