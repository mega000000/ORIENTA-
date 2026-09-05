package com.orientaplus.orientabackend.recommendation;

public class RecommendationResponse {
    private long specialtyId;
    private String specialtyName;
    private double score;
    private String explanation;

    public RecommendationResponse(long specialtyId, String specialtyName, double score, String explanation){
        this.specialtyId = specialtyId;
        this.specialtyName = specialtyName;
        this.score = score;
        this.explanation = explanation;
    }

    public long getSpecialtyId() { return specialtyId; }
    public String getSpecialtyName() { return specialtyName; }
    public double getScore() { return score; }
    public String getExplanation() { return explanation; }
}