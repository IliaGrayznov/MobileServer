package inreco.vlgu.mobile.model;




import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;


@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "number")
    private Integer number;

    @Column(name = "question_text")
    private String questionText;

    @Column(name = "plus")
    private boolean plus;

    @ManyToOne
    private Factor factor;


    public Question() {
    }

    public Question(Integer id, Integer number, String questionText, boolean plus, Factor factor) {
        this.id = id;
        this.number = number;
        this.questionText = questionText;
        this.plus = plus;
        this.factor = factor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public boolean isPlus() {
        return plus;
    }

    public void setPlus(boolean plus) {
        this.plus = plus;
    }

    public Factor getFactor() {
        return factor;
    }

    public void setFactor(Factor factor) {
        this.factor = factor;
    }
}