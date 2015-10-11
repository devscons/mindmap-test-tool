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
 * Unit test for simple App.
 */
@RunWith(Parameterized.class)
@Ignore
public class AddTest {

    public AddTest(TestData test) {
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
        get("/api/add");

    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {createTestData(15, new Pair<String, Integer>("a", 5), new Pair<String, Integer>("b", 10))},
                {createTestData(25, new Pair<String, Integer>("a", 15), new Pair<String, Integer>("b", 10))},
                {createTestData(35, new Pair<String, Integer>("a", 20), new Pair<String, Integer>("b", 15))}
        });
    }
}
