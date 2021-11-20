package inreco.vlgu.mobile.dto.account;

import inreco.vlgu.mobile.model.User;



public class AccountResponse {
   private Integer age;
   private  String username;
   private Boolean sexM;

    public AccountResponse() {
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getSexM() {
        return sexM;
    }

    public void setSexM(Boolean sexM) {
        this.sexM = sexM;
    }
}