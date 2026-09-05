package com.orientaplus.orientabackend.recommendation;

import com.orientaplus.orientabackend.assessment.AssessmentScore;
import com.orientaplus.orientabackend.assessment.AssessmentScoreRepository;
import com.orientaplus.orientabackend.assessment.AssessmentSession;
import com.orientaplus.orientabackend.assessment.AssessmentSessionRepository;
import com.orientaplus.orientabackend.auth.User;
import com.orientaplus.orientabackend.auth.UserRepository;
import com.orientaplus.orientabackend.specialty.Specialty;
import com.orientaplus.orientabackend.specialty.SpecialtyRepository;
import org.springframework.stereotype.Service;

import java.util.*;

import com.orientaplus.orientabackend.exception.ResourceNotFoundException;

@Service
public class RecommendationService {

    private final AssessmentSessionRepository sessionRepository;
    private final AssessmentScoreRepository scoreRepository;
    private final SpecialtyRepository specialtyRepository;
    private final SpecialtyInterestProfileRepository profileRepository;
    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;

    public RecommendationService(AssessmentSessionRepository sessionRepository,
                                 AssessmentScoreRepository scoreRepository,
                                 SpecialtyRepository specialtyRepository,
                                 SpecialtyInterestProfileRepository profileRepository,
                                 RecommendationRepository recommendationRepository,
                                 UserRepository userRepository){
        this.sessionRepository = sessionRepository;
        this.scoreRepository = scoreRepository;
        this.specialtyRepository = specialtyRepository;
        this.profileRepository = profileRepository;
        this.recommendationRepository = recommendationRepository;
        this.userRepository = userRepository;
    }

    // ---------- MAIN LOGIC ----------

    public List<Recommendation> generateRecommendations(long sessionId){
        Optional<AssessmentSession> optionalSession = sessionRepository.findById(sessionId);
        if(optionalSession.isEmpty()){ throw new ResourceNotFoundException("Session Not Found"); }
        AssessmentSession session = optionalSession.get();
        User user = session.getUser();

        List<AssessmentScore> userScores = scoreRepository.findBySession(session);

        Map<String, Double> userVector = new HashMap<>();
        for(AssessmentScore score : userScores){
            userVector.put(score.getDimension(), score.getScore());
        }

        List<Specialty> specialties = specialtyRepository.findAll();
        List<Recommendation> recommendations = new ArrayList<>();

        for(Specialty specialty : specialties){
            SpecialtyInterestProfile profile = profileRepository.findBySpecialty(specialty);
            if(profile == null) continue;

            Map<String, Double> specialtyVector = new HashMap<>();
            specialtyVector.put("R", profile.getR());
            specialtyVector.put("I", profile.getI());
            specialtyVector.put("A", profile.getA());
            specialtyVector.put("S", profile.getS());
            specialtyVector.put("E", profile.getE());
            specialtyVector.put("C", profile.getC());

            double similarity = cosineSimilarity(userVector, specialtyVector);
            double finalScore = similarity * 100;

            String explanation = buildExplanation(userVector, specialty.getName());

            Recommendation recommendation = new Recommendation(user, specialty, finalScore, explanation, "v1.0");
            recommendations.add(recommendation);
        }

        recommendations.sort((a, b) -> Double.compare(b.getScore(), a.getScore()));

        List<Recommendation> top5 = recommendations.subList(0, Math.min(5, recommendations.size()));

        recommendationRepository.saveAll(top5);

        return top5;
    }

    // ---------- RESPONSE WRAPPERS (for controller) ----------

    public List<RecommendationResponse> generateRecommendationsResponse(long sessionId){
        List<Recommendation> recommendations = generateRecommendations(sessionId);
        return toResponseList(recommendations);
    }

    public List<RecommendationResponse> getLatestRecommendations(long userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){ throw new ResourceNotFoundException("User Not Found"); }
        User user = optionalUser.get();

        List<Recommendation> recommendations = recommendationRepository.findByUserOrderByScoreDesc(user);
        return toResponseList(recommendations);
    }

    private List<RecommendationResponse> toResponseList(List<Recommendation> recommendations){
        return recommendations.stream()
                .map(r -> new RecommendationResponse(
                        r.getSpecialty().getId(),
                        r.getSpecialty().getName(),
                        r.getScore(),
                        r.getExplanation()
                ))
                .toList();
    }

    // ---------- HELPERS ----------

    private double cosineSimilarity(Map<String, Double> vectorA, Map<String, Double> vectorB){
        String[] dimensions = {"R", "I", "A", "S", "E", "C"};

        double dotProduct = 0;
        double normA = 0;
        double normB = 0;

        for(String dimension : dimensions){
            double a = vectorA.getOrDefault(dimension, 0.0);
            double b = vectorB.getOrDefault(dimension, 0.0);

            dotProduct += a * b;
            normA += a * a;
            normB += b * b;
        }

        if(normA == 0 || normB == 0) return 0;

        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    private String buildExplanation(Map<String, Double> userVector, String specialtyName){
        String topUserDimension = userVector.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");

        return "Your strongest trait (" + topUserDimension + ") aligns well with " + specialtyName + ".";
    }
}