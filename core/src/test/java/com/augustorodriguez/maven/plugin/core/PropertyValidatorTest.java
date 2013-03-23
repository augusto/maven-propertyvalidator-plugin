package com.augustorodriguez.maven.plugin.core;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Augusto
 * Date: 23/03/13
 * Time: 08:18
 * To change this template use File | Settings | File Templates.
 */
public class PropertyValidatorTest {

    @Test
    public void can_validate_an_empty_set_of_files() {
        PropertyValidator propertyValidator = new PropertyValidator();

        propertyValidator.verify();
    }


}
