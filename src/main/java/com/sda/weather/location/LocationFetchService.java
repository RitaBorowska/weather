package com.sda.weather.location;

import com.sda.weather.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LocationFetchService {

    private final LocationRepository locationRepository;

    public Location fetchLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Brak lokalizacji o id " + id));
    }

    List<Location> fetchAllLocations() {
        return locationRepository.findAll();
    }
}
