package com.springlab.controller;

import com.springlab.dao.UserRepository;
import com.springlab.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/user/id/{id}")
    public User findUserById(@PathVariable int id){
        return userRepository.findUserById(id);
    }

    @GetMapping(value = "/user/name/{username}")
    public User findUserByName(@PathVariable String username){
        return userRepository.findUserByName(username);
    }

    @GetMapping(value = "/users")
    public List<User> findUsers(){
        return userRepository.findAll();
    }

    @PostMapping(value = "/user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createdUser = userRepository.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

}
