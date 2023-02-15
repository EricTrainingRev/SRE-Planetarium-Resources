package com.example.demo;

import com.example.demo.repository.MoonDao;
import com.example.demo.repository.PlanetDao;
import com.example.demo.repository.UserDao;
import com.example.demo.service.MoonService;
import com.example.demo.service.PlanetService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class RepositoryPropertiesHolder {
    @Autowired
    protected UserDao userDao;

    @Autowired
    protected PlanetDao planetDao;

    @Autowired
    protected MoonDao moonDao;

    @Autowired
    protected UserService userService;

    @Autowired
    protected PlanetService planetService;

    @Autowired
    protected MoonService moonService;

}
