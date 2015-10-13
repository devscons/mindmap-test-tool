package io.neueda.mmtester;

import org.junit.Test;

import java.util.Collection;

import static io.neueda.mmtester.JooxMMParser.withMindmap;
import static org.junit.Assert.*;
/**
 * Created by cons on 12/10/15.
 */
public class JooxMMParserTest {

    @Test
    public void buildTestDataTest(){

        Collection<TestCase> testCases = withMindmap("calc.mm")
                .withHost("http://calculator.neueda.lv/api/")
                .build();

        assertEquals(testCases.size(), 1);

        TestCase testCase = testCases.iterator().next();

        assertEquals("http://calculator.neueda.lv/api/", testCase.getHost());
        assertEquals("GET", testCase.getMethod());
        assertEquals("/api/add", testCase.getPath());
        assertEquals("14", testCase.getResult());
        assertEquals("6", testCase.params.get("a"));
        assertEquals("8", testCase.params.get("b"));
    }
}
