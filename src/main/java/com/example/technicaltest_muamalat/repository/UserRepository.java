package com.example.technicaltest_muamalat.repository;

import com.example.technicaltest_muamalat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(int id);
    User findUserByUsername(String username);
    User findUserByEmail(String email);
}
