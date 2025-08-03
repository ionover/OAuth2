package org.example.oauth2.repositories;


import org.example.oauth2.entitys.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findByName(String name);

    List<City> findByNameContainingIgnoreCase(String name);

    boolean existsByName(String name);
}