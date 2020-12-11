package com.sda.weather.weather.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.weather.Weather;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class WeatherClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final WeatherClientConfiguration weatherClientConfiguration;

    public Optional<Weather> getWeather(String cityName, LocalDate weatherData) {

        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.openweathermap.org/data/2.5/forecast")
                .queryParam("q",  cityName)
                .queryParam("appid", weatherClientConfiguration.getApiKey())
                .build()
                .toUriString();

        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        String response = entity.getBody();

        try {
            WeatherOpenResponse weatherOpenResponse = objectMapper.readValue(response, WeatherOpenResponse.class);
            LocalDateTime date = weatherData.atTime(6, 30);
            return weatherOpenResponse.getSingleWeatherList().stream()
                    .filter(f -> mapToLocalDateTime(f.getDate()).isEqual(date))
                    .map(this::mapToWeather)
                    .findFirst();
        } catch (JsonProcessingException e) {
            log.error("Brak informacji o pogodzie", e);
            return Optional.empty();
        } catch (RestClientException e) {
        log.error("Blad polaczenia z url " + url, e);
        return Optional.empty();
    }
    }

    private LocalDateTime mapToLocalDateTime(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, dateTimeFormatter);
    }

    Weather mapToWeather(WeatherOpenResponse.SingleWeather singleWeather){
        LocalDateTime weatherData = mapToLocalDateTime(singleWeather.getDate());
        Instant dateWeatherInstant = weatherData.atZone(ZoneId.systemDefault()).toInstant();

        return Weather.builder()
                .temperature(singleWeather.getMain().getTemperature())
                .pressure(singleWeather.getMain().getPressure())
                .humidity(singleWeather.getMain().getHumidity())
                .windSpeed(singleWeather.getWind().getWindSpeed())
                .windDirection(singleWeather.getWind().getWindDirection())
                .weatherDate(dateWeatherInstant)
                .build();
    }
}
