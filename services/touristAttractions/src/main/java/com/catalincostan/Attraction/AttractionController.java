package com.catalincostan.Attraction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attractions")
public class AttractionController {

    @Autowired
    private AttractionService attractionService;

    @GetMapping
    public List<Attraction> getAllAttractions() {
        return attractionService.getAllAttractions();
    }

    @GetMapping("/{id}")
    public Optional<Attraction> getAttractionById(@PathVariable Long id) {
        return attractionService.getById(id);
    }
}
