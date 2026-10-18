// Hana Clements
// CS&143
// HW: Implement class for manipulating DNA sequence in plain format

package edu.hanalways.cs143.Sequences;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static edu.hanalways.cs143.FileUtils.getFile;

public class PlainSequences implements Sequences {
    private final List<String> descriptions;
    private final List<String> sequences;

    public PlainSequences() {
        descriptions = new ArrayList<>();
        sequences = new ArrayList<>();
    }

    // pre: none, always initialized as an empty array
    // post: returns a list of filenames used to populate sequences
    public List<String> getDescriptions() {
        return descriptions;
    }

    // pre: none, always initialized as an empty array
    // post: returns a list of sequences from given files
    public List<String> getSequences() {
        return sequences;
    }

    // pre: none
    // post: adds sequences and description from the given file
    public void readSequences(String filename) throws FileNotFoundException {
        try {
            File file = getFile(filename);
            Scanner input = new Scanner(file);
            StringBuilder sequence = new StringBuilder();

            while (input.hasNextLine()) {
                sequence.append(input.nextLine());
            }

            descriptions.add(filename);
            sequences.add(sequence.toString());
        } catch (Exception e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }

    // pre: none
    // post: returns boolean based on if there is a valid char
    // explicitly does not throw exception.
    public boolean isValidSequence(int index) {
        if (index >= sequences.size()) {
            return false;
        }

        // where tf is this ruleset defined?? I just copied this directly from the video (╥‸╥)
        String sequence = sequences.get(index);
        for (int i = 0; i < sequence.length(); i++) {
            char dnaBase = sequence.charAt(i);
            // this would be a lot less obnoxious if we just put it in an array and did a contains check
            if (!(dnaBase == ' ' || dnaBase == '\n' || dnaBase == 'A' || dnaBase == 'C' || dnaBase == 'G' || dnaBase == 'T' || dnaBase == 'U' || dnaBase == 'R' || dnaBase == 'Y' || dnaBase == 'K' || dnaBase == 'M' || dnaBase == 'S' || dnaBase == 'W' || dnaBase == 'B' || dnaBase == 'D' || dnaBase == 'H' || dnaBase == 'V' || dnaBase == 'N')) {
                return false;
            }
        }

        return true;
    }

    // pre: there is existing sequences
    // post: returns a string that formats sequences based on the group size (basesPerGroup) and number of groups per line (groupsPerLine)
    public String formatInGroups(int index, int basesPerGroup, int groupsPerLine) {
        String sequence = sequences.get(index);
        StringBuilder formatted = new StringBuilder();
        // increment so that it runs through the entire sequence
        int i = index;

        while (i < sequence.length()) {
            for (int line = 0; line < groupsPerLine && i < sequence.length(); line++) {
                for (int group = 0; group < basesPerGroup && i < sequence.length(); group++) {
                    char dnaBase = sequence.charAt(i++);
                    formatted.append(dnaBase);
                }

                if (i < sequence.length()) {
                    formatted.append(" ");
                }
            }

            if (i < sequence.length()) {
                formatted.append("\n");
            }
        }

        System.out.println(formatted);

        return formatted.toString();
    }

    // pre: an instance of PlainSequences that has already been given a seed of sequences via readSequences()
    // post: given an index to locate the exact sequence
    //      returns a map of string, integer pairs where each string is a substring of the given sequence
    //      and the integer is the frequency that the substring appears.
    public Map<String, Integer> generateFrequencies(int index) {
        Map<String, Integer> map = new HashMap<>();
        String sequence = sequences.get(index);

        for (int i = 0; i < sequence.length(); i++) {
            for (int j = i + 1; j <= sequence.length(); j++) {
                String substring = sequence.substring(i, j);
                map.put(substring, map.getOrDefault(substring, 0) + 1);
            }
        }

        return map;
    }
}
