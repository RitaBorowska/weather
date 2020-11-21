package com.sda.weather.location;

import com.sda.weather.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocationFetchService {

    private final LocationRepository locationRepository;

    public Location fetchLocation(String id) {
        // todo protect against invalid id value -> returns 400 status code
        return locationRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new NotFoundException("Brak lokalizacji o id " + id));
    }
}
