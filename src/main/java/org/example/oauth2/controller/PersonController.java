package org.example.oauth2.controller;



import lombok.RequiredArgsConstructor;
import org.example.oauth2.entitys.Person;
import org.example.oauth2.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public List<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) {
        Optional<Person> person = personService.findById(id);
        return person.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Person create(@RequestBody Person person) {
        return personService.save(person);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody Person person) {
        if (!personService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        person.setId(id);
        return ResponseEntity.ok(personService.save(person));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!personService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        personService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-city")
    public List<Person> findByCity(@RequestParam String city) {
        return personService.findByCity(city);
    }

    @GetMapping("/younger-than")
    public List<Person> findYoungerThan(@RequestParam int age) {
        return personService.findYoungerThan(age);
    }

    @GetMapping("/by-name-and-surname")
    public ResponseEntity<Person> findByNameAndSurname(@RequestParam String name, @RequestParam String surname) {
        Optional<Person> person = personService.findByNameAndSurname(name, surname);
        return person.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-age")
    public List<Person> findByAge(@RequestParam int age) {
        return personService.findByAge(age);
    }

    @GetMapping("/by-name")
    public List<Person> findByName(@RequestParam String name) {
        return personService.findByNameContaining(name);
    }

    @GetMapping("/by-surname")
    public List<Person> findBySurname(@RequestParam String surname) {
        return personService.findBySurnameContaining(surname);
    }
}
