package com.augustorodriguez.maven.plugin.core;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: Augusto
 * Date: 23/03/13
 * Time: 18:50
 * To change this template use File | Settings | File Templates.
 */
public class IO {

    public static void closeQuietly(InputStream inputStream) {
        try {
            inputStream.close();
        } catch (IOException e) {}
    }
}
