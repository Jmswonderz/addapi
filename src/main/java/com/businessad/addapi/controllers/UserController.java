package com.businessad.addapi.controllers;

import com.businessad.addapi.entities.Advert;
import com.businessad.addapi.entities.User;
import com.businessad.addapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") String id) {
        User user = userRepository.getOne(id);

        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public User createdUser(@Validated @RequestBody User userDetails){
        return userRepository.save(userDetails);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> Update(@PathVariable(value = "id") String id, @Validated @RequestBody User entity) {
        var user = userRepository.getOne(id);
        user.setContact(entity.getContact());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setLocationId(entity.getLocationId());

        try {
            userRepository.save(user);
            return ResponseEntity.ok().body(entity);
        }catch (Exception ex){
            return ResponseEntity.ok().body(null);
        }
    }
}
