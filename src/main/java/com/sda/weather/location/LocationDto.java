package com.sda.weather.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {

    Long id;
    String nameCountry;
    String region;
    String nameCity;
    String latitude;
    String longitude;


}
