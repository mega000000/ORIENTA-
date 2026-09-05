package com.orientaplus.orientabackend.learningpath;

import com.orientaplus.orientabackend.specialty.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LearningPathRepository extends JpaRepository<LearningPath, Long> {
    List<LearningPath> findBySpecialty(Specialty specialty);
}