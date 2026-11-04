package edu.hanalways.cs143.SudokuBoard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static edu.hanalways.cs143.SudokuBoard.constants.FILE_NAME;
import static edu.hanalways.cs143.SudokuBoard.constants.SUDOKU_ROOT;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {

    @Test
    @DisplayName("setting data from valid file is in order")
    public void testSetData() throws FileNotFoundException {
        SudokuBoard board = new SudokuBoard(SUDOKU_ROOT + FILE_NAME);
        ArrayList<ArrayList<Integer>> boardList = board.getDataAsList();

        assertAll("data array",
                () -> assertFalse(boardList.isEmpty()),
                () -> assertEquals(new ArrayList<>(asList(2, null, null, 1, null, 5, null, null, 3)), boardList.getFirst()),
                () -> assertNull(boardList.get(1).getFirst(), "indicates an empty cell"),
                () -> assertEquals(new ArrayList<>(asList(7, null, null, 4, null, 2, null, null, 1)), boardList.getLast()),
                () -> assertEquals(9, boardList.size())
        );
    }

    @Test
    @DisplayName("the stringified board formats correctly")
    public void testToString() throws FileNotFoundException {
        String expected = "============ WELCOME TO CS143 SUDOKU ============\n" +
                " -----------------------------------------------\n" +
                "|[ 2 ][   ][   ]|[ 1 ][   ][ 5 ]|[   ][   ][ 3 ]|\n" +
                "|[   ][ 5 ][ 4 ]|[   ][   ][   ]|[ 7 ][ 1 ][   ]|\n" +
                "|[   ][ 1 ][   ]|[ 2 ][   ][ 3 ]|[   ][ 8 ][   ]|\n" +
                " -----------------------------------------------\n" +
                "|[ 6 ][   ][ 2 ]|[ 8 ][   ][ 7 ]|[ 3 ][   ][ 4 ]|\n" +
                "|[   ][   ][   ]|[   ][   ][   ]|[   ][   ][   ]|\n" +
                "|[ 1 ][   ][ 5 ]|[ 3 ][   ][ 9 ]|[ 8 ][   ][ 6 ]|\n" +
                " -----------------------------------------------\n" +
                "|[   ][ 2 ][   ]|[ 7 ][   ][ 1 ]|[   ][ 6 ][   ]|\n" +
                "|[   ][ 8 ][ 1 ]|[   ][   ][   ]|[ 2 ][ 4 ][   ]|\n" +
                "|[ 7 ][   ][   ]|[ 4 ][   ][ 2 ]|[   ][   ][ 1 ]|\n" +
                " -----------------------------------------------";

        SudokuBoard board = new SudokuBoard(SUDOKU_ROOT + FILE_NAME);

        assertEquals(expected.trim(), board.toString().trim());
    }

    // Maybe worth parameterizing these tests
    @Test
    public void testIsValidRow() throws FileNotFoundException {
        SudokuBoard board = new SudokuBoard(SUDOKU_ROOT + "mock-valid-row.sdk");

        assertTrue(board.isValidRow());
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9), board.getDataAsList().getFirst());
    }

    @Test
    public void testIsInvalidRow() throws FileNotFoundException {
        SudokuBoard board = new SudokuBoard(SUDOKU_ROOT + "mock-invalid-row.sdk");

        assertFalse(board.isValidRow());
    }

    @Test
    public void testIsValidColumn() throws FileNotFoundException {
        SudokuBoard board = new SudokuBoard(SUDOKU_ROOT + "mock-valid-col.sdk");

        assertTrue(board.isValidColumn());
    }

    @Test
    public void testIsInvalidColumn() throws FileNotFoundException {
        SudokuBoard board = new SudokuBoard(SUDOKU_ROOT + "mock-invalid-col.sdk");

        assertFalse(board.isValidColumn());
    }

    @Test
    public void testIsInvalidMinisquare() throws FileNotFoundException {
        SudokuBoard board = new SudokuBoard(SUDOKU_ROOT + "mock-invalid-minisquare.sdk");

        assertFalse(board.isValidMinisquare());
    }

    @Test
    public void testIsValidMinisquare() throws FileNotFoundException {
        SudokuBoard board = new SudokuBoard(SUDOKU_ROOT + "mock-valid-minisquare.sdk");

        assertTrue(board.isValidMinisquare());
    }

    @Test
    public void testIsSolved() throws FileNotFoundException {
        SudokuBoard board = new SudokuBoard(SUDOKU_ROOT + "mock-valid-board.sdk");

        assertTrue(board.isSolved());
    }

    @Test
    public void testIsSolvedReturnsFalse() throws FileNotFoundException {
        SudokuBoard board = new SudokuBoard(SUDOKU_ROOT + "data1-1-1.sdk");

        assertFalse(board.isSolved());
    }
}
