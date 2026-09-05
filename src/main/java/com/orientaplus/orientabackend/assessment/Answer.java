package com.orientaplus.orientabackend.assessment;

import jakarta.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private AssessmentSession session;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private int value; // 1 to 5

    public Answer(){}

    public Answer(AssessmentSession session, Question question, int value){
        this.session = session;
        this.question = question;
        this.value = value;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public AssessmentSession getSession() { return session; }
    public void setSession(AssessmentSession session) { this.session = session; }

    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }

    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
}