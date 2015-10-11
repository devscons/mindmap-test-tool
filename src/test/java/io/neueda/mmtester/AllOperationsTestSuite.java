package io.neueda.mmtester;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by k.slavnikovs on 10/10/2015.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    ParameterizedAdd.class,
    MultiplyTest.class
})
@Ignore
public class AllOperationsTestSuite {
}
