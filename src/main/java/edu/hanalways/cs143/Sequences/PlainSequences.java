// Hana Clements
// CS&143
// HW: Implement class for manipulating DNA sequence in plain format

package edu.hanalways.cs143.Sequences;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PlainSequences implements Sequences {
    // pre:
    // post:
    public List<String> getDescriptions() {
        return new ArrayList<>();
    }

    // pre:
    // post:
    public List<String> getSequences() {
        return new ArrayList<>();
    }

    // pre:
    // post:
    public void readSequences(String filename) throws FileNotFoundException {
        if (filename == null) {
            throw new FileNotFoundException("whoa no file found dude");
        }
    }

    // pre:
    // post:
    public boolean isValidSequence(int index) {
        return false;
    }

    // pre:
    // post:
    public String formatInGroups(int index, int basesPerGroup, int groupsPerLine) {
        return "Howdy!";
    }
}
