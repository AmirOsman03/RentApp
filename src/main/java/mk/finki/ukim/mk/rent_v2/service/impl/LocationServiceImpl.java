package mk.finki.ukim.mk.rent_v2.service.impl;

import mk.finki.ukim.mk.rent_v2.model.Location;
import mk.finki.ukim.mk.rent_v2.repository.LocationRepository;
import mk.finki.ukim.mk.rent_v2.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> findAll() {
        return this.locationRepository.findAll();
    }

    @Override
    public Optional<Location> findById(Long id) {
        return this.locationRepository.findById(id);
    }

    @Override
    public Optional<Location> findByName(String name) {
        return this.locationRepository.findByName(name);
    }
}
