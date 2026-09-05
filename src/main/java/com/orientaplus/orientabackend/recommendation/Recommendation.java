package com.orientaplus.orientabackend.recommendation;

import com.orientaplus.orientabackend.auth.User;
import com.orientaplus.orientabackend.specialty.Specialty;
import jakarta.persistence.*;

@Entity
public class Recommendation {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    private double score;
    private String explanation;
    private String algorithmVersion;

    public Recommendation(){}

    public Recommendation(User user, Specialty specialty, double score, String explanation, String algorithmVersion){
        this.user = user;
        this.specialty = specialty;
        this.score = score;
        this.explanation = explanation;
        this.algorithmVersion = algorithmVersion;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Specialty getSpecialty() { return specialty; }
    public void setSpecialty(Specialty specialty) { this.specialty = specialty; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }

    public String getExplanation() { return explanation; }
    public void setExplanation(String explanation) { this.explanation = explanation; }

    public String getAlgorithmVersion() { return algorithmVersion; }
    public void setAlgorithmVersion(String algorithmVersion) { this.algorithmVersion = algorithmVersion; }
}