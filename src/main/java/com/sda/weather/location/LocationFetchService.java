package com.sda.weather.location;

import com.sda.weather.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocationFetchService {

    private final LocationRepository locationRepository;

    public Location fetchLocation(String id)  {
        return locationRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new NotFoundException("brak wpisu" + id));
    }
}
