package com.catalincostan.Attraction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.junit4.*;

import static org.assertj.core.api.Assertions.*;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttractionApplicationTests {

	@Autowired
	private AttractionRepository _repo;

	@Autowired
	private AttractionService _service;

	@Test
	public void repoReturnsTheRightEntryById() {
		Optional<Attraction> atr = _repo.findById(0L);

		atr.ifPresent(attraction -> assertThat(attraction.getName()).isEqualTo("Palatul Culturii Iasi"));
	}

	@Test
	public void serviceReturnsTheRightEntryById() {
		Optional<Attraction> atr = _service.getById(0L);

		atr.ifPresent(attraction -> assertThat(attraction.getName()).isEqualTo("Palatul Culturii Iasi"));
	}

	@Test
	public void serviceAndRepoMatch() {
		Optional<Attraction> rep = _repo.findById(1L);
		Optional<Attraction> srv = _service.getById(1L);

		if (rep.isPresent() && srv.isPresent()) {
			assertThat(rep.get().getName()).isEqualTo(srv.get().getName());
		}
	}


}
