package com.sda.weather.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {

    Long id;
    String nameCountry;
    String region;
    String nameCity;
    Double latitude;
    Double longitude;
}
