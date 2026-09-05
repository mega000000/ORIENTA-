package com.orientaplus.orientabackend.assessment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class Questionnaire {

    @Id
    @GeneratedValue
    private long id;
    private String type;
    private String title;
    private boolean active;

    public Questionnaire(){}

    public Questionnaire(String type,String title){
        this.type=type;this.title=title;
        active=true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }



}
