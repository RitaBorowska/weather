package com.sda.weather.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.location.Location;
import com.sda.weather.location.LocationFetchService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
@Data
public class WeatherService {

    private final LocationFetchService locationFetchService;
    private final RestTemplate restTemplate;
    private final  ObjectMapper objectMapper;

    public Weather getWeather(Long id, Integer period) {
        Location location = locationFetchService.fetchLocationById(id);
        String cityName = location.getNameCity();
    //    String url = "http://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&appid=70e566d6f0d272ed1581ec16fd4d7a1d";

        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.openweathermap.org/data/2.5/forecast")
                .queryParam("q",  cityName)
                .queryParam("appid", "70e566d6f0d272ed1581ec16fd4d7a1d")
                .build()
                .toUriString();

        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        String response = entity.getBody();

        try {
            WeatherOpenWeatherResponse weather = objectMapper.readValue(response, WeatherOpenWeatherResponse.class);
            System.out.println(weather.getCod());
            System.out.println(weather.getCity().getName());
            System.out.println(weather.getList().get(1).getDate());
            System.out.println(weather.getList().get(1).getMain());
            System.out.println(weather.getList().get(1).getWind());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

            return new Weather();
    }
}