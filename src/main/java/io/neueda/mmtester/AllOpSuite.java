package io.neueda.mmtester;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by k.slavnikovs on 10/10/2015.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        SimpleAdd.class,
        SimpleMultiply.class
})
public class AllOpSuite {
    public static String pathToMindMap;
}
