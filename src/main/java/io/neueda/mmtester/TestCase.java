package io.neueda.mmtester;

import java.util.Map;

/**
 * Created by k.slavnikovs on 10/9/2015.
 */
public class TestCase {

    String host;
    String method;
    String path;
    String result;
    Map<String, String> params;

    public TestCase(String method, String host, String path, String result, Map<String, String> params) {
        this.method = method;
        this.host = host;
        this.path = path;
        this.result = result;
        this.params = params;
    }

    public String getMethod() {
        return method;
    }

    public String getHost() {
        return host;
    }

    public String getPath() {
        return path;
    }

    public String getResult() {
        return result;
    }

    public Map<String, String> getParams() {
        return params;
    }
}
