package com.sda.weather.location;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Log4j2
@RestController
public class LocationController {

    final LocationService locationService;
    final LocationFetchService locationFetchService;
    final LocationMapper locatioMapper;

    @GetMapping("/location/{id}")
    LocationDto getLocations(@PathVariable String id) {
        Location location = locationFetchService.fetchLocation(id);
        return  locatioMapper.mapToLocationDto(location);
    }

    @PostMapping("/location")
    ResponseEntity<LocationDto> createLocation (@RequestBody LocationDto locationDto){
        String nameCountry = locationDto.getNameCountry();
        String nameCity = locationDto.getNameCity();
        String region = locationDto.getRegion();
        String latitude = locationDto.getLatitude();
        String longitude = locationDto.getLongitude();
        Location newLocation = locationService.createLocation(nameCountry, nameCity,
                region, latitude, longitude);
        log.info(newLocation);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(locatioMapper.mapToLocationDto(newLocation));
    }


}
