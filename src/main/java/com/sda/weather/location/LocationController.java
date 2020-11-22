package com.sda.weather.location;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@RestController
public class LocationController {

    final LocationCreateService locationCreateService;
    final LocationFetchService locationFetchService;
    final LocationMapper locationMapper;

    @GetMapping("/location/{id}")
    LocationDto getLocationById(@PathVariable Long id) {
        Location location = locationFetchService.fetchLocationById(id);
        return locationMapper.mapToLocationDto(location);
    }

    @GetMapping("/location/{nameCity}")
    LocationDto getLocationByNameCity(@PathVariable String nameCity) {
       Object location = locationFetchService.fetchLocationByNameCity(nameCity);
        return locationMapper.mapToLocationDto(location);
    }

    @PostMapping("/location")
    ResponseEntity<LocationDto> createLocation(@RequestBody LocationDto locationDto) {
        String nameCountry = locationDto.getNameCountry();
        String nameCity = locationDto.getNameCity();
        String region = locationDto.getRegion();
        String latitude = locationDto.getLatitude();
        String longitude = locationDto.getLongitude();
        Location newLocation = locationCreateService.createLocation(nameCountry, nameCity,
                region, latitude, longitude);
        log.info(newLocation);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(locationMapper.mapToLocationDto(newLocation));
    }

    @GetMapping("/location")
    ResponseEntity<List<Location>> getAllLocations() {
        List<Location> all = locationFetchService.fetchAllLocations();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(all);
    }

    }

