package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.Optional;

import com.example.demo.RepositoryPropertiesHolder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entities.User;

@SpringBootTest
public class UserDaoTest extends RepositoryPropertiesHolder {

    private static final User assertUser = new User();

    @BeforeAll
    static void setup(){
        assertUser.setUserId(1);
        assertUser.setUserUsername("login");
        assertUser.setUserPassword("user");
    }

    @Test
    void findByUserUsernamePositive(){
        Optional<User> possibleUser = userDao.findByUserUsername("login");
        assumeTrue(possibleUser.isPresent());
        assertEquals(assertUser.hashCode(), possibleUser.get().hashCode());
    }

    @Test
    void findByUserUsernameNegative(){
        Optional<User> possibleUser = userDao.findByUserUsername("does not exist");
        assertFalse(possibleUser.isPresent());
    }
    
}
