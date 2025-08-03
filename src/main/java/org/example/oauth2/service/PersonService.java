package org.example.oauth2.service;

import lombok.RequiredArgsConstructor;
import org.example.oauth2.repositories.PersonRepository;
import org.example.oauth2.entitys.Person;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }

    @Transactional
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Transactional
    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return personRepository.existsById(id);
    }

    public List<Person> findByCity(String cityName) {
        return personRepository.findByCityName(cityName);
    }

    public List<Person> findYoungerThan(int age) {
        return personRepository.findByAgeLessThanOrderByAgeAsc(age);
    }

    public Optional<Person> findByNameAndSurname(String name, String surname) {
        return personRepository.findByNameAndSurname(name, surname);
    }

    public List<Person> findByAge(int age) {
        return personRepository.findByAge(age);
    }

    public List<Person> findByNameContaining(String name) {
        return personRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Person> findBySurnameContaining(String surname) {
        return personRepository.findBySurnameContainingIgnoreCase(surname);
    }
}