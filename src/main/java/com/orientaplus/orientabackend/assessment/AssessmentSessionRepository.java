package com.orientaplus.orientabackend.assessment;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssessmentSessionRepository extends JpaRepository<AssessmentSession, Long> {
}