package com.project.hamakisasa.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        // Add additional logic, such as hashing passwords, if required
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        return null;
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }
    }
    // Method to authenticate users based on username and password
    public User authenticateUser(String username, String password) {
        // Query the repository to find a user with the provided username
        User user = userRepository.findByUsername(username);

        // If user is found and password matches, return the user
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null; // Authentication failed
        }
    }
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void save(User currentUser) {
    }

    public long getTotalUsers() {
        return userRepository.count();
    }
}

