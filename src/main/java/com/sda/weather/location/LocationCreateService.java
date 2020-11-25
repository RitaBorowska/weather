package com.sda.weather.location;

import com.sda.weather.exceptions.BadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocationCreateService {

    private final LocationRepository locationRepository;

    Location createLocation(LocationDefinition locationDefinition) {
        String nameCity = locationDefinition.getNameCity();
        String nameCountry = locationDefinition.getNameCountry();
//        String region = locationDefinition.getRegion();
        Double longitude = locationDefinition.getLongitude();
        Double latitude = locationDefinition.getLatitude();

        if (nameCity.isEmpty() || nameCountry.isEmpty() || nameCity.isBlank() || nameCountry.isBlank()) {
            throw new BadRequest("City or Country can't be empty");
        }
        if (longitude < -180 || longitude > 180 || latitude < -90 || latitude > 90) {
            throw new BadRequest("Incorrect geographical coordinates.");
        }
        Location location = new Location();
        location.setNameCity(nameCity);
        location.setNameCountry(nameCountry);
        location.setLongitude(locationDefinition.getLongitude());
        location.setLatitude(locationDefinition.getLatitude());
        return locationRepository.save(location);
    }
}

