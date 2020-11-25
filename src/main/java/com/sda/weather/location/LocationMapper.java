package com.sda.weather.location;

import lombok.Builder;
import org.springframework.stereotype.Component;

@Builder
@Component
class LocationMapper {

    LocationDto mapToLocationDto(Location newLocation) {
        return LocationDto.builder()
                .id(newLocation.getId())
                .nameCountry(newLocation.getNameCountry())
                .nameCity(newLocation.getNameCity())
//                .region(String.valueOf(newLocation.getRegion()))
//                .region(newLocation.getRegion().orElse(null))
                .latitude(newLocation.getLatitude())
                .longitude(newLocation.getLongitude())
                .build();

    }

    LocationDefinition mapToLocationDefinition(LocationDto newLocationDto) {
        return LocationDefinition.builder()
                .nameCountry(newLocationDto.getNameCountry())
                .nameCity(newLocationDto.getNameCity())
                .region(newLocationDto.getRegion())
                .latitude(newLocationDto.getLatitude())
                .longitude(newLocationDto.getLongitude())
                .build();

    }
}