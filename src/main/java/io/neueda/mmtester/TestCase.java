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

    @Override
    public int hashCode() {
        int hashCode = 37;
        hashCode = 37 * hashCode + method.hashCode();
        hashCode = 37 * hashCode + host.hashCode();
        hashCode = 37 * hashCode + path.hashCode();
        hashCode = 37 * hashCode + result.hashCode();
        hashCode = 37 * hashCode + params.hashCode();
        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TestCase))
            return false;
        if (o == this)
            return true;

        TestCase that = (TestCase) o;

        if ((host == null) ? (that.getHost() != null) : !host.equals(that.getHost()))
            return false;
        if ((method == null) ? (that.getMethod() != null) : !method.equals(that.getMethod()))
            return false;
        if ((path == null) ? (that.getPath() != null) : !path.equals(that.getPath()))
            return false;
        if ((result == null) ? (that.getResult() != null) : !result.equals(that.getResult()))
            return false;
        if ((params == null) ? (that.getParams() != null) : !params.equals(that.getParams()))
            return false;

        return true;
    }
}
