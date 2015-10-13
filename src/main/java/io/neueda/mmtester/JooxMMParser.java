package io.neueda.mmtester;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.joox.JOOX.$;

/**
 * Created by cons on 12/10/15.
 */
public class JooxMMParser implements MMParser {
    private static final Logger logger = LoggerFactory.getLogger(JooxMMParser.class);

    private String pathToMindmap;

    private String host;

    private JooxMMParser(){
    }

    private JooxMMParser(String pathToMindmap){
        this.pathToMindmap = pathToMindmap;
    }

    public static JooxMMParser withMindmap(String path){
        JooxMMParser parser = new JooxMMParser(path);
        return parser;
    }

    public JooxMMParser withHost(String host){
        this.host = host;
        return this;
    }

    public  String loadAsFilesystem(String path){
        try {
            return IOUtils.toString(new FileReader(path));
        } catch (FileNotFoundException e) {
            logger.info("Mindmap is not found at {}", path);
            return loadAsResource(path);
        } catch (IOException ioe){
            logger.info("Error reading mindmap at {}", path);
            ioe.printStackTrace();
        }
        return null;
    }

    public String loadAsResource(String path){
        try {
            if (getClass().getClassLoader().getResourceAsStream(path) == null) {
                logger.info("Mindmap is not found at {}", path);
            }
            return IOUtils.toString(getClass().getClassLoader().getResourceAsStream(path), "UTF-8");
        } catch (IOException ioe) {
            logger.info("Error reading mindmap at {}", path);
        }
        return null;
    }

    @Override
    public Collection<TestCase> build() {
        String xml = loadAsFilesystem(pathToMindmap);

        Collection<TestCase> testCases = new ArrayList<>();

        List<String> scenarios = $(xml).xpath("//node[@TEXT=\"Request\"]/..").contents();
        for (String scenario : scenarios){

            String method = $(scenario).children().filter(req -> $(req).attr("TEXT").contentEquals("Request")).children().matchTag("node").filter(pa -> $(pa).attr("TEXT").contains("Method")).attr("TEXT");

            String path = $(scenario).children().filter(req -> $(req).attr("TEXT").contentEquals("Request")).children().matchTag("node").filter(pa -> $(pa).attr("TEXT").contains("Path")).attr("TEXT");

            testCases.addAll(extractTestCasesFromScenario(host, method, path, scenario));
        }
        return testCases;
    }

    private Collection<TestCase> extractTestCasesFromScenario(String host, String method, String path, String scenario){
        List<String> tests = $(scenario).children().filter(test -> !$(test).attr("TEXT").contentEquals("Request")).contents();

        Collection<TestCase> testDatas = new ArrayList<>();
        for (String test : tests)
        {
            List<String> params = $(test).children().matchTag("node").filter(pa -> !$(pa).attr("TEXT").contains("result")).attrs("TEXT");

            String result = $(test).children().matchTag("node").filter(pa -> $(pa).attr("TEXT").contains("result")).attr("TEXT");

            TestCase testData = new TestCase(getStringValue(method, ":"),
                                            host,
                                            getStringValue(path, ":"),
                                            getStringValue(result, ":"),
                                            buildParams(params, ":"));

            testDatas.add(testData);
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
}
