package com.sda.weather.location;

import com.fasterxml.jackson.core.type.TypeReference;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class LocationFetchIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    LocationRepository locationRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void fetchNewLocation_whenRegionIsEmpty_createNewLocationAndReturn201StatusCode() throws Exception {
        //given
        locationRepository.deleteAll();

        Location location = Location.builder()
                .id(null)
                .nameCountry("Polska")
                .nameCity("Gdansk")
                .longitude(18.65)
                .latitude(54.35)
                .region("")
                .build();

        locationRepository.save(location);

        MockHttpServletRequestBuilder builder = get("/location")
                .contentType(MediaType.APPLICATION_JSON);

        //when
        MvcResult result = mockMvc.perform(builder).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        String responseBody = response.getContentAsString();
        List<LocationDto> locations = objectMapper.readValue(responseBody, new TypeReference<>() {
        });
        assertThat(locations).hasSize(1);
        assertThat(locations).anySatisfy(singleLocation -> {
            assertThat(singleLocation.getNameCity()).isEqualTo("Gdansk");
            assertThat(singleLocation.getRegion()).isEqualTo("");
            assertThat(singleLocation.getLongitude()).isEqualTo(18.65);
            assertThat(singleLocation.getLatitude()).isEqualTo(54.38);

        });
    }
    @Test
    void fetchNewLocation_whenRegionIsBlank_createNewLocationAndReturn201StatusCode() throws Exception {
        //given
        locationRepository.deleteAll();

        Location location = Location.builder()
                .id(null)
                .nameCountry("Polska")
                .nameCity("Gdansk")
                .longitude(18.65)
                .latitude(54.35)
                .region("  ")
                .build();

        locationRepository.save(location);

        MockHttpServletRequestBuilder builder = get("/location")
                .contentType(MediaType.APPLICATION_JSON);

        //when
        MvcResult result = mockMvc.perform(builder).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        String responseBody = response.getContentAsString();
        List<LocationDto> locations = objectMapper.readValue(responseBody, new TypeReference<>() {
        });
        assertThat(locations).hasSize(1);
        assertThat(locations).anySatisfy(singleLocation -> {
            assertThat(singleLocation.getNameCity()).isEqualTo("Gdansk");
            assertThat(singleLocation.getRegion()).isEqualTo("  ");
            assertThat(singleLocation.getLongitude()).isEqualTo(18.65);
            assertThat(singleLocation.getLatitude()).isEqualTo(54.35);

        });
    }

}