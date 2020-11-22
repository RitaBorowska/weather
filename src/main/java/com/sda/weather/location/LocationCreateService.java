package com.sda.weather.location;

import com.sda.weather.exceptions.BadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocationCreateService {

    final LocationRepository locationRepository;

    Location createLocation(String nameCountry, String nameCity, String region, String latitude, String longitude) {
        if (nameCountry.isEmpty()) {
            throw new BadRequest("Nie podano panstwa");
        }
        if (nameCity.isEmpty()) {
            throw new BadRequest("Nie podano miasta");
        }
        if (region.isEmpty()) {
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
        location.setRegion(region);
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        return locationRepository.save(location);
    }
}
