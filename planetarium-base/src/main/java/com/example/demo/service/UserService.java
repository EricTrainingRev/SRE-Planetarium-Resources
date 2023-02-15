package com.example.demo.service;

import java.util.Optional;

import com.example.demo.exceptions.AuthenticationFailed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repository.UserDao;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User createUser(User newUser) {
        return this.userDao.save(newUser);
    }

    public String authenticate(User loginInfo) {
        Optional<User> possibleUser = this.userDao.findByUserUsername(loginInfo.getUserUsername());
        if(possibleUser.isPresent() && possibleUser.get().getUserPassword().equals(loginInfo.getUserPassword())){
            return "login complete";
        } else {
            throw new AuthenticationFailed("login attempt failed");
        }
    }
}
