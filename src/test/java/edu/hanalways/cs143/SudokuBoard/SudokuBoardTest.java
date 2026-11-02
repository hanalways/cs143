package edu.hanalways.cs143.SudokuBoard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static edu.hanalways.cs143.SudokuBoard.constants.FILE_NAME;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {

    @Test
    @DisplayName("setting data from valid file is in order")
    public void testSetData() throws FileNotFoundException {
        SudokuBoard board = new SudokuBoard(FILE_NAME);
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

        SudokuBoard board = new SudokuBoard(FILE_NAME);

        assertEquals(expected.trim(), board.toString().trim());
    }
}
