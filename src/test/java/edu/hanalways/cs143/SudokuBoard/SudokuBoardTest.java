package edu.hanalways.cs143.SudokuBoard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {
    String FILE_NAME = "data1-1-1.sdk";

    @Test
    @DisplayName("setting data from valid file is in order")
    public void testSetData() throws FileNotFoundException {
        SudokuBoard board = new SudokuBoard(FILE_NAME);

        assertAll("data array",
                () -> assertFalse(board.getData().isEmpty()),
                () -> assertEquals(2, board.getData().getFirst()),
                () -> assertNull(board.getData().get(1), "indicates an empty cell"),
                () -> assertEquals(1, board.getData().getLast())
        );
    }
}
