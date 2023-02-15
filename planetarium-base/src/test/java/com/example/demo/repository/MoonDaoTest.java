package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import com.example.demo.RepositoryPropertiesHolder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import com.example.demo.entities.Moon;

@SpringBootTest
public class MoonDaoTest extends RepositoryPropertiesHolder {

    private static final Moon findByNameMoon = new Moon();
    private static final Moon deleteMoon = new Moon();

    @BeforeAll
    private static void setup(){
        findByNameMoon.setMoonId(1);
        findByNameMoon.setMoonName("getMoon");
        findByNameMoon.setPlanetId(1);

        deleteMoon.setMoonId(-1);
        deleteMoon.setMoonName("delete");
        deleteMoon.setPlanetId(1);


    }

    @Test
    public void findByMoonNamePositive(){
        Optional<Moon> possibleMoon = moonDao.findByMoonName(findByNameMoon.getMoonName());
        assumeTrue(possibleMoon.isPresent());
        assertEquals(findByNameMoon.hashCode(), possibleMoon.get().hashCode());
    }

    @Test
    public void findByMoonNameNegative(){
        Optional<Moon> possibleMoon = moonDao.findByMoonName("does not exist");
        assertFalse(possibleMoon.isPresent());
    }

    @Test
    public void deleteMoonPositive(){
        assertEquals(1, moonDao.deleteMoon(deleteMoon.getMoonName()));
    }

    @Test
    public void deleteMoonNegative(){
        assertEquals(0, moonDao.deleteMoon("does not exist"));
    }
    
}
