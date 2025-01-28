package mk.finki.ukim.mk.rent_v2.service.impl;

import mk.finki.ukim.mk.rent_v2.model.Car;
import mk.finki.ukim.mk.rent_v2.model.Location;
import mk.finki.ukim.mk.rent_v2.model.enumerations.CarType;
import mk.finki.ukim.mk.rent_v2.model.exceptions.InvalidCarIdException;
import mk.finki.ukim.mk.rent_v2.model.exceptions.InvalidLocationIdException;
import mk.finki.ukim.mk.rent_v2.repository.CarRepository;
import mk.finki.ukim.mk.rent_v2.repository.LocationRepository;
import mk.finki.ukim.mk.rent_v2.service.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static mk.finki.ukim.mk.rent_v2.service.specs.FieldFilterSpecification.*;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final LocationRepository locationRepository;

    public CarServiceImpl(CarRepository carRepository, LocationRepository locationRepository) {
        this.carRepository = carRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public void deleteById(Long id) {
        this.carRepository.deleteById(id);
    }

    @Override
    public Optional<Car> update(Long id, String brand, String model, int year, double pricePerDay, boolean available, Long locationId, CarType carType) {
        Car car = this.carRepository.findById(id).orElseThrow(InvalidCarIdException::new);
        Location location = this.locationRepository.findById(locationId).orElseThrow(InvalidLocationIdException::new);

        car.setBrand(brand);
        car.setModel(model);
        car.setYear(year);
        car.setPricePerDay(pricePerDay);
        car.setAvailable(available);
        car.setLocation(location);
        car.setType(carType);

        return Optional.of(carRepository.save(car));
    }

    @Override
    public List<Car> listAll() {
        return this.carRepository.findAll();
    }

    @Override
    public Optional<Car> findById(Long id) {
        return this.carRepository.findById(id);
    }

    @Override
    public Optional<Car> findByModel(String model) {
        return this.carRepository.findByModel(model);
    }

    @Override
    public Optional<Car> save(String brand, String model, int year, double pricePerDay, boolean available, Long locationId, CarType carType, int rating) {
        Location location = this.locationRepository.findById(locationId).orElseThrow(InvalidLocationIdException::new);

        Car car = new Car(brand ,model, year, pricePerDay, available, carType, location, rating);

        return Optional.of(carRepository.save(car));
    }

    @Override
    public List<Car> searchCarsByLocation_Name(String name) {
        return this.carRepository.findAllByLocation_Name(name);
    }

    @Override
    public Page<Car> findPage(String brand, CarType carType, Integer pageNum, Integer pageSize) {
        Specification<Car> specification = Specification
                .where(filterContainsText(Car.class, "brand", brand))
                .and(filterEqualsV(Car.class, "carType", carType));

        return this.carRepository.findAll(
                specification,
                PageRequest.of(pageNum, pageSize)
        );
    }
}
