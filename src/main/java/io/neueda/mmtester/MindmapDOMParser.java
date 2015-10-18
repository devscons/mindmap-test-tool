package io.neueda.mmtester;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.joox.JOOX.$;
import static org.apache.commons.lang3.StringUtils.*;
import static io.neueda.mmtester.MindmapDOMExtractorHelper.*;
/**
 * Created by cons on 12/10/15.
 */
public class MindmapDOMParser implements MindmapParser {
    private static final Logger logger = LoggerFactory.getLogger(MindmapDOMParser.class);

    private String pathToMindmap;

    private String host;

    private MindmapDOMParser(){
    }

    private MindmapDOMParser(String pathToMindmap){
        this.pathToMindmap = pathToMindmap;
    }

    public static MindmapDOMParser withMindmap(String path){
        MindmapDOMParser parser = new MindmapDOMParser(path);
        return parser;
    }

    public MindmapDOMParser withHost(String host){
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

        if (scenarios.size() <= 0) throw new MindmapParseException("no scenarios in mindmap");

        for (String scenario : scenarios){

            String request = extractRequest(scenario);
            String method = extractRequestMethod(request);
            String path = extractRequestPath(request);

            testCases.addAll(extractTestCasesFromScenario(host, method, path, scenario));
        }
        return testCases;
    }

    private static Collection<TestCase> extractTestCasesFromScenario(String host, String method, String path, String scenario){

        List<String> tests = extractTestsFromScenario(scenario);

        Collection<TestCase> testDatas = new ArrayList<>();
        for (String test : tests)
        {
            Map<String, String> params = extractTestParams(test);

            String result = extractTestResult(test);

            TestCase testData = new TestCase(method,
                                            host,
                                            path,
                                            result,
                                            params);

            testDatas.add(testData);
        }
        return testDatas;
    }
}
