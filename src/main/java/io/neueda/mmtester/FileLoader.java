package io.neueda.mmtester;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by cons on 13/10/15.
 */
public class FileLoader {
    private static final Logger logger = LoggerFactory.getLogger(FileLoader.class);

    public String loadAsFilesystem(String path){
        try {
            return IOUtils.toString(new FileReader(path));
        } catch (FileNotFoundException e) {
            logger.info("Mindmap is not found at {}", path);
            return loadAsResource(path);
        } catch (IOException ioe){
            logger.info("Error reading mindmap at {}", path);
            ioe.printStackTrace();
        }
        return null;
    }

    public String loadAsResource(String path){
        try {
            if (getClass().getClassLoader().getResourceAsStream(path) == null) {
                logger.info("Mindmap is not found at {}", path);
            }
            return IOUtils.toString(getClass().getClassLoader().getResourceAsStream(path), "UTF-8");
        } catch (IOException ioe) {
            logger.info("Error reading mindmap at {}", path);
        }
        return null;
    }

    public static void main(String[] args){

        FileLoader fl = new FileLoader();

        //String fullpathres = fl.loadAsResource("/Users/cons/mindmap-test-tool/src/main/resources/calc_tests.mm");

        //String xml = fl.loadAsResource("calc_tests.mm");

        //String fullpath = fl.loadAsFilesystem("/Users/cons/mindmap-test-tool/src/main/resources/calc_tests.mm");

        String fullpathF = fl.loadAsFilesystem("calc_tests.mm");


        //System.out.println(xml);
        //System.out.println(fullpath);
        //System.out.println(fullpathres);
        System.out.println(fullpathF);


    }
}
