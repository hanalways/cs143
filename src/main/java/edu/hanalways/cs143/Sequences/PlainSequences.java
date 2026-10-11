// Hana Clements
// CS&143
// HW: Implement class for manipulating DNA sequence in plain format

package edu.hanalways.cs143.Sequences;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    // helper: retrieving given file from resources folder
    // see: https://mkyong.com/java/java-read-a-file-from-resources-folder/
    private File getFile(String filename) throws Exception {
        // explicit check - filename has to start with '/' to look from the root of the resource folder
        String path = filename.startsWith("/") ? filename : "/" + filename;
        java.net.URL resource = getClass().getResource(path);

        if (resource == null) {
            throw new IllegalArgumentException("file not found! Is it in the resources directory?");
        } else {
            return new File(resource.getPath());
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

        System.out.println(formatted.toString());

        return formatted.toString();
    }
}
