package com.orientaplus.orientabackend.assessment;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class AnswerRequest {
    @NotNull
    private Long questionId;

    @Min(1)
    @Max(5)
    private int value;

    public AnswerRequest(){}

    public Long getQuestionId() { return questionId; }
    public void setQuestionId(Long questionId) { this.questionId = questionId; }

    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
}