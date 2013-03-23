package com.augustorodriguez.maven.plugin.core;

/**
 * Created with IntelliJ IDEA.
 * User: Augusto
 * Date: 23/03/13
 * Time: 18:44
 * To change this template use File | Settings | File Templates.
 */
public class PropertyValidatorException extends Exception {
    public PropertyValidatorException() {
        super();
    }

    public PropertyValidatorException(String message) {
        super(message);
    }

    public PropertyValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertyValidatorException(Throwable cause) {
        super(cause);
    }
}
