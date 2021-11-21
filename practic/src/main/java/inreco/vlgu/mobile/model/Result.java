package inreco.vlgu.mobile.model;



import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "description")
    private String description;

    @Column(name = "points")
    private Integer points;

    @ManyToOne
    private Factor factor;



    public Result() {
    }

    public Result(Integer id, String description, Factor factor, Integer points) {
        this.id = id;
        this.description = description;
        this.factor = factor;
        this.points = points;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Factor getFactor() {
        return factor;
    }

    public void setFactor(Factor factor) {
        this.factor = factor;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}