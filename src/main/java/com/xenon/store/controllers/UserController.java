package com.xenon.store.controllers;

import com.xenon.store.entities.User;
import com.xenon.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    @GetMapping("/users")
    public Iterable<User> getAllUsers(){
       return userRepository.findAll();
    }
}
