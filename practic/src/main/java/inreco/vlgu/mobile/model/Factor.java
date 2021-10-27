package inreco.vlgu.mobile.model;



import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "factor")
public class Factor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "m_critic_male")
    private Double McriticMale;

    @Column(name = "q_critic_male")
    private Double QcriticMale;

    @Column(name = "m_critic_female")
    private Double McriticFemale;

    @Column(name = "q_critic_female")
    private Double QcriticFemale;


    public Factor() {
    }

    public Factor(Integer id, String name, Double mcriticMale, Double qcriticMale, Double mcriticFemale, Double qcriticFemale) {
        this.id = id;
        this.name = name;
        McriticMale = mcriticMale;
        QcriticMale = qcriticMale;
        McriticFemale = mcriticFemale;
        QcriticFemale = qcriticFemale;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMcriticMale() {
        return McriticMale;
    }

    public void setMcriticMale(Double mcriticMale) {
        McriticMale = mcriticMale;
    }

    public Double getQcriticMale() {
        return QcriticMale;
    }

    public void setQcriticMale(Double qcriticMale) {
        QcriticMale = qcriticMale;
    }

    public Double getMcriticFemale() {
        return McriticFemale;
    }

    public void setMcriticFemale(Double mcriticFemale) {
        McriticFemale = mcriticFemale;
    }

    public Double getQcriticFemale() {
        return QcriticFemale;
    }

    public void setQcriticFemale(Double qcriticFemale) {
        QcriticFemale = qcriticFemale;
    }
}