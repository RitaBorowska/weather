package com.sda.weather.location;

import com.sda.weather.exceptions.BadRequest;
import org.junit.Assert;
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

        LocationDefinition reselt = LocationDefinition.builder()
                .nameCountry("Polska")
                .nameCity("Warszawa")
                .region("Mazowieckie")
                .latitude(52.00)
                .longitude(20.00 )
                .build();
        //when
        Location result = locationCreateService.createLocation(reselt);
        //then
        assertThat(result).isExactlyInstanceOf(Location.class);
        verify(locationRepository, times(1)).save(any(Location.class));

    }

    @Test
    void createLocation_whenNameCountryIsEmpty() {
        // when
        LocationDefinition location = LocationDefinition.builder()
                .nameCountry("")
                .nameCity("Poznan")
                .region("wielkopolskie")
                .latitude(52.4144)
                .longitude(16.9211)
                .build();

        //when
        Throwable result = catchThrowable(() -> locationCreateService.createLocation(location));

        //then
        assertThat(result).isInstanceOf(BadRequest.class);
        verify(locationRepository, times(0)).save(any(Location.class));
    }

    @Test
    void createLocation_whenNameCityIsEmpty() throws Exception{
        // given
        LocationDefinition location = LocationDefinition.builder()
                .nameCountry("Polska")
                .nameCity("  ")
                .region("małopolskie")
                .latitude(50.36)
                .longitude(19.56)
                .build();

        //when
        Throwable result = catchThrowable(() -> locationCreateService.createLocation(location));

        //then
        assertThat(result).isInstanceOf(BadRequest.class);
        verify(locationRepository, times(0)).save(any(Location.class));
    }

    @Test
    void createLocation_whenRegionIsEmpty_entryRepository() {
        // given
        LocationDefinition location =  LocationDefinition.builder()
                .nameCountry("Polska")
                .nameCity("Zakopane")
                .region("")
                .latitude(49.18)
                .longitude(19.57)
                .build();


        //when
        Throwable result = catchThrowable(() -> locationCreateService.createLocation(location));

        //then
        assertThat(result).isInstanceOf(BadRequest.class);
        verify(locationRepository, times(0)).save(any(Location.class));
    }

    @Test
    void createLocation_whenRegionIsBlank_entryRepository() {
        // given
        LocationDefinition location =  LocationDefinition.builder()
                .nameCountry("Polska")
                .nameCity("Zakopane")
                .region("  ")
                .latitude(49.18)
                .longitude(19.57)
                .build();


        //when
        Throwable result = catchThrowable(() -> locationCreateService.createLocation(location));

        //then
        assertThat(result).isInstanceOf(BadRequest.class);
        verify(locationRepository, times(0)).save(any(Location.class));
    }

    @Test
    void createLocation_whenLatitudeIsNull() {
        // when
        Throwable result = catchThrowable(() -> LocationDefinition.builder()
                .nameCountry("Polska")
                .nameCity("Zakopane")
                .region("podkarpacie")
                .latitude(0.00)
                .longitude(19.57)
                .build());

        //then
        Assert.assertNull(result);
        verify(locationRepository, times(0)).save(any(Location.class));
    }

    @Test
    void createLocation_whenLongitudeIsNull() {
        // when
        Throwable result = catchThrowable(() -> LocationDefinition.builder()
                .nameCountry("Polska")
                .nameCity("Zakopane")
                .region("podkarpacie")
                .latitude(49.18)
                .longitude( 0.00)
                .build());

        //then
        Assert.assertNull(result);
        verify(locationRepository, times(0)).save(any(Location.class));
    }

    @Test
    void createLocation_whenLongitudeIsOver180() {
        // given
        LocationDefinition location =  LocationDefinition.builder()
                .nameCountry("Polska")
                .nameCity("Zakopane")
                .region("podkarpacie")
                .longitude(0.00)
                .latitude(181.0)
                .build();

        //when
        Throwable result = catchThrowable(() -> locationCreateService.createLocation(location));

        //then
        assertThat(result).isInstanceOf(BadRequest.class);
        verify(locationRepository, times(0)).save(any(Location.class));
    }

    @Test
    void createLocation_whenLongitudeIsBelow180Negative() {

        LocationDefinition location =  LocationDefinition.builder()
                .nameCountry("Polska")
                .nameCity("Zakopane")
                .region("podkarpacie")
                .latitude(0.0)
                .longitude(-181.0)
                .build();

        // when
        Throwable result = catchThrowable(() -> locationCreateService.createLocation(location));
        //then
        assertThat(result).isInstanceOf(BadRequest.class);
        verify(locationRepository, times(0)).save(any(Location.class));
    }

    @Test
    void createLocation_whenLatitudeIsOver90() {
        // given
        LocationDefinition location =  LocationDefinition.builder()
                .nameCountry("Polska")
                .nameCity("Zakopane")
                .region("podkarpacie")
                .latitude(91.0)
                .longitude(0.0)
                .build();

        // when
        Throwable result = catchThrowable(() -> locationCreateService.createLocation(location));
        //then
        assertThat(result).isInstanceOf(BadRequest.class);
        verify(locationRepository, times(0)).save(any(Location.class));
    }

    @Test
    void createLocation_whenLatitude90Negative() {
        // when
        LocationDefinition location = LocationDefinition.builder()
                .nameCountry("Polska")
                .nameCity("Zakopane")
                .region("podkarpacie")
                .latitude(-91.0)
                .longitude(0.0)
                .build();

        // when
        Throwable result = catchThrowable(() -> locationCreateService.createLocation(location));
        //then
        assertThat(result).isInstanceOf(BadRequest.class);
        verify(locationRepository, times(0)).save(any(Location.class));
    }
}
