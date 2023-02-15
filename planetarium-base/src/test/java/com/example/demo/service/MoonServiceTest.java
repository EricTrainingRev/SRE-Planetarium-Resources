package com.example.demo.service;

import com.example.demo.ServicePropertiesHolder;
import com.example.demo.entities.Moon;
import com.example.demo.exceptions.EntityNotFound;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MoonServiceTest extends ServicePropertiesHolder {

    private static final Moon stubMoon = new Moon();

    @BeforeAll
    public static void setup(){
        stubMoon.setMoonId(1);
        stubMoon.setMoonName("stubMoon");
        stubMoon.setPlanetId(1);
    }

    @Test
    void findAllMoonsPositive() {
        when(moonDao.findAll()).thenReturn(Arrays.asList(new Moon(), new Moon()));
        assertEquals(2, moonService.findAllMoons().size());
    }

    @Test
    void findAllMoonsNegative(){
        when(moonDao.findAll()).thenReturn(Collections.emptyList());
        EntityNotFound expectedException = assertThrows(
                EntityNotFound.class,
                () -> moonService.findAllMoons(),
                "EntityNotFound exception expected, but it was not thrown"
        );
        assertEquals("No moons found", expectedException.getMessage());
    }

    @Test
    void findByNamePositive() {
        when(moonDao.findByMoonName(stubMoon.getMoonName())).thenReturn(Optional.of(stubMoon));
        assertEquals(stubMoon.hashCode(), moonService.findByName(stubMoon.getMoonName()).hashCode());
    }

    @Test
    void findByNameNegative(){
        when(moonDao.findByMoonName(stubMoon.getMoonName())).thenReturn(Optional.empty());
        EntityNotFound expectedException = assertThrows(
                EntityNotFound.class,
                () -> moonService.findByName(stubMoon.getMoonName()),
                "EntityNotFound exception expected, but it was not thrown"
        );
        assertEquals("No moon found", expectedException.getMessage());
    }

    @Test
    void findByIdPositive() {
        when(moonDao.findById(stubMoon.getMoonId())).thenReturn(Optional.of(stubMoon));
        assertEquals(stubMoon.hashCode(), moonService.findById(stubMoon.getMoonId()).hashCode());
    }

    @Test
    void findByIdNegative(){
        when(moonDao.findById(stubMoon.getMoonId())).thenReturn(Optional.empty());
        EntityNotFound expectedException = assertThrows(
                EntityNotFound.class,
                () -> moonService.findById(stubMoon.getMoonId()),
                "EntityNotFound exception expected, but it was not thrown"
        );
        assertEquals("No moon found", expectedException.getMessage());
    }

    @Test
    void deleteMoonPositive() {
        when(moonDao.deleteMoon(stubMoon.getMoonName())).thenReturn(1);
        assertTrue(moonService.deleteMoon(stubMoon.getMoonName()));
    }

    @Test
    void deleteMoonNegative(){
        when(moonDao.deleteMoon(stubMoon.getMoonName())).thenReturn(0);
        assertFalse(moonService.deleteMoon(stubMoon.getMoonName()));
    }
}
