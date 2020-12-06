package com.sda.weather.weather;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Configuration
@ConfigurationProperties("com.sda.weather.openweathermap-api")
public class WeatherConfiguration {

    private String apiKey;
    private String uri;
    private String units;
    private String lang;
//
//    // todo
//    //  private String apiKey
//    //  private String uri
//    //  ...
}
