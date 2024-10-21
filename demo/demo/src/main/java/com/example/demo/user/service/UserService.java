package com.example.demo.user.service;

import com.example.demo.user.model.User;
import com.example.demo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Method to save a new user
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Method to update an existing user by userId
    public User updateUser(String userId, User updatedUser) {
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            // Update fields
            existingUser.setName(updatedUser.getName());
            existingUser.setSurname(updatedUser.getSurname());
            existingUser.setStudentNr(updatedUser.getStudentNr());
            existingUser.setIdNumber(updatedUser.getIdNumber());
            existingUser.setCampus(updatedUser.getCampus());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setYearOfStudy(updatedUser.getYearOfStudy());
            existingUser.setPassword(updatedUser.getPassword());
            return userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found.");
        }
    }

    // Method to get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Method to get a user by userId
    public Optional<User> getUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    // Method to delete a user by userId
    public void deleteUser(String userId) {
        Optional<User> userOptional = userRepository.findByUserId(userId);
        userOptional.ifPresent(userRepository::delete);
    }

    // Method to save profile picture to the database for a user
    public void saveProfilePicture(String userId, MultipartFile file) throws IOException {
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setProfilePicture(file.getBytes()); // Convert the image to byte array and save it
            userRepository.save(user);
        } else {
            throw new RuntimeException("User not found.");
        }
    }

    // Method to retrieve the profile picture for a user
    public byte[] getProfilePicture(String userId) {
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if (userOptional.isPresent()) {
            return userOptional.get().getProfilePicture();
        } else {
            throw new RuntimeException("User not found.");
        }
    }

    // Method to authenticate a user by email and password (example method)
    public Optional<User> authenticateUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.filter(user -> user.getPassword().equals(password));  // Filter if the password matches
    }
}
