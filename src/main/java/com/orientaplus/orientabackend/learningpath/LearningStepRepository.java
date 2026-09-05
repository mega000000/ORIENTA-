package com.orientaplus.orientabackend.learningpath;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LearningStepRepository extends JpaRepository<LearningStep, Long> {
    List<LearningStep> findByPathOrderByOrderIndexAsc(LearningPath path);
}