package com.orientaplus.orientabackend.gamification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StepProgressRepository extends JpaRepository<StepProgress, Long> {
    List<StepProgress> findByUserPath(UserPath userPath);
}