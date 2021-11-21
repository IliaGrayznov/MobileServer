package inreco.vlgu.mobile.dto.admin;


public class ChangeTextRequest {
    private  String text;

    private  Long id;

    public ChangeTextRequest() {
    }

    public String getText() {
        return text;
    }

    public void setText(String name) {
        this.text = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}