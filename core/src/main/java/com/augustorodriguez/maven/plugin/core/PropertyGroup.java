package com.augustorodriguez.maven.plugin.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Augusto
 * Date: 23/03/13
 * Time: 08:30
 * To change this template use File | Settings | File Templates.
 */
public class PropertyGroup {

    private List<String> filePaths = new ArrayList<String>();

    public void addFile(String filePath) {
        filePaths.add(filePath);
    }

    public PropertyValidationResult validate() {
        PropertyValidationResult result = new PropertyValidationResult();
        List<Properties> loadedProperties = new ArrayList<Properties>();
        for(String filePath:filePaths) {
            Properties properties = new Properties();
            File propertyFile = new File(filePath);
            if( propertyFile.exists() == false ) {
                result.addError("File " + filePath + " does not exist");
            } else if ( propertyFile.isFile() != true ) {
                result.addError("File " + filePath + " does not exist");
            } else if ( propertyFile.canRead() != true ) {
                result.addError("File " + filePath + " cannot be read - check if you have permissions to read it");
            } else {

                try {
                    FileInputStream inputStream = new FileInputStream(propertyFile);
                    properties.load(inputStream);
                    loadedProperties.add(properties);
                } catch (FileNotFoundException e) {
                    result.addError("Error reading file " + filePath + ": " + e.getMessage());
                } catch (IOException e) {
                    result.addError("Error reading file " + filePath + ": " + e.getMessage());
                }
            }
        }

        if( ! result.isSuccess() ){
            return result;
        }


        for( Properties propertiesToTest : loadedProperties) {
            Set<String> keySetToTest = (Set) propertiesToTest.keySet();
            for( String keyToTest : keySetToTest) {
                for(Properties otherProperties : loadedProperties) {
                    if( propertiesToTest != otherProperties) {

                        if (! otherProperties.contains(keyToTest) ) {
                            result.addError("one property is missing in one file!");
                        }
                    }


                }
            }
        }






        return result;
    }
}
