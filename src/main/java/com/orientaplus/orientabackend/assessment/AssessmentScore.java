package com.orientaplus.orientabackend.assessment;

import jakarta.persistence.*;

@Entity
public class AssessmentScore {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private AssessmentSession session;

    private String dimension; // R, I, A, S, E, C
    private double score; // 0 to 100

    public AssessmentScore(){}

    public AssessmentScore(AssessmentSession session, String dimension, double score){
        this.session = session;
        this.dimension = dimension;
        this.score = score;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public AssessmentSession getSession() { return session; }
    public void setSession(AssessmentSession session) { this.session = session; }

    public String getDimension() { return dimension; }
    public void setDimension(String dimension) { this.dimension = dimension; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }
}