package com.orientaplus.orientabackend.assessment;

import com.orientaplus.orientabackend.auth.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class AssessmentSession {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "questionnaire_id")
    private Questionnaire questionnaire;

    private String status; // "IN_PROGRESS" or "COMPLETED"
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;

    public AssessmentSession(){}

    public AssessmentSession(User user, Questionnaire questionnaire){
        this.user = user;
        this.questionnaire = questionnaire;
        this.status = "IN_PROGRESS";
        this.startedAt = LocalDateTime.now();
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Questionnaire getQuestionnaire() { return questionnaire; }
    public void setQuestionnaire(Questionnaire questionnaire) { this.questionnaire = questionnaire; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }

    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
}