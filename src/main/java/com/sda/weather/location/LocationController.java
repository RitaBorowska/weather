package com.sda.weather.location;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
@RestController
public class LocationController {

    private final LocationCreateService locationCreateService;
    private final LocationFetchService locationFetchService;
    private final LocationMapper locationMapper;

    @GetMapping("/location/{id}")
    LocationDto getLocationById(@PathVariable Long id) {
        Location location = locationFetchService.fetchLocationById(id);
        return locationMapper.mapToLocationDto(location);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/location")
    ResponseEntity<LocationDto> createLocation(@RequestBody LocationDto locationDto) {
        LocationDefinition locationDefinition = locationMapper.mapToLocationDefinition(locationDto);
        Location newLocation = locationCreateService.createLocation(locationDefinition);
        log.info("create location: " + newLocation);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(locationMapper.mapToLocationDto(newLocation));
    }

    @GetMapping("/location")
    ResponseEntity<List<LocationDto>> getAllLocations() {
        List<LocationDto> locations = locationFetchService.fetchAllLocations().stream()
                .map(locationMapper::mapToLocationDto)
                .collect(Collectors.toList());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(locations);
    }
}

