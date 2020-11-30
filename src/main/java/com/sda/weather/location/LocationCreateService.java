package com.sda.weather.location;

import com.sda.weather.exceptions.BadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LocationCreateService {

    private final LocationRepository locationRepository;

    Location createLocation(LocationDefinition locationDefinition) {
        String nameCity = locationDefinition.getNameCity();
        String nameCountry = locationDefinition.getNameCountry();
        Double longitude = locationDefinition.getLongitude();
        Double latitude = locationDefinition.getLatitude();

        if (nameCity.isEmpty() || nameCountry.isEmpty() || nameCity.isBlank() || nameCountry.isBlank()) {
            throw new BadRequest("City or Country can't be empty");
        }
        if (longitude < -180 || longitude > 180 || latitude < -90 || latitude > 90) {
            throw new BadRequest("Incorrect geographical coordinates.");
        }

        Location location = new Location();
        location.setNameCity(locationDefinition.getNameCity());
        location.setNameCountry(locationDefinition.getNameCountry());
        location.setLongitude(locationDefinition.getLongitude());
        location.setLatitude(locationDefinition.getLatitude());

        String region = locationDefinition.getRegion();
        Optional.ofNullable(region)
                .filter(r -> !r.isBlank())
                .ifPresent(location::setRegion);

        return locationRepository.save(location);
    }
}

