package edu.hanalways.cs143.SudokuBoard;

import static edu.hanalways.cs143.SudokuBoard.constants.FILE_NAME;

public class PlaySudoku {
    static void main(String[] args) {
        try {
            SudokuBoard board = new SudokuBoard(FILE_NAME);
            System.out.println(board);
        } catch (Exception e) {
            System.out.println("Uh oh! Unexpected exception occurred: " + e);
        }
    }
}
