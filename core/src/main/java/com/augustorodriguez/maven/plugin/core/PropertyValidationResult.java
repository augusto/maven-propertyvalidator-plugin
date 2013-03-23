package com.augustorodriguez.maven.plugin.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Augusto
 * Date: 23/03/13
 * Time: 08:35
 * To change this template use File | Settings | File Templates.
 */
public class PropertyValidationResult {

    private boolean success = true;
    private List<String> errors = new ArrayList<String>();


    public boolean isSuccess() {
        return success;
    }

    public void addError(String message) {
        success=false;
        errors.add(message);
    }

    public List<String> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "PropertyValidationResult{" +
                "success=" + success +
                ", errors=" + errors +
                '}';
    }
}
