package com.sda.weather.location;

import com.sda.weather.exceptions.BadRequestNoCountryOrCityNames;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocationCreateService {

    final LocationRepository locationRepository;

    Location createLocation(String nameCountry, String nameCity, String region, String latitude, String longitude) {
        if (nameCountry.isEmpty() && nameCity.isEmpty() && region.isEmpty() && latitude.isEmpty() && longitude.isEmpty()) { // todo split this validation
            throw new BadRequestNoCountryOrCityNames("Nie podano wartosci");
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
