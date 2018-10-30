package com.example.weather.control.service.impl;

import com.example.weather.control.client.OpenWeatherClient;
import com.example.weather.control.client.pojo.OpenWeatherResponse;
import com.example.weather.control.service.WeatherService;
import com.example.weather.entity.model.Weather;
import com.example.weather.entity.repository.WeatherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherServiceImpl implements WeatherService {

    private WeatherRepository weatherRepository;
    private ModelMapper modelMapper;
    private OpenWeatherClient openWeatherClient;

    @Autowired
    public WeatherServiceImpl(WeatherRepository weatherRepository, ModelMapper modelMapper, OpenWeatherClient openWeatherClient) {
        this.weatherRepository = weatherRepository;
        this.modelMapper = modelMapper;
        this.openWeatherClient = openWeatherClient;
    }

    @Override
    public Weather save(Weather entity) {
        return weatherRepository.save(entity);
    }

    @Override
    public Optional<Weather> findById(Long id) {
        return weatherRepository.findById(id);
    }

    @Override
    public List<Weather> findAll() {
        return weatherRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        weatherRepository.deleteById(id);
    }

    @Override
    public Weather getCurrentWeather() {
        Optional<Weather> lastSavedWeather = weatherRepository.findTopByOrderByTimestampDesc();

        if (lastSavedWeather.isPresent()) {
            Weather dbWeather = lastSavedWeather.get();
            Date lastHour = new Date(System.currentTimeMillis() - 3600 * 1000);

            if (!dbWeather.getTimestamp().before(lastHour)) {
                return dbWeather;
            }
        }
        Weather weather = getWeatherFromOpenWeatherApi();
        weather.setTimestamp(new Date());
        weatherRepository.save(weather);
        return weather;

    }

    private Weather getWeatherFromOpenWeatherApi() {
        OpenWeatherResponse openWeatherResponse = openWeatherClient.getCurrentWeather();
        Weather weather = modelMapper.map(openWeatherResponse, Weather.class);
        return weather;
    }
}
