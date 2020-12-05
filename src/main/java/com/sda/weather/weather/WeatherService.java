package com.sda.weather.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.location.Location;
import com.sda.weather.location.LocationFetchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class WeatherService {
    private final LocationFetchService locationFetchService;
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    public Weather getWeather(Long id, String period) {
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
        String respone = entity.getBody();

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            WeatherOpenWeatherResponse weather = objectMapper.readValue(respone, WeatherOpenWeatherResponse.class);
            System.out.println(weather.getCod());
            System.out.println(weather.getCity().getName());
            System.out.println(weather.getList().get(1).getDate());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // save to the database
        return new Weather();

    }
}