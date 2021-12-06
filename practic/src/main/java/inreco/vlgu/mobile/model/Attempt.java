package inreco.vlgu.mobile.model;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "attempt")
public class Attempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "finished")
    private boolean finished;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JsonManagedReference
    private User user;

    @OneToMany (fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "attempt_id")
    private List<Result> results;

    @OneToMany (fetch = FetchType.LAZY,
                cascade = CascadeType.ALL)
    @JoinColumn(name = "attempt_id")
    @JsonBackReference
    private List<AnswerTOquestion> anserTOquestionList;

    public Attempt() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public List<AnswerTOquestion> getAnserTOquestionList() {
        return anserTOquestionList;
    }

    public void setAnserTOquestionList(List<AnswerTOquestion> anserTOquestionList) {
        this.anserTOquestionList = anserTOquestionList;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}