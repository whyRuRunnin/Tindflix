package com.example.register.repository;

import com.example.register.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByUsernameAndPassword(String username, String password);
    Optional<UserModel> findFirstByUsername (String username);

}
