package com.orientaplus.orientabackend.learningpath;

import java.util.List;

public class LearningStepResponse {
    private long id;
    private int orderIndex;
    private String title;
    private String objective;
    private int durationHours;
    private List<ResourceResponse> resources;

    public LearningStepResponse(long id, int orderIndex, String title, String objective, int durationHours, List<ResourceResponse> resources){
        this.id = id;
        this.orderIndex = orderIndex;
        this.title = title;
        this.objective = objective;
        this.durationHours = durationHours;
        this.resources = resources;
    }

    public long getId() { return id; }
    public int getOrderIndex() { return orderIndex; }
    public String getTitle() { return title; }
    public String getObjective() { return objective; }
    public int getDurationHours() { return durationHours; }
    public List<ResourceResponse> getResources() { return resources; }
}