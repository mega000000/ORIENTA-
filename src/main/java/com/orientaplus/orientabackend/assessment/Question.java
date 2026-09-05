package com.orientaplus.orientabackend.assessment;

import jakarta.persistence.*;

@Entity
public class Question {
    @Id
    @GeneratedValue
    private long id;
    private String text;
    private String dimension;
    private int orderIndex;
    @ManyToOne
    @JoinColumn(name="questionnaire_id")
   private Questionnaire questionnaire;

    public Question(){}
    public Question(String text, String dimension, int orderIndex, Questionnaire questionnaire) {

        this.text = text;
        this.dimension = dimension;
        this.orderIndex = orderIndex;
        this.questionnaire = questionnaire;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }
}

