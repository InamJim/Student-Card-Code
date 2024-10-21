package com.example.demo.user.controller;

import com.example.demo.user.model.User;
import com.example.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        Optional<User> user = userService.getUserByUserId(userId);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser( @RequestBody User user) {
        User createdUser = userService.saveUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User user) {
        User updatedUser = userService.updateUser(userId, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/profile-picture")
    public ResponseEntity<Void> uploadProfilePicture(@PathVariable String userId, @RequestParam("file") MultipartFile file) {
        try {
            userService.saveProfilePicture(userId, file);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(500).build(); // Consider more specific handling
        }
    }

    @GetMapping("/{userId}/profile-picture")
    public ResponseEntity<byte[]> getProfilePicture(@PathVariable String userId) {
        try {
            byte[] profilePicture = userService.getProfilePicture(userId);
            return ResponseEntity.ok(profilePicture);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
