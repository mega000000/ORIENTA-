package com.orientaplus.orientabackend.gamification;

import com.orientaplus.orientabackend.auth.User;
import com.orientaplus.orientabackend.learningpath.LearningPath;
import jakarta.persistence.*;

@Entity
public class UserPath {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "path_id")
    private LearningPath path;

    private String status; // "IN_PROGRESS" or "COMPLETED"
    private double progress; // 0 to 100

    public UserPath(){}

    public UserPath(User user, LearningPath path){
        this.user = user;
        this.path = path;
        this.status = "IN_PROGRESS";
        this.progress = 0;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LearningPath getPath() { return path; }
    public void setPath(LearningPath path) { this.path = path; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getProgress() { return progress; }
    public void setProgress(double progress) { this.progress = progress; }
}