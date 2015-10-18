package io.neueda.mmtester;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.*;
import static org.joox.JOOX.$;

/**
 * Created by cons on 18/10/15.
 */
public class MindmapDOMExtractorHelper {
    
    public static List<String> extractTestsFromScenario(String scenario){
        List<String> tests = $(scenario)
                .children()
                .filter(test ->
                                !$(test)
                                .attr("TEXT")
                                .contentEquals("Request")
                )
                .contents();
        if (tests.size() <= 0) throw new MindmapParseException("no tests for scenarion: " + scenario);
        return tests;
    }

    public static String extractTestResult(String test){
        String result = $(test)
                .children()
                .matchTag("node")
                .filter(pa ->
                                $(pa)
                                .attr("TEXT")
                                .contains("result")
                )
                .attr("TEXT");
        if (isEmpty(result)) throw new MindmapParseException("no result for test: " + test);
        return getStringValue(result, ":");

    }

    public static Map<String, String> extractTestParams(String test){
        List<String> params = $(test)
                .children()
                .matchTag("node")
                .filter(param ->
                                !$(param)
                                .attr("TEXT")
                                .contains("result")
                )
                .attrs("TEXT");
        if (params.size() <= 0) throw new MindmapParseException("no params for test: " + test);
        return buildParams(params, ":");
    }

    public static String extractRequest(String scenario){
        String request = $(scenario)
                .children()
                .filter(req ->
                                $(req)
                                .attr("TEXT")
                                .contentEquals("Request")
                )
                .content();
        if (isEmpty(request)) throw new MindmapParseException("no request for scenario " + scenario);
        return request;
    }

    public static String extractRequestMethod(String request){
        String methodText = $(request)
                .children()
                .matchTag("node")
                .filter(pa ->
                                $(pa)
                                .attr("TEXT")
                                .contains("Method")
                )
                .attr("TEXT");
        if (isEmpty(methodText)) throw new MindmapParseException("no method for request: " + request);
        return getStringValue(methodText, ":");
    }

    public static String extractRequestPath(String request){
        String pathText = $(request)
                .children()
                .matchTag("node")
                .filter(pa ->
                                $(pa)
                                .attr("TEXT")
                                .contains("Path")
                )
                .attr("TEXT");
        if (isEmpty(pathText)) throw new MindmapParseException("no path for request: " + request);
        return getStringValue(pathText, ":");
    }

    public static Map<String, String> buildParams(List<String> par, String separator){
        Map<String, String> params = new HashMap<>();
        for (String keyValue : par){
            String[] param = keyValue.split(separator);
            if (param.length < 2) throw new MindmapParseException("param incorrect format, use " + separator + " separator");
            String key = param[0].trim();
            String value = param[1].trim();
            if (isEmpty(key) || key == null) throw new MindmapParseException("param name is missing");
            if (isEmpty(value) || value == null) throw new MindmapParseException("param value is missing");
            params.put(key, value);
        }
        return params;
    }

    public static String getStringValue(String keyValue, String separator){
        String[] param = keyValue.split(separator);
        if (param.length < 2) throw new MindmapParseException("param incorrect format, use " + separator + " separator");
        return keyValue.split(separator)[1].trim();
    }

}
