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

        UriComponents build = UriComponentsBuilder.fromHttpUrl(configuraton.getUri())
                .queryParam("access_key", config.getApiKey())
                .queryParam("query", location)
                .queryParam("units", config.getUnits())
                .queryParam("lang", config.getLang())
                .build();

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(build.toUri(), String.class);

        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new BadRequestException("Unable to get data from remote service.");
        }

        String responseBody = responseEntity.getBody();

        try {
            ForecastDTO forecastDTO = objectMapper.readValue(responseBody, ForecastDTO.class);
            return saveForecastToDatabase(forecastDTO);
        } catch (JsonProcessingException e) {
            throw new DataProcessingErrorException("Unable to process forecast data.");
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
