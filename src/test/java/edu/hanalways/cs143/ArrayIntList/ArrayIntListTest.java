package edu.hanalways.cs143.ArrayIntList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayIntListTest {
    private ArrayIntList test;

    @BeforeEach
    void setup() {
        test = new ArrayIntList();
    }

    // junit4
//    @Test(expected = IllegalStateException.class)
//    public void testIllegalStateExceptionIsThrown() {
//        ArrayIntList test = new ArrayIntList();
//        for (int i = 0; i < 21; i++) {
//            test.add(i);
//        }
//    }

    // rewritten with junit6
    @Test
    public void testAddingPastCapacityThrowsException() {
        Exception e = assertThrows(IllegalStateException.class, () -> {
            for (int i = 0; i < 21; i++) {
                test.add(i);
            }
        });

        String expectedMsg = "would exceed list capacity";
        String actualMsg = e.getMessage();
        assertTrue(actualMsg.contains(expectedMsg));
    }
}
