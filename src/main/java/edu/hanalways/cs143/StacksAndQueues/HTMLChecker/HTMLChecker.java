package edu.hanalways.cs143.StacksAndQueues.HTMLChecker;// CSE 143, Homework: HTML Validator
//
// Instructor-provided code.
// This program tests your HTML manager object


import java.util.*;
import java.io.*;

import static edu.hanalways.cs143.FileUtils.getFile;

public class HTMLChecker {
    public static void main(String[] args) throws FileNotFoundException {
        String failedTests = "";

//        File folder = new File("HTMLChecker/tests/");
        File folder = getFile("HTMLChecker/tests/");
        File[] listOfFiles = folder.listFiles();

        for (File file: listOfFiles) {
            if (file.isFile()) {
                String actualResult = check("HTMLChecker/tests/" + file.getName());
                String expectedResult = getManager("HTMLChecker/expected_output/" + file.getName().replace("html", "txt")).toString();
                if (actualResult.equals(expectedResult))
                    System.out.println("----> Result matches Expected Output!");
                else {
                    System.out.println("----> Something isn't working right! \nFixed HTML should be: \n" + expectedResult);
                    failedTests += file.getName() + " ";
                }
                System.out.println();
            }
        }
        
        System.out.println("===============================");
        if(failedTests.equals(""))
            System.out.println("        All tests passed!");
        else
            System.out.println("Failed tests: " + failedTests);
        System.out.println("===============================");
    }

    public static String check(String file) {
        System.out.println("===============================");
        System.out.println("Processing " + file + "...");
        System.out.println("===============================");

        HTMLManager manager = getManager(file);
        //System.out.println("Loaded tags: " + manager.getTags());
        System.out.println("HTML: " + manager);
        System.out.println("Checking HTML for errors...");
        manager.fixHTML();
        System.out.println("HTML after fix: " + manager);
        return manager.toString();
    }

    public static HTMLParser getParser(String path) {
        HTMLParser result = null;
        try {
            result = new HTMLParser(getFile(path));
        } catch (Exception e) {
            System.out.println("Something went wrong.");
            e.printStackTrace();
        }
        return result;
    }

    public static HTMLManager getManager(String file) {
        HTMLManager m = null;
        HTMLParser parser = getParser(file);
        if (parser != null) {
            Queue < HTMLTag > tags = parser.parse();
            m = new HTMLManager(tags);
        } else {
            System.err.println("Couldn't resolve input.  Try again!");
        }
        return m;
    }
}
