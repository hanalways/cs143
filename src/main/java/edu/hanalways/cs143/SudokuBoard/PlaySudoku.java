// Hana Clements
// CS 143
// HW #1: SudokuBoard
//
// This program will store a given file seed to a 2d array data structure
// Includes a toString method that returns the board in a formatted string.

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

// 2:28:05 PM: Executing ':edu.hanalways.cs143.SudokuBoard.PlaySudoku.main()'…
//
//> Task :compileJava
//> Task :processResources UP-TO-DATE
//> Task :classes
//
//> Task :edu.hanalways.cs143.SudokuBoard.PlaySudoku.main()
//============ WELCOME TO CS143 SUDOKU ============
// -----------------------------------------------
//|[ 2 ][   ][   ]|[ 1 ][   ][ 5 ]|[   ][   ][ 3 ]|
//|[   ][ 5 ][ 4 ]|[   ][   ][   ]|[ 7 ][ 1 ][   ]|
//|[   ][ 1 ][   ]|[ 2 ][   ][ 3 ]|[   ][ 8 ][   ]|
// -----------------------------------------------
//|[ 6 ][   ][ 2 ]|[ 8 ][   ][ 7 ]|[ 3 ][   ][ 4 ]|
//|[   ][   ][   ]|[   ][   ][   ]|[   ][   ][   ]|
//|[ 1 ][   ][ 5 ]|[ 3 ][   ][ 9 ]|[ 8 ][   ][ 6 ]|
// -----------------------------------------------
//|[   ][ 2 ][   ]|[ 7 ][   ][ 1 ]|[   ][ 6 ][   ]|
//|[   ][ 8 ][ 1 ]|[   ][   ][   ]|[ 2 ][ 4 ][   ]|
//|[ 7 ][   ][   ]|[ 4 ][   ][ 2 ]|[   ][   ][ 1 ]|
// -----------------------------------------------
//
//
//BUILD SUCCESSFUL in 80ms
//3 actionable tasks: 2 executed, 1 up-to-date
//Consider enabling configuration cache to speed up this build: https://docs.gradle.org/9.6.0/userguide/configuration_cache_enabling.html
//2:28:05 PM: Execution finished ':edu.hanalways.cs143.SudokuBoard.PlaySudoku.main()'.
