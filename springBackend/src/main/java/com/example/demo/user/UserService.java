package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isEmpty()) {
            throw new IllegalStateException("User not found");
        }
        return user;
    }

    public void addNewUser(User user) {
        Optional<User> userEmailOptional = userRepository.findUserByEmail(user.getEmail());
        if (userEmailOptional.isPresent()) {
            throw new IllegalStateException("User with email " + user.getEmail() + " already exists");
        }

        userRepository.save(user);
    }
    public void deleteUser(String userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException("User with id " + userId + " does not exist");
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(String username, String name, String email) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new IllegalStateException(
                        "User with username " + username + " does not exist"));

        if (name != null && !name.isEmpty() && !Objects.equals(user.getName(), name)) {
            user.setName(name);
        }

        if (email != null && !email.isEmpty() && !Objects.equals(user.getEmail(), email)) {
            Optional<User> userOptional = userRepository.findUserByEmail(email);
            if (userOptional.isPresent()) {
                throw new IllegalStateException("Email taken");
            }
            user.setEmail(email);
        }
    }
}
