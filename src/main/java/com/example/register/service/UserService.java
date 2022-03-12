package com.example.register.service;

import com.example.register.model.UserModel;
import com.example.register.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public  UserModel registerUser(String username, String password, String email) {
        if (username == null && password == null) {
            return null;
        } else {
            if(userRepository.findFirstByUsername(username).isPresent()){
                System.out.println("username already exists");
                return null;
            }
            UserModel userModel = new UserModel();
            userModel.setUsername(username);
            userModel.setPassword(password);
            userModel.setEmail(email);
            return userRepository.save(userModel);
        }
    }
    public UserModel authenticate (String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).orElse(null);
    }
}
