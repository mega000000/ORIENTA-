package com.orientaplus.orientabackend.gamification;

import com.orientaplus.orientabackend.learningpath.LearningStep;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class StepProgress {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_path_id")
    private UserPath userPath;

    @ManyToOne
    @JoinColumn(name = "step_id")
    private LearningStep step;

    private String status; // "PENDING" or "COMPLETED"
    private LocalDateTime completedAt;

    public StepProgress(){}

    public StepProgress(UserPath userPath, LearningStep step){
        this.userPath = userPath;
        this.step = step;
        this.status = "PENDING";
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public UserPath getUserPath() { return userPath; }
    public void setUserPath(UserPath userPath) { this.userPath = userPath; }

    public LearningStep getStep() { return step; }
    public void setStep(LearningStep step) { this.step = step; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
}