package com.sda.weather.weather.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherOpenResponse {

    @JsonProperty("list")
    private List<SingleWeather> singleWeatherList;

    @Data
    public static  class SingleWeather {
        @JsonProperty("dt_txt")
        private String date;
        private Main main;
        private Wind wind;


    @Data
    public static class Main {
        private String pressure;
        private String humidity;
        @JsonProperty("temp")
        private String temperature;
    }
    @Data
    public static class Wind {
        @JsonProperty("speed")
        private String windSpeed;
        @JsonProperty("deg")
        private String windDirection;
    }

}}