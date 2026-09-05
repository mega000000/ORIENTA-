package com.orientaplus.orientabackend.recommendation;

import com.orientaplus.orientabackend.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findByUserOrderByScoreDesc(User user);
}