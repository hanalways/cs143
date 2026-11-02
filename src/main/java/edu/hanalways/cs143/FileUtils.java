package edu.hanalways.cs143;

import java.io.File;
import java.io.FileNotFoundException;

import static java.lang.Character.getNumericValue;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class FileUtils {
    // helper: retrieving given file from resources folder
    // see: https://mkyong.com/java/java-read-a-file-from-resources-folder/
    public static File getFile(String filename) throws FileNotFoundException {
        // Class.getResource() (old, non-static way) - filename has to start with '/' to look from the absolute root of the resource folder
        // Using a static thread context classloader
        // always looks from the absolute root of the classpath by default and because they are rooted at the top they do not accept a leading slash
        String path = filename.startsWith("/") ? filename.substring(1) : filename;
        java.net.URL resource = Thread.currentThread().getContextClassLoader().getResource(path);

        if (resource == null) {
            throw new FileNotFoundException(String.format("file not found: %s. Is it in the resources directory?", filename));
        } else {
            return new File(resource.getPath());
        }
    }

    // helper: parse file contents into board data
    public static Integer parseCell(char cell) {
        if (cell == '.') {
            return null;
        }

        return getNumericValue(cell);
    }
}
