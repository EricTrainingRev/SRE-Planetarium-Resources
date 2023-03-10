package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "moons")
public class Moon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moon_id")
    private int moonId;
    @Column(name = "moon_name")
    private String moonName;
    @Column(name = "planet_id")
    private int planetId;

}
