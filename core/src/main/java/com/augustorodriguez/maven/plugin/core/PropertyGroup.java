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

    private List<PropertyFile> propertyFiles = new ArrayList<PropertyFile>();
    private boolean errorOnEmptyProperty = false;
    private static final String EMPTY_STRING = "";

    public void addFile(String filePath) throws PropertyValidatorException {
        PropertyFile propertyFile = new PropertyFile(filePath);
        propertyFiles.add(propertyFile);
    }

    public PropertyValidationResult validate() {
        PropertyValidationResult result = new PropertyValidationResult();

        for( PropertyFile propertiesToTest : propertyFiles) {
            Set<String> keySetToTest = (Set) propertiesToTest.keySet();
            for( String keyToTest : keySetToTest) {
                for(PropertyFile otherProperties : propertyFiles) {
                    if( propertiesToTest != otherProperties) {
                        if (! otherProperties.containsKey(keyToTest) ) {
                            result.addError("Property '" + keyToTest + "' is present in " + propertiesToTest.getFileName() + ", but it's present in " + otherProperties.getFileName());
                        }

                        if( errorOnEmptyProperty) {
                            String valueToTest = propertiesToTest.get(keyToTest).trim();
                            if(valueToTest.equals(EMPTY_STRING)) {
                                result.addError("Property '" + keyToTest + "' is EMPTY in " + propertiesToTest.getFileName());
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    public void setErrorOnEmptyProperty(boolean errorOnEmptyProperty) {
        this.errorOnEmptyProperty = errorOnEmptyProperty;
    }
}
