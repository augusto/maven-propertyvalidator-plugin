package com.augustorodriguez.maven.plugin.core;

/**
 * Created with IntelliJ IDEA.
 * User: Augusto
 * Date: 23/03/13
 * Time: 08:19
 * To change this template use File | Settings | File Templates.
 */
public class PropertyValidator {
    public PropertyValidationResult verify(PropertyGroup propertyGroup) {
        PropertyValidationResult result = new PropertyValidationResult();

        return propertyGroup.validate();
    }
}
