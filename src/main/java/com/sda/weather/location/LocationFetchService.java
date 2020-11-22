package com.sda.weather.location;

import com.sda.weather.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LocationFetchService {

    private final LocationRepository locationRepository;

    public Location fetchLocationById(Long id) {
        return locationRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new NotFoundException("Brak lokalizacji o id " + id));
    }
    public Object fetchLocationByNameCity(String nameCity) {
        return locationRepository.findLocationByNameCity((nameCity))
                .orElseThrow(() -> new NotFoundException("Brak lokalizacji danego miasta " + nameCity));
    }
    public List<Location> fetchAllLocations() {
        List<Location> array = new ArrayList<>();
        locationRepository.findAll()
                .forEach(array::add);
        return array;
    }
}
