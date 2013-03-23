package com.augustorodriguez.maven.plugin.core;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created with IntelliJ IDEA.
 * User: Augusto
 * Date: 23/03/13
 * Time: 08:18
 * To change this template use File | Settings | File Templates.
 */
public class PropertyValidatorTest {

    private static String testPropertiesBaseFolder;

    @BeforeClass
    public static void setup() {
        URL resource = PropertyValidatorTest.class.getClassLoader().getResource("testproperties");

        testPropertiesBaseFolder = resource.getFile();
    }

    @Test
    public void can_validate_one_empty_property_group() {
        PropertyValidator propertyValidator = new PropertyValidator();
        PropertyGroup propertyGroup = new PropertyGroup();


        PropertyValidationResult result = propertyValidator.verify(propertyGroup);
        assertThat(result.isSuccess(), is(true));
    }

    @Test
    public void can_validate_one_property_group_with_two_empty_property_files() {
        PropertyValidator propertyValidator = new PropertyValidator();
        PropertyGroup propertyGroup = new PropertyGroup();

        propertyGroup.addFile(testPropertiesBaseFolder+"/empty.properties");
        propertyGroup.addFile(testPropertiesBaseFolder+"/empty.properties");

        PropertyValidationResult result = propertyValidator.verify(propertyGroup);
        System.out.println(result);
        assertThat(result.isSuccess(), is(true));
    }

    @Test
    public void can_validate_one_property_group_with_two_different_property_files() {
        PropertyValidator propertyValidator = new PropertyValidator();
        PropertyGroup propertyGroup = new PropertyGroup();
        propertyGroup.addFile(testPropertiesBaseFolder+"/one.properties");
        propertyGroup.addFile(testPropertiesBaseFolder+"/empty.properties");

        PropertyValidationResult result = propertyValidator.verify(propertyGroup);
        assertThat(result.isSuccess(), is(false));
        assertThat(result.getErrors().size(), equalTo(1));
    }
}
