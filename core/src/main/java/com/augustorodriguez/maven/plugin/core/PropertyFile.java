package com.augustorodriguez.maven.plugin.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Augusto
 * Date: 23/03/13
 * Time: 18:42
 * To change this template use File | Settings | File Templates.
 */
public class PropertyFile extends HashMap<String, String> {
    private String fileName;

    public PropertyFile(String fileName) throws PropertyValidatorException {
        this.fileName = fileName;
        assertFileIsReadable();
        readFile();
    }

    private void readFile() throws PropertyValidatorException {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(fileName);
            readFileInternal(inputStream);

        } catch (FileNotFoundException e) {
            throwError("Property file not found " + fileName + ": " + e.getMessage());
        } catch (IOException e) {
            throwError("Error reading file " + fileName + ": " + e.getMessage());
        } finally {
            IO.closeQuietly(inputStream);
        }

    }

    private void readFileInternal(FileInputStream inputStream) throws IOException {
        Properties properties = new Properties();
        properties.load(inputStream);

        this.putAll((Map) properties);
    }

    private void assertFileIsReadable() throws PropertyValidatorException {
        File propertyFile = new File(fileName);
        if( propertyFile.exists() == false ) {
            throwError("File " + fileName + " does not exist");
        } else if ( propertyFile.isFile() != true ) {
            throwError("File " + fileName + " does not exist");
        } else if ( propertyFile.canRead() != true ) {
            throwError("File " + fileName + " cannot be read - check if you have permissions to read it");
        }
    }

    private void throwError(String message) throws PropertyValidatorException {
        throw new PropertyValidatorException(message);
    }

    public String getFileName() {
        return fileName;
    }
}
