package io.neueda.mmtester;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.runners.Parameterized.*;
import static io.neueda.mmtester.Utils.*;

/**
 * Created by k.slavnikovs on 10/10/2015.
 */
@RunWith(Parameterized.class)
@Ignore
public class MultiplyTest {

    public MultiplyTest(TestData test) {
        this.test = test;
    }

    private TestData test;

    @Test
    public void test(){

        given().
            baseUri("http://calculator.neueda.lv").
            params(test.getParams()).
        expect().
            body("result", equalTo(String.valueOf(test.getResult()))).
        when().
                get("/api/multiply");

    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {createTestData(50, new Pair<String, Integer>("a", 5), new Pair<String, Integer>("b", 10))},
                {createTestData(150, new Pair<String, Integer>("a", 15), new Pair<String, Integer>("b", 10))},
                {createTestData(300, new Pair<String, Integer>("a", 20), new Pair<String, Integer>("b", 15))}
        });
    }
}
