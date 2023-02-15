package com.example.demo;

import com.example.demo.repository.MoonDao;
import com.example.demo.repository.PlanetDao;
import com.example.demo.repository.UserDao;
import com.example.demo.service.MoonService;
import com.example.demo.service.PlanetService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public abstract class ServicePropertiesHolder {

    @MockBean
    protected UserDao userDao;

    @MockBean
    protected PlanetDao planetDao;

    @MockBean
    protected MoonDao moonDao;

    @Autowired
    protected UserService userService;

    @Autowired
    protected PlanetService planetService;

    @Autowired
    protected MoonService moonService;
}
