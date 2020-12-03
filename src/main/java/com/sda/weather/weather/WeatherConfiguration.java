package com.sda.weather.weather;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ToString
@ConfigurationProperties("com.sda.weather.openweathermap-api")    // todo com.sda.weather.openweathermap-api
public class WeatherConfiguration {

    private String apiKey;
    private String uri;
    private String units;
    private String lang;

    // todo
    //  private String apiKey
    //  private String uri
    //  ...
}
