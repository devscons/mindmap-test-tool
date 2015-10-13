package io.neueda.mmtester;

import org.junit.runner.JUnitCore;
import static io.neueda.mmtester.Arguments.*;

/**
 * MMTester
 * starts testing using mindmap
 *
 */
public class MindmapTesterStarter {
    public static void main( String[] args ){

//        System.setProperty(HOST, args[0]);
//        System.setProperty(MINDMAP, args[1]);

        System.setProperty(HOST, "http://calculator.neueda.lv");
        System.setProperty(MINDMAP, "calc_tests.mm");

        JUnitCore.main(MindmapTest.class.getCanonicalName());
    }

}
