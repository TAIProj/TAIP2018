package com.catalincostan.Attraction;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface AttractionRepository extends CrudRepository<Attraction, Long> {
//    List<Attraction> findByName(String Name);
}
