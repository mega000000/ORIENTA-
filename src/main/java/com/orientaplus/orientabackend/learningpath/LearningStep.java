package com.orientaplus.orientabackend.learningpath;

import jakarta.persistence.*;

@Entity
public class LearningStep {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "path_id")
    private LearningPath path;

    private int orderIndex;
    private String title;
    private String objective;
    private int durationHours;

    public LearningStep(){}

    public LearningStep(LearningPath path, int orderIndex, String title, String objective, int durationHours){
        this.path = path;
        this.orderIndex = orderIndex;
        this.title = title;
        this.objective = objective;
        this.durationHours = durationHours;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public LearningPath getPath() { return path; }
    public void setPath(LearningPath path) { this.path = path; }

    public int getOrderIndex() { return orderIndex; }
    public void setOrderIndex(int orderIndex) { this.orderIndex = orderIndex; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getObjective() { return objective; }
    public void setObjective(String objective) { this.objective = objective; }

    public int getDurationHours() { return durationHours; }
    public void setDurationHours(int durationHours) { this.durationHours = durationHours; }
}