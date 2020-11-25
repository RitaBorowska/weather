package com.sda.weather.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDefinition {

    private String nameCountry;
    private String nameCity;
    private String region;
    private Double latitude;
    private Double longitude;

}
