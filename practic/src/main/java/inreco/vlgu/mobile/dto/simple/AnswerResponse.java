package inreco.vlgu.mobile.dto.simple;

import inreco.vlgu.mobile.model.Answer;

import java.util.List;

public class AnswerResponse {
    private List<Answer> answers;

    public AnswerResponse(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}