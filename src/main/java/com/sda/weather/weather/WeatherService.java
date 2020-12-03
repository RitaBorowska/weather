package com.sda.weather.weather;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Builder
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherConfiguration configuration;


    public WeatherDto getWeather(String location) {

    }
//     WeatherDto saveWeather(WeatherDto newWeatherDto) {
//        return WeatherDto.builder()
//                .id(newWeatherDto.getId())
//                .temperature(newWeatherDto.getTemperature())
//                .pressure(newWeatherDto.getPressure())
//                .humidity(newWeatherDto.getHumidity())
//                .windDirection(newWeatherDto.getWindDirection())
//                .windSpeed(newWeatherDto.getWindSpeed())
//                .build();
//    }

}