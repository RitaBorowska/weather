package com.sda.weather.weather;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ToString
@ConfigurationProperties("com.sda.weather.weatherstack-api")    // todo com.sda.weather.openweathermap-api
public class WeatherConfiguration {
    // todo
    //  private String apiKey
    //  private String uri
    //  ...
}
