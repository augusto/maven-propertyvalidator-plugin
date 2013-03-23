package com.augustorodriguez.maven.plugin.core;

import org.junit.Before;

import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Augusto
 * Date: 23/03/13
 * Time: 19:14
 * To change this template use File | Settings | File Templates.
 */
public class PropertyGroupTestBuilder {

    public static String getBasePath() {
        URL resource = PropertyValidatorTest.class.getClassLoader().getResource("testproperties");

        return resource.getFile();
    }

    public static PropertyGroup with(String ... files) throws PropertyValidatorException {
        PropertyGroup propertyGroup = new PropertyGroup();

        for(String file:files) {
            propertyGroup.addFile(getBasePath()+file);
        }
        return propertyGroup;
    }
}
