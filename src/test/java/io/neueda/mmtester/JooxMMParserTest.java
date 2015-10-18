package io.neueda.mmtester;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collection;

import static io.neueda.mmtester.MindmapDOMParser.withMindmap;
import static org.junit.Assert.*;
/**
 * Created by cons on 12/10/15.
 */
public class JooxMMParserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void correctMindmapTest(){

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

    @Test
    public void noScenariosInMindmapTest(){
        thrown.expect(MindmapParseException.class);
        thrown.expectMessage("no scenarios in mindmap");

        withMindmap("calc_no_request.mm")
            .withHost("http://calculator.neueda.lv/api/")
            .build();
    }
}
