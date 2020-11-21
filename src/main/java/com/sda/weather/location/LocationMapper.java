package com.sda.weather.location;

import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

    LocationDto mapToLocationDto(Location newLocation){
        LocationDto locationDto = new LocationDto();
        locationDto.setId(newLocation.getId());
        locationDto.setNameCountry(newLocation.getNameCountry());
        locationDto.setNameCity(newLocation.getNameCity());
        locationDto.setLatitude(newLocation.getLatitude());
        locationDto.setLongitude(newLocation.getLongitude());
        return locationDto;
    }
}
