package com.life.privacyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.life.privacyapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // We will use this later for login
    User findByEmail(String email);
}