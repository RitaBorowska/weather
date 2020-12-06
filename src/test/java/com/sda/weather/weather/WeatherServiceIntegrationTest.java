package com.sda.weather.weather;

import com.sda.weather.location.Location;
import com.sda.weather.location.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
class WeatherServiceIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    LocationRepository locationRepository;

    Location saveLocation;

    @BeforeEach
    void setUp(){
        locationRepository.deleteAll();
        Location location = new Location();
        location.setNameCity("Warsaw");
        location.setNameCountry("Polska");
        location.setLatitude(66.0);
        location.setLongitude(44.0);
        saveLocation = locationRepository.save(location);
    }

    @Test
    void getWeather_returnCorrectWeatherAnd200SC() throws Exception {
        //given
        Long id = saveLocation.getId();
        MockHttpServletRequestBuilder request =get("/location/" + id + "/weather")
                .contentType(MediaType.APPLICATION_JSON);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void getWeather_whenPeriodOver5_return400SC() throws Exception {
        //given
        Long id  = saveLocation.getId();
        MockHttpServletRequestBuilder request = get("/location/" + id + "/weather?period=6")
                .contentType(MediaType.APPLICATION_JSON);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
