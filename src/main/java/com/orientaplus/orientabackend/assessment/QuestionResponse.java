package com.orientaplus.orientabackend.assessment;

public class QuestionResponse {
    private long id;
    private String text;
    private int orderIndex;

    public QuestionResponse(){}
    public QuestionResponse(long id,String text, int orderIndex) {
        this.id=id;
        this.text = text;
        this.orderIndex = orderIndex;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getOrderIndex() {
        return orderIndex;
    }
}
