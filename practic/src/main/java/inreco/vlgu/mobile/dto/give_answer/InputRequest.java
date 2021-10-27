package inreco.vlgu.mobile.dto.give_answer;


import javax.validation.constraints.NotBlank;

public class InputRequest {
    @NotBlank
    private long idQ;

    @NotBlank
    private long idA;

    public long getIdQ() {
        return idQ;
    }

    public void setIdQ(long idQ) {
        this.idQ = idQ;
    }

    public long getIdA() {
        return idA;
    }

    public void setIdA(long idA) {
        this.idA = idA;
    }
}