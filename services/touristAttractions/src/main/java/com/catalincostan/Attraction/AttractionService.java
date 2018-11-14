package com.catalincostan.Attraction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
class AttractionService {

    @Autowired
    private AttractionRepository repo;

    public List<Attraction> getAllAttractions() {
        return (List<Attraction>) repo.findAll();
    }

    public Optional<Attraction> getById(Long id) {
        return repo.findById(id);
    }
}
