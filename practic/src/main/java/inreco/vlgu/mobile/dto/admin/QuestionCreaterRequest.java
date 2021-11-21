package inreco.vlgu.mobile.dto.admin;


public class QuestionCreaterRequest {
   private Integer number;
   private  String text;
   private Boolean plus;
   private Long factor_id;

    public QuestionCreaterRequest() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getPlus() {
        return plus;
    }

    public void setPlus(Boolean plus) {
        this.plus = plus;
    }

    public Long getFactor_id() {
        return factor_id;
    }

    public void setFactor_id(Long factor_id) {
        this.factor_id = factor_id;
    }
}