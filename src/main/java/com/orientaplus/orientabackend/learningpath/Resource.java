package com.orientaplus.orientabackend.learningpath;

import jakarta.persistence.*;

@Entity
public class Resource {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "step_id")
    private LearningStep step;

    private String type;
    private String title;
    private String provider;
    private String url;

    public Resource(){}

    public Resource(LearningStep step, String type, String title, String provider, String url){
        this.step = step;
        this.type = type;
        this.title = title;
        this.provider = provider;
        this.url = url;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public LearningStep getStep() { return step; }
    public void setStep(LearningStep step) { this.step = step; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}