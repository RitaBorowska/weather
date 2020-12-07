package com.sda.weather.weather;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class WeatherMapper {

    WeatherDto mapToWeatherDto(Weather newWeather){
        return WeatherDto.builder()
                .temperature(newWeather.getTemperature())
                .pressure(newWeather.getPressure())
                .humidity(newWeather.getHumidity())
                .windSpeed(newWeather.getWindSpeed())
                .windDirection(newWeather.getWindDirection())
                .build();
    }

}
