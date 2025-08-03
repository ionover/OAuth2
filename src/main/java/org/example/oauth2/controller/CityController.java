package org.example.oauth2.controller;


import lombok.RequiredArgsConstructor;
import org.example.oauth2.entitys.City;
import org.example.oauth2.repositories.CityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityRepository repository;

    @GetMapping
    public List<City> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> findById(@PathVariable Long id) {
        Optional<City> city = repository.findById(id);
        return city.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public City create(@RequestBody City city) {
        return repository.save(city);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> update(@PathVariable Long id, @RequestBody City city) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        city.setId(id);
        return ResponseEntity.ok(repository.save(city));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-name")
    public ResponseEntity<City> findByName(@RequestParam String name) {
        Optional<City> city = repository.findByName(name);
        return city.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<City> searchByName(@RequestParam String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}
