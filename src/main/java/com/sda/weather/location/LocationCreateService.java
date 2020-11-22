package com.sda.weather.location;

import com.sda.weather.exceptions.BadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocationCreateService {

    final LocationRepository locationRepository;

    Location createLocation(String nameCountry, String nameCity, String region, String latitude, String longitude) {
        if (nameCountry.isEmpty()) {    // todo isEmpty vs isBlank
            throw new BadRequest("Nie podano panstwa");
        }
        if (nameCity.isEmpty()) {
            throw new BadRequest("Nie podano miasta");
        }
        if (region.isEmpty()) { // todo check the requirements
            throw new BadRequest("Nie podano regionu");
        }
        if (latitude.isEmpty()) {
            throw new BadRequest("nie podano szerokpsci geograficznej");
        }
        if (longitude.isEmpty()) {
            throw new BadRequest("nie podano dlugosci geograficznej");
        }

        Location location = new Location();
        location.setNameCountry(nameCountry);
        location.setNameCity(nameCity);
        location.setRegion(region); // todo check if region is empty (isBlank), then don't save this data
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        return locationRepository.save(location);
    }
}
