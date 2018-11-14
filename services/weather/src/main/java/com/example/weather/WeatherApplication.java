package com.example.weather;

import com.example.weather.aop.Retry;
import com.example.weather.boundry.controller.WeatherController;
import com.example.weather.control.client.OpenWeatherClient;
import com.example.weather.control.client.pojo.OpenWeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.validation.constraints.Null;


@SpringBootApplication
@EnableAspectJAutoProxy
public class WeatherApplication implements CommandLineRunner{

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);
    }

    @Autowired
    private WeatherController weatherController;
    @Override
    public void run(String... args) throws Exception {
        weatherController.test();
    }
}
