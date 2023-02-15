package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.User;

public interface UserDao extends JpaRepository<User, Integer> {

    Optional<User> findByUserUsername(String username);

}
