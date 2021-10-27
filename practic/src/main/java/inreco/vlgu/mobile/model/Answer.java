package inreco.vlgu.mobile.model;



import javax.persistence.*;

@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "number")
    private Integer number;

    @Column(name = "answer_text")
    private String anserText;

    @Column(name = "points")
    private Integer points;


    public Answer() {
    }

    public Answer(long id, Integer number, String anserText, Integer points) {
        this.id = id;
        this.number = number;
        this.anserText = anserText;
        this.points = points;
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

    public String getAnserText() {
        return anserText;
    }

    public void setAnserText(String anserText) {
        this.anserText = anserText;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}