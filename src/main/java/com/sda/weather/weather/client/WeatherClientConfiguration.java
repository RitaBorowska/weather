package com.sda.weather.weather.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("client.openweather")
public class WeatherClientConfiguration {

    private String apiKey;

}
