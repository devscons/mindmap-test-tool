package io.neueda.mmtester;

import com.jayway.restassured.internal.http.Method;
import com.jayway.restassured.specification.ResponseSpecification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.runners.Parameterized.Parameters;
import static io.neueda.mmtester.MindmapDOMParser.*;

/**
 * Parameterized test that is run for every Tests case
 */
@RunWith(Parameterized.class)
public class MindmapTest {
    private static final Logger logger = LoggerFactory.getLogger(MindmapTest.class);


    public MindmapTest(TestCase test) {
        this.test = test;
    }

    private TestCase test;

    @Test
    public void test(){

        ResponseSpecification spec =

    given().
        baseUri(test.getHost()).
        params(test.getParams()).
    log().
        path().
    log().
        method().
    log().
        params().
    expect().
        body("result", equalTo(String.valueOf(test.getResult()))).
    log().
        body().
    when();

        doRequest(spec);

    }

    private void doRequest(ResponseSpecification spec){
        if (test.getMethod().equals(Method.POST.toString())){
            spec.post(test.getPath());
        } else if (test.getMethod().equals(Method.GET.toString())) {
            spec.get(test.getPath());
        } else {
            spec.get(test.getPath());
        }
    }

    @Parameters
    public static Collection<Object[]> data() {
            Collection<TestCase> testCases = withMindmap(System.getProperty(Arguments.MINDMAP))
                    .withHost(System.getProperty(Arguments.HOST))
                    .build();
            return testCases.stream().map(s -> new Object[]{s}).collect(Collectors.toCollection(ArrayList::new));
    }
}
