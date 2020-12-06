package com.sda.weather.weather;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherDto {

    Long id;
    String temperature;
    String pressure;
    String humidity;
    String windDirection;
    String windSpeed;

}
