package com.augustorodriguez.maven.plugin.core;

import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: Augusto
 * Date: 23/03/13
 * Time: 19:05
 * To change this template use File | Settings | File Templates.
 */
public class PropertyGroupTest {

    @Test
    public void can_validate_one_empty_property_group() throws Exception {
        PropertyGroup propertyGroup = new PropertyGroup();

        PropertyValidationResult result = propertyGroup.validate();
        assertThat(result.isSuccess(), is(true));
    }

    @Test
    public void can_validate_one_property_group_with_two_empty_property_files() throws Exception{
        PropertyGroup propertyGroup = PropertyGroupTestBuilder.with("/empty.properties","/empty.properties");

        PropertyValidationResult result = propertyGroup.validate();
        assertThat(result.isSuccess(), is(true));
    }

    @Test
    public void can_validate_one_property_group_with_two_different_property_files() throws Exception {
        PropertyGroup propertyGroup = PropertyGroupTestBuilder.with("/one.properties","/empty.properties");

        PropertyValidationResult result = propertyGroup.validate();
        assertThat(result.isSuccess(), is(false));
        assertThat(result.getErrors().size(), equalTo(1));
    }

    @Test
    public void throws_exception_if_a_file_does_not_exist() {
        PropertyGroup propertyGroup = new PropertyGroup();
        try {
            propertyGroup.addFile(PropertyGroupTestBuilder.getBasePath()+"/does_not_exist.properties");
            fail("PropertyValidatorException expected");
        } catch (PropertyValidatorException pve) {
            assertThat(pve.getMessage(), containsString("/does_not_exist.properties does not exist"));
        }
    }

    @Test
    public void can_find_differences_in_more_than_two_files() throws Exception {
        PropertyGroup propertyGroup = PropertyGroupTestBuilder.with("/one.properties","/two.properties", "/one_and_two.properties");
        PropertyValidationResult result = propertyGroup.validate();
        assertThat(result.isSuccess(), is(false));
        assertThat(result.getErrors().size(), equalTo(4));
    }

    @Test
    public void can_report_an_error_when_configured_to_report_empty_properties() throws Exception {
        PropertyGroup propertyGroup = PropertyGroupTestBuilder.with("/one.properties","/empty_one.properties");
        propertyGroup.setErrorOnEmptyProperty(true);
        PropertyValidationResult result = propertyGroup.validate();
        assertThat(result.isSuccess(), is(false));
        assertThat(result.getErrors().size(), equalTo(1));
        assertThat(result.getErrors().get(0), containsString("Property 'one' is EMPTY in"));
    }

}
