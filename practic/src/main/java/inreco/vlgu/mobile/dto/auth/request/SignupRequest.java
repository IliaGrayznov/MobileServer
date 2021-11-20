package inreco.vlgu.mobile.dto.auth.request;

import java.sql.Date;

import javax.validation.constraints.*;

public class SignupRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private Date age;

    @NotBlank
    private boolean sexM;

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

    public Date getAge() {
        return age;
    }

    public void setAge(Date age) {
        this.age = age;
    }

    public boolean isSexM() {
        return sexM;
    }

    public void setSexM(boolean sexM) {
        this.sexM = sexM;
    }
}