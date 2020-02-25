package com.kolev.demo.repositories;

import com.kolev.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
    public User findByUsername(String username);
}
