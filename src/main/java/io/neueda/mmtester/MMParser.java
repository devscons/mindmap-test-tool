package io.neueda.mmtester;

import java.util.Collection;

/**
 * Created by cons on 12/10/15.
 */
public interface MMParser {

    /**
     * Parses xml string to Collection of TestData
     * @return
     */
    Collection<TestCase> build();
}
