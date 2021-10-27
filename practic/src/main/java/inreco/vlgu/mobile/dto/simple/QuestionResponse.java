package inreco.vlgu.mobile.dto.simple;

import inreco.vlgu.mobile.model.Question;

import java.util.List;

public class QuestionResponse {
    private List<Question> questions;

    public QuestionResponse(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}