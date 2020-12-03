package com.sda.weather.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDto {

    Long id;
    String temperature;
    String pressure;
    String humidity;
    String windDirection;
    String windSpeed;

}
