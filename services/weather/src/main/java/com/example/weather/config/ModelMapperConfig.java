package com.example.weather.config;

import com.example.weather.control.client.pojo.Main;
import com.example.weather.control.client.pojo.OpenWeatherResponse;
import com.example.weather.entity.model.Weather;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(OpenWeatherResponse.class, Weather.class).addMappings(mapper -> {
            mapper.map(src -> src.getMain().getHumidity(), Weather::setHumidity);
            mapper.map(src -> src.getMain().getPressure(), Weather::setHumidity);
            mapper.map(src -> src.getMain().getTemp(), Weather::setTemperature);
            mapper.map(src -> src.getMain().getTempMax(), Weather::setMaximumTemperature);
            mapper.map(src -> src.getMain().getTempMin(), Weather::setMinimumTemperature);
            mapper.map(src -> src.getWind().getDeg(), Weather::setWindDegree);
            mapper.map(src -> src.getWind().getSpeed(), Weather::setWindSpeed);
        });
        return modelMapper;
    }

}