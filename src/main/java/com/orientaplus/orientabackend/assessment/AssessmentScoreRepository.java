package com.orientaplus.orientabackend.assessment;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssessmentScoreRepository extends JpaRepository<AssessmentScore, Long> {
    List<AssessmentScore> findBySession(AssessmentSession session);
}