package io.neueda.mmtester;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static io.neueda.mmtester.Utils.createTestData;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created by k.slavnikovs on 10/10/2015.
 */
public class SimpleAdd {

    @Test
    public void test(){
        TestData test = createTestData(15, new Pair<String, Integer>("a", 5), new Pair<String, Integer>("b", 10));

        given().
            baseUri("http://calculator.neueda.lv").
            params(test.getParams()).
        expect().
            body("result", equalTo(String.valueOf(test.getResult()))).
        when().
            get("/api/add");

    }

    @Test
    public void anotherTest(){
        TestData test = createTestData(20, new Pair<String, Integer>("a", 10), new Pair<String, Integer>("b", 10));

        given().
            baseUri("http://calculator.neueda.lv").
            params(test.getParams()).
        expect().
            body("result", equalTo(String.valueOf(test.getResult()))).
        when().
            get("/api/add");

    }

}
