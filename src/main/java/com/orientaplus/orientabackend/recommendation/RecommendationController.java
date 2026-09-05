package com.orientaplus.orientabackend.recommendation;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService){
        this.recommendationService = recommendationService;
    }

    @PostMapping("/recommendations/generate/{sessionId}")
    public ResponseEntity<List<RecommendationResponse>> generateRecommendations(@PathVariable long sessionId){
        return ResponseEntity.ok(recommendationService.generateRecommendationsResponse(sessionId));
    }

    @GetMapping("/recommendations/latest")
    public ResponseEntity<List<RecommendationResponse>> getLatestRecommendations(Authentication authentication){
        long userId = Long.parseLong((String) authentication.getPrincipal());
        return ResponseEntity.ok(recommendationService.getLatestRecommendations(userId));
    }
}