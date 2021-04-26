package com.itransition.mikrise2.demo.repos;

import com.itransition.mikrise2.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
