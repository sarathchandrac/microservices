package com.training.mobile.controller;

import com.training.mobile.model.User;
import com.training.mobile.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserDaoService userService;

    @GetMapping("/get-users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping(value = "{user-id}", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<User> getUser(@PathVariable("user-id") int userId){
        return userId<=0
            ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
            : ResponseEntity.ok().body(userService.getUser(userId));
    }

    @PostMapping("/save-user")
    public User saveUser(User user){
        return userService.saveUser(user);
    }
    @PutMapping("/create-user")
    public User createUser(User user){
        return userService.saveUser(user);
    }
}
