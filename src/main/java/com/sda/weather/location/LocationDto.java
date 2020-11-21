package com.sda.weather.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {

    private Long id;
    private String nameCountry;
    private String region;
    private String nameCity;
    private String latitude;
    private String longitude;
}
