package org.kangspace.common;

import java.util.List;

public class Question{
    private Integer gradeId;
    private Long questionId;
    private Integer type;
    private String body;
    private List<Answer> answers;

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<org.kangspace.common.Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<org.kangspace.common.Answer> answers) {
        this.answers = answers;
    }
}