package edu.hanalways.cs143.SudokuBoard;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static edu.hanalways.cs143.FileUtils.getFile;
import static edu.hanalways.cs143.FileUtils.parseCell;
import static edu.hanalways.cs143.SudokuBoard.constants.SUDOKU_SUBSET;
import static java.lang.String.valueOf;

public class SudokuBoard {
    // single field - 2d array that will store the contents of the board
    final private Integer[][] data = new Integer[SUDOKU_SUBSET][SUDOKU_SUBSET];

    // modify constructor header to take filename then load the data directly into the 2D array
    public SudokuBoard(String fileName) throws FileNotFoundException {
        File file = getFile(fileName);
        Scanner scanner = new Scanner(file);

        for (int r = 0; r < SUDOKU_SUBSET; r++) {
            String fullRow = scanner.next();
            for (int c = 0; c < SUDOKU_SUBSET; c++) {
                data[r][c] = parseCell(fullRow.charAt(c));
                String cell = valueOf(fullRow.charAt(c));
                if (!cell.equals(".")) {
                    data[r][c] = parseCell(fullRow.charAt(c));
                }
            }
        }
    }

    // fancy data getter for unit tests
    // pre: a valid instance of a SudokuBoard
    // post: returns nested array lists of the data
    public ArrayList<ArrayList<Integer>> getDataAsList() {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int r = 0; r < SUDOKU_SUBSET; r++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int c = 0; c < SUDOKU_SUBSET; c++) {
                row.add(data[r][c]);
            }
            list.add(row);
        }

        return list;
    }
}
