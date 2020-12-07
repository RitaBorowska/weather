package com.sda.weather.weather;

import com.sda.weather.exceptions.NotFoundException;
import com.sda.weather.weather.client.WeatherClient;
import com.sda.weather.location.Location;
import com.sda.weather.location.LocationFetchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class WeatherService {

    private final LocationFetchService locationFetchService;
    private final WeatherClient weatherClient;
    private final WeatherRepository weatherRepository;

    Weather getWeather(Long id, Integer period) {
        Location location  = locationFetchService.fetchLocationById(id);
        String nameCity = location.getNameCity();
        LocalDate weatherData = LocalDate.now().plusDays(period);

        Weather weather = weatherClient.getWeather(nameCity, weatherData)
                .orElseThrow(() -> new NotFoundException("Prognozy dla mmiasta" + nameCity+ " nie odnaleziono " + weatherData));

        return weatherRepository.save(weather);
    }

}