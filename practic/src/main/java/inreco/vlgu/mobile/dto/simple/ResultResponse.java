package inreco.vlgu.mobile.dto.simple;

import inreco.vlgu.mobile.model.Attempt;
import inreco.vlgu.mobile.model.Result;

import java.util.List;

public class ResultResponse {
    private List<Result> results;

    public ResultResponse(List<Result> results) {
        this.results = results;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}