package com.orientaplus.orientabackend.assessment;

import java.util.Map;

public class AssessmentResultResponse {
    private long sessionId;
    private Map<String, Double> scores;
    private String dominantCode;

    public AssessmentResultResponse(long sessionId, Map<String, Double> scores, String dominantCode){
        this.sessionId = sessionId;
        this.scores = scores;
        this.dominantCode = dominantCode;
    }

    public long getSessionId() { return sessionId; }
    public Map<String, Double> getScores() { return scores; }
    public String getDominantCode() { return dominantCode; }
}