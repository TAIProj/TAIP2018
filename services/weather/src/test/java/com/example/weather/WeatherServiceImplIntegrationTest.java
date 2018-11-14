package com.example.weather;


import com.example.weather.control.client.OpenWeatherClient;
import com.example.weather.control.client.OpenWeatherClientImpl;
import com.example.weather.control.service.WeatherService;
import com.example.weather.control.service.impl.WeatherServiceImpl;
import com.example.weather.entity.model.Weather;
import com.example.weather.entity.repository.WeatherRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
public class WeatherServiceImplIntegrationTest {

    @TestConfiguration
    static class WeatherServiceImplTestContextConfiguration {

        @Bean
        public WeatherService weatherService() {
            return new WeatherServiceImpl();
        }

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }

        @Bean
        public OpenWeatherClient openWeatherClient() {
            return new OpenWeatherClientImpl();
        }
    }

    @Autowired
    private WeatherService weatherService;

    @MockBean
    private WeatherRepository weatherRepository;

    @Before
    public void setUp() {
        Weather weather = new Weather();
        weather.setTimestamp(new Date());
        weather.setTemperature(20.4);

        Mockito.when(weatherRepository.findTopByOrderByTimestampDesc())
                .thenReturn(Optional.of(weather));
    }

    @Test
    public void whenGetWeather_thenWeatherShouldBeFound() {
        Weather weather = weatherService.getCurrentWeather();
        assertThat(weather.getTemperature()).isEqualTo(20.4);
    }
}
