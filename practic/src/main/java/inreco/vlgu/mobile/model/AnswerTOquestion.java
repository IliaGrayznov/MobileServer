package inreco.vlgu.mobile.model;



import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "answer_to_question")
public class AnswerTOquestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne
    private Question question;

    @ManyToOne
    private Answer answer;

    @ManyToOne
    @JsonManagedReference
    private Attempt attempt;


    public AnswerTOquestion() {
    }

    public AnswerTOquestion(long id, Question question, Answer answer, Attempt attempt) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.attempt = attempt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Attempt getAttempt() {
        return attempt;
    }

    public void setAttempt(Attempt attempt) {
        this.attempt = attempt;
    }
}