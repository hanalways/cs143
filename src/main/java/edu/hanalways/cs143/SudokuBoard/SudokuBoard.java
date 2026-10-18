package edu.hanalways.cs143.SudokuBoard;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static edu.hanalways.cs143.FileUtils.getFile;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class SudokuBoard {
    // single field - 2d array that will store the contents of the board
    // let's go with Character to allow the most wiggle room for representing null/empty cells
    ArrayList<Integer> data = new ArrayList<>();

    // modify constructor header to take filename then load the data directly into the 2D array
    public SudokuBoard(String fileName) throws FileNotFoundException {
        // figure out the path and retrieve the file from resources
        File file = getFile(fileName);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            String val = scanner.next();
            for (Character c : val.toCharArray()) {
                if (!c.toString().equals(".")) {
                    data.add(parseInt(valueOf(c)));
                } else {
                    data.add(null);
                }
            }
        }
    }

    public ArrayList<Integer> getData() {
        return data;
    }

    public String toString() {
        return data.toString();
    }
}
