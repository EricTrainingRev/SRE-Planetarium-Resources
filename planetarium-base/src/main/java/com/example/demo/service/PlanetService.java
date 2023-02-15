package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Planet;
import com.example.demo.exceptions.EntityNotFound;
import com.example.demo.repository.PlanetDao;

import java.util.List;
import java.util.Optional;

@Service
public class PlanetService {
    
    @Autowired
    private PlanetDao planetDao;

    public List<Planet> findAll() {
        List<Planet> planets = this.planetDao.findAll();
        if (planets.size() == 0) {
            throw new EntityNotFound("No planets found");
        }
        return planets;
    }

    public Planet findByName(String name) {
        Optional<Planet> possiblePlanet = this.planetDao.findByPlanetName(name);
        if (possiblePlanet.isPresent()) {
            return possiblePlanet.get();
        } else {
            throw new EntityNotFound("No planet found");
        }   
    }

    public Planet findById(int id) {
        Optional<Planet> possiblePlanet = this.planetDao.findById(id);
        if (possiblePlanet.isPresent()) {
            return possiblePlanet.get();
        } else {
            throw new EntityNotFound("No planet found");
        }   
    }

    public Planet createPlanet(Planet planet) {
        return this.planetDao.save(planet);
    }

    public boolean deletePlanet(String name) {
        if (this.planetDao.deletePlanet(name) == 1){
            return true;
        } else {
            return false;
        }
    }
}
