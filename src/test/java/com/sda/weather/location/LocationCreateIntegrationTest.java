package com.sda.weather.location;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class LocationCreateIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    LocationRepository locationRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void createLocation_createLocationReturn200SC() throws Exception {
        // given
        locationRepository.deleteAll();
        LocationDto locationDto = new LocationDto(null,
                "Polska",
                "pomorskie",
                "Gdansk",
                54.35,
                18.6667);
        MockHttpServletRequestBuilder post = post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(locationDto));

        // when
        MvcResult result = mockMvc.perform(post).andReturn();

        // then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        LocationDto responseBody = objectMapper.readValue(response.getContentAsString(), LocationDto.class);
        assertThat(responseBody.getId()).isNotNull();
        assertThat(responseBody.getNameCity()).isEqualTo("Gdansk");
    }

    @Test
    void createLocation_whenNameCityIsEmpty_Return400SC() throws Exception {
        // given
        locationRepository.deleteAll();
        LocationDto locationDto = new LocationDto(null,
                "Polska",
                "pomorskie",
                " ",
                54.35,
                18.6667);
        MockHttpServletRequestBuilder post = post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(locationDto));

        // when
        MvcResult result = mockMvc.perform(post).andReturn();

        // then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        List<Location> locations = locationRepository.findAll();
        assertThat(locations).isEmpty();
    }

    @Test
    void createLocation_whenNameCountryIsEmpty_Return400SC() throws Exception {
        // given
        locationRepository.deleteAll();
        LocationDto locationDto = new LocationDto(null,
                "",
                "pomorskie",
                "gDANSK",
                54.35,
                18.6667);
        MockHttpServletRequestBuilder post = post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(locationDto));

        // when
        MvcResult result = mockMvc.perform(post).andReturn();

        // then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        List<Location> locations = locationRepository.findAll();
        assertThat(locations).isEmpty();
    }


}
