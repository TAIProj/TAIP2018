package com.example.weather;

import com.example.weather.entity.model.Weather;
import com.example.weather.entity.repository.WeatherRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WeatherRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private WeatherRepository weatherRepository;

    @Test
    public void whenFindTopByOrderByTimestampDesc_thenReturnWeather() {
        // given
        Date currentDate = new Date();

        Weather weather = new Weather();
        weather.setTimestamp(currentDate);
        entityManager.persist(weather);
        entityManager.flush();

        // when
        Optional<Weather> found = weatherRepository.findTopByOrderByTimestampDesc();

        assertThat(found.get().getTimestamp()).isEqualTo(currentDate);
    }
}
