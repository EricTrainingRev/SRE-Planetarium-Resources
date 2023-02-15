package com.example.demo.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import com.example.demo.ServicePropertiesHolder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import com.example.demo.entities.Planet;
import com.example.demo.exceptions.EntityNotFound;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PlanetServiceTest extends ServicePropertiesHolder {

    private static final Planet stubPlanet = new Planet();

    @BeforeAll
    public static void setup(){
        stubPlanet.setPlanetId(1);
        stubPlanet.setPlanetName("login");
        stubPlanet.setUserId(1);
    }

    @Test
    public void findAllPositive(){
        when(planetDao.findAll()).thenReturn(Arrays.asList(new Planet(), new Planet()));
        assertEquals(2, planetService.findAll().size());
    }

    @Test
    public void findAllNegative(){
        when(planetDao.findAll()).thenReturn(Collections.emptyList());
        EntityNotFound expectedException = assertThrows(
            EntityNotFound.class,
            () -> planetService.findAll(),
            "Expected EntityNotFound to be thrown, but it was not"
        );
        assertEquals("No planets found", expectedException.getMessage());
    }

    @Test
    public void findByNamePositive(){
        when(planetDao.findByPlanetName(stubPlanet.getPlanetName())).thenReturn(Optional.of(stubPlanet));
        assertEquals(stubPlanet.hashCode(), planetService.findByName(stubPlanet.getPlanetName()).hashCode() );
    }

    @Test
    public void findByNameNegative(){
        when(planetDao.findByPlanetName(stubPlanet.getPlanetName())).thenReturn(Optional.empty());
        EntityNotFound expectedException = assertThrows(
                EntityNotFound.class,
                () -> planetService.findByName(stubPlanet.getPlanetName()),
                "Expected EntityNotFound to be thrown, but it was not."
        );
        assertEquals("No planet found", expectedException.getMessage());
    }

    @Test
    public void findByIdPositive(){
        when(planetDao.findById(stubPlanet.getPlanetId())).thenReturn(Optional.of(stubPlanet));
        assertEquals(stubPlanet.hashCode(), planetService.findById(stubPlanet.getPlanetId()).hashCode());
    }
    @Test
    public void findByIdNegative(){
        when(planetDao.findById(stubPlanet.getPlanetId())).thenReturn(Optional.empty());
        EntityNotFound expectedException = assertThrows(
                EntityNotFound.class,
                () -> planetService.findById(stubPlanet.getPlanetId()),
                "Expected EntityNotFound to be thrown, but it was not."
        );
        assertEquals("No planet found", expectedException.getMessage());
    }

    @Test
    public void deletePlanetPositive(){
        when(planetDao.deletePlanet(stubPlanet.getPlanetName())).thenReturn(1);
        assertTrue(planetService.deletePlanet(stubPlanet.getPlanetName()));
    }

    @Test
    public void deletePlanetNegative(){
        when(planetDao.deletePlanet(stubPlanet.getPlanetName())).thenReturn(0);
        assertFalse(planetService.deletePlanet(stubPlanet.getPlanetName()));
    }
    
}
