package org.example.oauth2.repositories;


import org.example.oauth2.entitys.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByCityName(String cityName);

    List<Person> findByAgeLessThanOrderByAgeAsc(int age);

    Optional<Person> findByNameAndSurname(String name, String surname);

    List<Person> findByAge(int age);

    List<Person> findByNameContainingIgnoreCase(String name);

    List<Person> findBySurnameContainingIgnoreCase(String surname);
}
