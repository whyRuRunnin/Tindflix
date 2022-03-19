package com.example.register.service;

import com.example.register.model.Movie;
import com.example.register.model.User;
import com.example.register.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String username, String password, String email) {
        if (username == null && password == null) {
            return null;
        } else {
            if(userRepository.findFirstByUsername(username).isPresent()) {
                System.out.println("username already exists");
                return null;
            } else if (userRepository.findFirstByEmail(email).isPresent()) {
                System.out.println("email already exists");
                return null;
            }
            User userModel = new User();
            userModel.setUsername(username);
            userModel.setPassword(password);
            userModel.setEmail(email);
            return userRepository.save(userModel);
        }
    }
    public User authenticate (String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).orElse(null);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}