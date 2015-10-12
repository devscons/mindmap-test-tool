package io.neueda.mmtester;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.*;

import static org.joox.JOOX.$;

/**
 * Created by cons on 12/10/15.
 */
public class JooxMMParser implements MMParser {

    private String pathToMindmap;

    private String host;

    private String xml;

    private JooxMMParser(){
    }

    private JooxMMParser(String pathToMindmap){
        this.pathToMindmap = pathToMindmap;
    }

    public static JooxMMParser withMindmap(String pathToMindmap){
        return new JooxMMParser(pathToMindmap);
    }

    public JooxMMParser withHost(String host){
        this.host = host;
        return this;
    }

    public JooxMMParser loadXMLToString(){
        try {
            xml = IOUtils.toString(getClass().getClassLoader().getResourceAsStream(pathToMindmap), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public Collection<TestCase> build() {
        Collection<TestCase> testDatas = new ArrayList<>();

        List<String> scenarios = $(xml).xpath("//node[@TEXT=\"Request\"]/..").contents();
        for (String scenario : scenarios){

            String method = $(scenario).children().filter(req -> $(req).attr("TEXT").contentEquals("Request")).children().matchTag("node").filter(pa -> $(pa).attr("TEXT").contains("Method")).attr("TEXT");

            String path = $(scenario).children().filter(req -> $(req).attr("TEXT").contentEquals("Request")).children().matchTag("node").filter(pa -> $(pa).attr("TEXT").contains("Path")).attr("TEXT");

            List<String> tests = $(scenario).children().filter(test -> !$(test).attr("TEXT").contentEquals("Request")).contents();
            for (String test : tests)
            {
                List<String> params = $(test).children().matchTag("node").filter(pa -> !$(pa).attr("TEXT").contains("result")).attrs("TEXT");
                String result = $(test).children().matchTag("node").filter(pa -> $(pa).attr("TEXT").contains("result")).attr("TEXT");

                TestCase testData = new TestCase(getStringValue(method, ":"), host, getStringValue(path, ":"), getStringValue(result, ":"), buildParams(params, ":"));

                testDatas.add(testData);
            }
        }
        return testDatas;
    }

    private Map<String, String> buildParams(List<String> par, String separator){
        Map<String, String> params = new HashMap<>();
        for (String keyValue : par){
            String[] pair = keyValue.split(separator);
            String key = pair[0].trim();
            String value = pair[1].trim();
            params.put(key, value);
        }
        return params;
    }

    private String getStringValue(String keyValue, String separator){
        return keyValue.split(separator)[1].trim();
    }

    private Integer getIntegerValue(String keyValue, String separator){
        return Integer.valueOf(getStringValue(keyValue, separator));
    }
}
