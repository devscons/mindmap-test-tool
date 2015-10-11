package io.neueda.mmtester;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by k.slavnikovs on 10/10/2015.
 */
public class Utils {

    public static TestData createTestData(Integer result, Pair... params){
        Collection<Pair> paramsCollection = Arrays.asList(params);
        Map paramsMap = new HashMap<>();
        for(Pair p : paramsCollection){
            paramsMap.put(p.getKey(), p.getValue());
        }
        return new TestData(result, paramsMap);
    };
}
