package com.example.bsm.service;

import com.example.bsm.entity.User;
import com.example.bsm.exception.UserNotFoundException;
import com.example.bsm.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Create
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Read
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    // Update
    public User updateUser(Long userId, User updatedUser) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setRole(updatedUser.getRole());
            user.setPhoneNumber(updatedUser.getPhoneNumber());

            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // Delete
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }


}
