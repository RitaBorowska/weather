package com.sda.weather.location;

import com.sda.weather.exceptions.BadRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class LocationCreateServiceTest {

    @Mock
    LocationRepository locationRepository;

    @InjectMocks
    LocationCreateService locationCreateService;

    @Test
    void createLocation_entryRepository() {
        //given
        when(locationRepository.save(any(Location.class))).thenReturn(new Location());

        //when
        Location reselt = locationCreateService.createLocation(
                "Polska",
                "Warszawa",
                "Mazowieckie",
                "52",
                "20");

        //then
        verify(locationRepository).save(any(Location.class));

    }

    @Test
    void createLocation_whenNameCountryIsEmpty(){
        // when
        Throwable result = catchThrowable(() -> locationCreateService.createLocation(
                "",
                "Poznan",
                "wielkopolskie",
                "52.4144",
                "16.9211"
        ));

        //then
        assertThat(result).isInstanceOf(BadRequest.class);
        verify(locationRepository, times(0)).save(any(Location.class));
    }

    @Test
    void createLocation_whenNameCityIsEmpty(){
        // when
        Throwable result = catchThrowable(() -> locationCreateService.createLocation(
                "Polska",
                "",
                "małopolskie",
                "50.368",
                "19.56"
        ));

        //then
        assertThat(result).isInstanceOf(BadRequest.class);
        verify(locationRepository, times(0)).save(any(Location.class));
    }

    @Test
    void createLocation_whenRegionIsEmpty(){
        // when
        Throwable result = catchThrowable(() -> locationCreateService.createLocation(
                "Polska",
                "Zakopane",
                "",
                "49.18",
                "19.57"
        ));

        //then
        assertThat(result).isInstanceOf(BadRequest.class);
        verify(locationRepository, times(0)).save(any(Location.class));
    }
    @Test
    void createLocation_whenLatitudeIsEmpty(){
        // when
        Throwable result = catchThrowable(() -> locationCreateService.createLocation(
                "Polska",
                "Zakopane",
                "podkarpacie",
                "",
                "19.57"
        ));

        //then
        assertThat(result).isInstanceOf(BadRequest.class);
        verify(locationRepository, times(0)).save(any(Location.class));
    }
    @Test
    void createLocation_whenLongitudeIsEmpty(){
        // when
        Throwable result = catchThrowable(() -> locationCreateService.createLocation(
                "Polska",
                "Zakopane",
                "podkarpacie",
                "49.18",
                ""
        ));

        //then
        assertThat(result).isInstanceOf(BadRequest.class);
        verify(locationRepository, times(0)).save(any(Location.class));
    }

}
