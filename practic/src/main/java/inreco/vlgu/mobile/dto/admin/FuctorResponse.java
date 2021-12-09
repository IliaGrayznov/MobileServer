package inreco.vlgu.mobile.dto.admin;

import inreco.vlgu.mobile.model.Factor;

import java.util.List;

public class FuctorResponse {
    private List<Factor> factors;

    public FuctorResponse(List<Factor> factors) {
        this.factors = factors;
    }

    public List<Factor> getFactors() {
        return factors;
    }

    public void setFactors(List<Factor> factors) {
        this.factors = factors;
    }
}
