package com.sda.weather.location;

import lombok.Builder;
import org.springframework.stereotype.Component;

@Builder
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

    LocationDefinition mapToLocationDefinition(LocationDto locationDto) {
        LocationDefinition locationDefinition = new LocationDefinition();
        locationDefinition.setNameCountry(locationDto.getNameCountry());
        locationDefinition.setNameCity(locationDto.getNameCity());
        locationDefinition.setRegion(locationDto.getRegion());
        locationDefinition.setLatitude(locationDto.getLatitude());
        locationDefinition.setLongitude(locationDto.getLongitude());
        return locationDefinition;
    }
}
