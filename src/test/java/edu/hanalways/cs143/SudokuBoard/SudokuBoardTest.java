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
}
