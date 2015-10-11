package io.neueda.mmtester;

import java.util.Map;

/**
 * Created by k.slavnikovs on 10/9/2015.
 */
public class TestData {

    Integer result;
    Map<String, Integer> params;

    public TestData(Integer result, Map<String, Integer> params) {
        this.result = result;
        this.params = params;
    }

    public Integer getResult() {
        return result;
    }

    public Map<String, Integer> getParams() {
        return params;
    }
}
