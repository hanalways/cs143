package edu.hanalways.cs143.ArrayIntList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    // rewritten with junit6 and spent wayyyy too long adding gradle to this project because apparently i hate myself
    @DisplayName("adding elements pass the array capacity throws an exception")
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

    // timebox blitz to rewrite tests in junit6
    @DisplayName("adding elements at position shifts the rest of the elements correctly")
    @Test
    public void testAddElementAtPosition() throws Exception {
        addElementsToArray(test, 5);

        test.add(1, 100);
        assertEquals(100, test.get(1), "after adding elements the new position 1 should be");
        assertEquals(1, test.get(2), "after adding elements the new postion 2 should be");
        test.add(1, 200);
        assertEquals(200, test.get(1));
        assertEquals(100, test.get(2));
        assertEquals(1, test.get(3));
    }

    @DisplayName("adding elements should increase size correctly")
    @Test
    public void testAddAndSize() throws Exception {
        addElementsToArray(test, 7);
        assertEquals(7, test.size());
    }

    @DisplayName("removing elements at index should not have element")
    @Test
    public void testRemoveElementAtPositionAndGet() throws Exception {
        addElementsToArray(test, 6);
        test.remove(3);
        assertEquals(-1, test.indexOf(3));
    }

    private void addElementsToArray(ArrayIntList test, int n) throws Exception {
        for (int i = 0; i < n; i++) {
            test.add(i);
        }
    }
}

// output:
//hana@fuji cs143 % ./gradlew test
//WARNING: A restricted method in java.lang.System has been called
//WARNING: java.lang.System::load has been called by net.rubygrapefruit.platform.internal.NativeLibraryLoader in an unnamed module (file:/Users/hana/.gradle/wrapper/dists/gradle-9.6.0-bin/42k10rwplmzkhuboz9kdazi7s/gradle-9.6.0/lib/native-platform-0.22-milestone-29.jar)
//        WARNING: Use --enable-native-access=ALL-UNNAMED to avoid a warning for callers in this module
//        WARNING: Restricted methods will be blocked in a future release unless native access is enabled
//
//
//        > Task :test
//
//        ArrayIntListTest > adding elements pass the array capacity throws an exception PASSED
//
//        ArrayIntListTest > removing elements at index should not have element PASSED
//
//        ArrayIntListTest > adding elements should increase size correctly PASSED
//
//        ArrayIntListTest > adding elements at position shifts the rest of the elements correctly PASSED
//
//        BUILD SUCCESSFUL in 516ms
//        5 actionable tasks: 1 executed, 4 up-to-date
//        Consider enabling configuration cache to speed up this build: https://docs.gradle.org/9.6.0/userguide/configuration_cache_enabling.html
//        hana@fuji cs143 %
