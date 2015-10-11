package io.neueda.mmtester;

import org.junit.runner.JUnitCore;

/**
 * MMTester
 * starts testing using mindmap
 *
 */
public class MMTester
{
    public static void main( String[] args ){

        for (String arg : args){
            System.out.println(arg);
        }

        ParameterizedAdd.host = args[0];
        ParameterizedAdd.pathToMindMap = args[1];

        JUnitCore.main(ParameterizedAdd.class.getCanonicalName());
    }
}
