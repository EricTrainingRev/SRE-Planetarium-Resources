package com.example.demo.service;

import com.example.demo.ServicePropertiesHolder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import com.example.demo.entities.User;
import com.example.demo.exceptions.AuthenticationFailed;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest extends ServicePropertiesHolder {

    private static final User authenticationUser = new User();

    @BeforeAll
    public static void setup(){
        authenticationUser.setUserId(1);
        authenticationUser.setUserUsername("login");
        authenticationUser.setUserPassword("user");
    }

    @Test
    public void authenticatePositive(){
        when(userDao.findByUserUsername(authenticationUser.getUserUsername())).thenReturn(Optional.of(authenticationUser));
        assertEquals("login complete", userService.authenticate(authenticationUser));
    }

    @Test
    public void authenticateNegative(){
        when(userDao.findByUserUsername(authenticationUser.getUserUsername())).thenReturn(Optional.empty());
        AuthenticationFailed expectedException = assertThrows(
            AuthenticationFailed.class,
            () -> userService.authenticate(authenticationUser),
            "Expected AuthenticationFailed exception to be thrown, but it was not."
            );
            assertTrue(expectedException.getMessage().contentEquals("login attempt failed"));
    }
    
}
