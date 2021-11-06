package inreco.vlgu.mobile.dto.simple;

import inreco.vlgu.mobile.model.Answer;

import java.util.List;

public class SomeQuestionRequest {
    private Integer first;

    private  Integer last;

    public SomeQuestionRequest(Integer first, Integer last) {
        this.first = first;
        this.last = last;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getLast() {
        return last;
    }

    public void setLast(Integer last) {
        this.last = last;
    }
}