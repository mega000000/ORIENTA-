package com.orientaplus.orientabackend.learningpath;

import com.orientaplus.orientabackend.specialty.Specialty;
import jakarta.persistence.*;

@Entity
public class LearningPath {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    private String title;
    private String level;
    private int estimatedWeeks;

    public LearningPath(){}

    public LearningPath(Specialty specialty, String title, String level, int estimatedWeeks){
        this.specialty = specialty;
        this.title = title;
        this.level = level;
        this.estimatedWeeks = estimatedWeeks;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Specialty getSpecialty() { return specialty; }
    public void setSpecialty(Specialty specialty) { this.specialty = specialty; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public int getEstimatedWeeks() { return estimatedWeeks; }
    public void setEstimatedWeeks(int estimatedWeeks) { this.estimatedWeeks = estimatedWeeks; }
}