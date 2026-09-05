package com.orientaplus.orientabackend.assessment;

import java.util.List;

public class QuestionnaireResponse {


    long id;
    String title;
    List<QuestionResponse> questions;

    public QuestionnaireResponse(long id, String title, List<QuestionResponse> questions) {
        this.id = id;
        this.title = title;
        this.questions = questions;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<QuestionResponse> getQuestions() {
        return questions;
    }
}
