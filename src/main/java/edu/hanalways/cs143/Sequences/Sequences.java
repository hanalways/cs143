package edu.hanalways.cs143.Sequences;

import java.util.List;
import java.io.FileNotFoundException;

interface Sequences {
    public List<String> getDescriptions();
    public List<String> getSequences();
    public void readSequences(String fileName) throws FileNotFoundException;
    public boolean isValidSequence(int index);
    public String formatInGroups(int index, int basesPerGroup, int groupsPerLine);
}
