// Hana Clements
// CS143
// HW #3: Sudoku #3 (solve)
// This program will create a Sudoku board given a seed file
//      print the board in an ascii format
//      check if the board is in a valid state
//      check if the whole board is solved
//      solve a board that was seeded validly

package edu.hanalways.cs143.SudokuBoard;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static edu.hanalways.cs143.FileUtils.getFile;
import static edu.hanalways.cs143.FileUtils.parseCell;
import static edu.hanalways.cs143.SudokuBoard.constants.ROW_SUBSET;
import static edu.hanalways.cs143.SudokuBoard.constants.SUDOKU_SUBSET;
import static java.lang.String.valueOf;

// TODO: Scale this out to an infinite (n) board, given a minibox size.
public class SudokuBoard implements Sudoku {
    // single field - 2d array that will store the contents of the board
    final private Integer[][] data = new Integer[SUDOKU_SUBSET][SUDOKU_SUBSET];

    // modify constructor header to take filename then load the data directly into the 2D array
    public SudokuBoard(String fileName) throws FileNotFoundException {
        // figure out the path and retrieve the file from resources
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
            row.addAll(Arrays.asList(data[r]).subList(0, SUDOKU_SUBSET));
            list.add(row);
        }

        return list;
    }

    // pre: a valid instance of a SudokuBoard
    // post: returns a really badly ascii-formatted string representation of the board
    public String toString() {
        String horizontalDivider = " -----------------------------------------------\n";

        StringBuilder sb = new StringBuilder();
        sb.append("============ WELCOME TO CS143 SUDOKU ============\n");

        for (int r = 0; r < SUDOKU_SUBSET; r++) {
            if (r % ROW_SUBSET == 0) sb.append(horizontalDivider);
            for (int c = 0; c < SUDOKU_SUBSET; c++) {
                if (c % ROW_SUBSET == 0) sb.append("|");

                sb.append("[ ");
                sb.append(formatCell(data[r][c]));
                sb.append(" ]");
            }
            sb.append("|\n");
        }

        sb.append(horizontalDivider);
        return sb.toString();
    }

    // helper: format board data values to string
    private String formatCell(Integer val) {
        return (val == null) ? " " : String.valueOf(val);
    }

    /**
     * pre: instantiated, seeded SudokuBoard
     * post: boolean check to determine if the whole board is valid against the following rules:
     * there is no data in the board that is not a 1-9 or space/null
     * no row contains any duplicate values of 1-9
     * no mini-square contains any duplicate values of 1-9
     */
    public boolean isValid() {
        boolean isValidRow = isValidRow();
        boolean isValidColumn = isValidColumn();
        boolean isValidMiniSquare = isValidMinisquare();

        return isValidRow && isValidColumn && isValidMiniSquare;
    }

    // pre: instantiated, seeded SudokuBoard
    // post: boolean check to determine if the row does not contain duplicate values of 1-9
    protected boolean isValidRow() {
        for (int r = 0; r < 9; r++) {
            Set<Integer> checkedValues = new HashSet<>();

            for (int c = 0; c < 9; c++) {
                Integer cell = data[r][c];

                if (cell != null) {
                    if (checkedValues.contains(cell)) {
                        return false;
                    } else {
                        checkedValues.add(cell);
                    }
                }
            }
        }

        return true;
    }

    // pre: instantiated, seeded SudokuBoard
    // post: boolean check to determine if the column does not contain duplicate values of 1-9
    protected boolean isValidColumn() {
        for (int c = 0; c < 9; c++) {
            Set<Integer> checkedValues = new HashSet<>();

            for (int r = 0; r < 9; r++) {
                Integer cell = data[r][c];

                // Could probs refactor this to a helper that passes the set and the cell
                // but, it's for like 5 lines of code so whatever
                if (cell != null) {
                    if (checkedValues.contains(cell)) {
                        return false;
                    } else {
                        checkedValues.add(cell);
                    }
                }

            }
        }

        return true;
    }

    // pre: instantiated, seeded SudokuBoard
    // post: boolean check to determine if the subsquare (3x3 minigrid) does not contain duplicate values of 1-9
    protected boolean isValidMinisquare() {
        for (int r = 0; r < 9; r += 3) {
            for (int c = 0; c < 9; c += 3) {
                Set<Integer> checkedValues = new HashSet<>();

                for (int miniR = 0; miniR < 3; miniR++) {
                    for (int miniC = 0; miniC < 3; miniC++) {
                        Integer cell = data[r + miniR][c + miniC];

                        if (cell != null) {
                            if (checkedValues.contains(cell)) {
                                return false;
                            } else {
                                checkedValues.add(cell);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    // pre: instantiated, seeded SudokuBoard
    // post: boolean check to determine if the entire board is solved based on the isValid ruleset
    //      and no cells are null
    public boolean isSolved() {
        // must be valid layout
        if (!isValid()) {
            return false;
        }

        // must be completely filled (no nulls)
        for (int r = 0; r < SUDOKU_SUBSET; r++) { // totes forgot about the const for 9, too lazy to go back and update isValid
            for (int c =0; c < SUDOKU_SUBSET; c++) {
                if (data[r][c] == null) {
                    return false;
                }
            }
        }

        return true;
    }

    // pre: an instantiated SudokuBoard
    // post: recursive backtrack attempt to solve the given SudokuBoard
    //      returns true if the board is solved, false if it cannot be solved
    public boolean solve() {
        boolean boardSolved = false;

        if (!isValid()) {
            return false;
        } else if (isSolved()) {
            return true;
        } else {
            for (int r = 0; r < SUDOKU_SUBSET; r++) {
                for (int c = 0; c < SUDOKU_SUBSET; c++) {
                    if (data[r][c] == null) {
                        // found an empty row start trying all digits 1-9 to see if any lead to a solution
                        for (int val = 1; val <= SUDOKU_SUBSET && !boardSolved; val++) {
                            data[r][c] = val;
                            boardSolved = solve();
                        }
                        if (!boardSolved) {
                            data[r][c] = null;
                        }
                    }
                }
            }
        }

        return boardSolved;
    }
}

//3:36:34 PM: Executing ':edu.hanalways.cs143.SudokuBoard.SudokuSolverEngineRecursion.main()'…
//
//> Task :compileJava
//> Task :processResources UP-TO-DATE
//> Task :classes
//
//> Task :edu.hanalways.cs143.SudokuBoard.SudokuSolverEngineRecursion.main()
//Verifying behavior of an invalid board
//Can this board be solved?: false
//Verifying behavior of a pre-solved board
//Is this board already solved?: true
//Initial board
//============ WELCOME TO CS143 SUDOKU ============
// -----------------------------------------------
//|[   ][ 3 ][ 4 ]|[ 6 ][ 7 ][ 8 ]|[ 9 ][ 1 ][ 2 ]|
//|[   ][ 7 ][ 2 ]|[ 1 ][ 9 ][ 5 ]|[ 3 ][ 4 ][ 8 ]|
//|[ 1 ][ 9 ][ 8 ]|[ 3 ][ 4 ][ 2 ]|[ 5 ][ 6 ][ 7 ]|
// -----------------------------------------------
//|[   ][   ][ 9 ]|[   ][ 6 ][ 1 ]|[ 4 ][ 2 ][ 3 ]|
//|[   ][ 2 ][ 6 ]|[ 8 ][ 5 ][ 3 ]|[ 7 ][ 9 ][ 1 ]|
//|[   ][ 1 ][ 3 ]|[ 9 ][ 2 ][ 4 ]|[   ][ 5 ][ 6 ]|
// -----------------------------------------------
//|[   ][ 6 ][ 1 ]|[ 5 ][ 3 ][ 7 ]|[ 2 ][ 8 ][ 4 ]|
//|[   ][ 8 ][   ]|[ 4 ][ 1 ][ 9 ]|[ 6 ][ 3 ][ 5 ]|
//|[ 3 ][ 4 ][ 5 ]|[   ][ 8 ][ 6 ]|[ 1 ][ 7 ][ 9 ]|
// -----------------------------------------------
//
//
//Solving board...SOLVED in 0.549 seconds.
//
//============ WELCOME TO CS143 SUDOKU ============
// -----------------------------------------------
//|[ 5 ][ 3 ][ 4 ]|[ 6 ][ 7 ][ 8 ]|[ 9 ][ 1 ][ 2 ]|
//|[ 6 ][ 7 ][ 2 ]|[ 1 ][ 9 ][ 5 ]|[ 3 ][ 4 ][ 8 ]|
//|[ 1 ][ 9 ][ 8 ]|[ 3 ][ 4 ][ 2 ]|[ 5 ][ 6 ][ 7 ]|
// -----------------------------------------------
//|[ 8 ][ 5 ][ 9 ]|[ 7 ][ 6 ][ 1 ]|[ 4 ][ 2 ][ 3 ]|
//|[ 4 ][ 2 ][ 6 ]|[ 8 ][ 5 ][ 3 ]|[ 7 ][ 9 ][ 1 ]|
//|[ 7 ][ 1 ][ 3 ]|[ 9 ][ 2 ][ 4 ]|[ 8 ][ 5 ][ 6 ]|
// -----------------------------------------------
//|[ 9 ][ 6 ][ 1 ]|[ 5 ][ 3 ][ 7 ]|[ 2 ][ 8 ][ 4 ]|
//|[ 2 ][ 8 ][ 7 ]|[ 4 ][ 1 ][ 9 ]|[ 6 ][ 3 ][ 5 ]|
//|[ 3 ][ 4 ][ 5 ]|[ 2 ][ 8 ][ 6 ]|[ 1 ][ 7 ][ 9 ]|
// -----------------------------------------------
//
//
//BUILD SUCCESSFUL in 685ms
//3 actionable tasks: 2 executed, 1 up-to-date
//Consider enabling configuration cache to speed up this build: https://docs.gradle.org/9.6.0/userguide/configuration_cache_enabling.html
//3:36:35 PM: Execution finished ':edu.hanalways.cs143.SudokuBoard.SudokuSolverEngineRecursion.main()'.
