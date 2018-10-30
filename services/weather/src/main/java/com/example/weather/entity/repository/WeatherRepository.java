package com.example.weather.entity.repository;

import com.example.weather.entity.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {
    Optional<Weather> findTopByOrderByTimestampDesc();
}
