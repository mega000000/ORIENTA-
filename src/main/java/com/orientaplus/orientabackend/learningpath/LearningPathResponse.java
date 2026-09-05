package com.orientaplus.orientabackend.learningpath;

import java.util.List;

public class LearningPathResponse {
    private long id;
    private String title;
    private String level;
    private int estimatedWeeks;
    private List<LearningStepResponse> steps;

    public LearningPathResponse(long id, String title, String level, int estimatedWeeks, List<LearningStepResponse> steps){
        this.id = id;
        this.title = title;
        this.level = level;
        this.estimatedWeeks = estimatedWeeks;
        this.steps = steps;
    }

    public long getId() { return id; }
    public String getTitle() { return title; }
    public String getLevel() { return level; }
    public int getEstimatedWeeks() { return estimatedWeeks; }
    public List<LearningStepResponse> getSteps() { return steps; }
}