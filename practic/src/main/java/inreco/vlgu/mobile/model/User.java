package inreco.vlgu.mobile.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user",schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password",nullable = false)
    @JsonIgnore
    private String password;
    @Column(name = "age")
    private Date age;
    @Column(name = "sex_m")
    private boolean sex_m;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private List<Attempt> attempts;


    public User() {
    }

    public User(String username, String password, Date age, boolean sexM) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.sex_m = sexM;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Attempt> getAttempts() {
        return attempts;
    }

    public void setAttempts(List<Attempt> attempts) {
        this.attempts = attempts;
    }

    public Date getAge() {
        return age;
    }

    public void setAge(Date age) {
        this.age = age;
    }

    public boolean isSexM() {
        return sex_m;
    }

    public void setSexM(boolean sexM) {
        this.sex_m = sexM;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

