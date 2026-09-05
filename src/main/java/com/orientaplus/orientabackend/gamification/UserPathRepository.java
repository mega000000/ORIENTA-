package com.orientaplus.orientabackend.gamification;

import com.orientaplus.orientabackend.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPathRepository extends JpaRepository<UserPath, Long> {
    List<UserPath> findByUser(User user);
}