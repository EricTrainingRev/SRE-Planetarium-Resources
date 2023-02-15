package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.Optional;

import com.example.demo.RepositoryPropertiesHolder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entities.Planet;

@SpringBootTest
public class PlanetDaoTest extends RepositoryPropertiesHolder {

    private static final Planet getByNamePlanet = new Planet();
    private static final Planet deletePlanet = new Planet();

    @BeforeAll
    static void setup(){
        getByNamePlanet.setPlanetId(1);
        getByNamePlanet.setPlanetName("getPlanet");
        getByNamePlanet.setUserId(1);

        deletePlanet.setPlanetId(-1);
        deletePlanet.setPlanetName("delete");
        deletePlanet.setPlanetId(1);
    }

    @Test
    void findPlanetByPlanetNamePositive(){
        Optional<Planet> possiblePlanet = planetDao.findByPlanetName(getByNamePlanet.getPlanetName());
        assumeTrue(possiblePlanet.isPresent());
        assertEquals(getByNamePlanet.hashCode(), possiblePlanet.get().hashCode());
    }

    @Test
    void findPlanetByPlanetNameNegative(){
        Optional<Planet> possiblePlanet = planetDao.findByPlanetName("does not exist");
        assertFalse(possiblePlanet.isPresent());
    }

    @Test
    void deletePlanetPositive(){
        assertEquals(1, planetDao.deletePlanet(deletePlanet.getPlanetName()));
    }

    @Test
    void deletePlanetNegative(){
        assertEquals(0, planetDao.deletePlanet("does not exist"));
    }

}
