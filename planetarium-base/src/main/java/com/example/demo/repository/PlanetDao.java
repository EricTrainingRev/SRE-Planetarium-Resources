package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Planet;

public interface PlanetDao extends JpaRepository<Planet, Integer> {

    Optional<Planet> findByPlanetName(String name);

    @Transactional
    @Modifying
    @Query(value = "delete from planets where planet_name = :name", nativeQuery = true)
    int deletePlanet(@Param("name") String name);

}
