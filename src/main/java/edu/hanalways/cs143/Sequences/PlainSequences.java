// Hana Clements
// CS&143
// HW: Week 7 Use Stacks and Queues to compute the reverse complement of a dna sequence

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

        System.out.println(formatted.toString());

        return formatted.toString();
    }

    public Map<String, Integer> generateFrequencies(int index) {
        return Map.of();
    }

    public List<String> getSortedListOfSubstrings(int index) {
        return List.of();
    }

    public String getReverseComplement(int index) {
        String originalSequence = sequences.get(index);

        // take all the characters from the original sequence string
        // place them one at a time into a queue
        Queue<Character> q = new LinkedList<>();
        for (int i = 0; i < originalSequence.length(); i++) {
            // adding each char to the queue
            q.add(originalSequence.charAt(i));
        }

        // iterate thru the q and remove one char at a time
        // add it to our stack
        Stack<Character> s = new Stack<>();
        while (!q.isEmpty()) {
            Character c = q.remove();
            s.push(c);
        }

        // pop each char off of the stack
        // make the 'complement' swap
        // i.e. A -> T, T -> A, G -> C, C -> G and build up a file string
        StringBuilder sb = new StringBuilder();
        while (!s.isEmpty()) {
            Character c = s.pop();

            // change the char into its complement
            switch (c) {
                case 'a':
                case 'A':
                    sb.append('T');
                    break;
                case 't':
                case 'T':
                    sb.append('A');
                    break;
                case 'g':
                case 'G':
                    sb.append('C');
                    break;
                case 'c':
                case 'C':
                    sb.append('G');
                    break;
            }
        }

        return sb.toString();
    }
}

//> Task :compileJava UP-TO-DATE
//> Task :processResources
//> Task :classes
//> Task :compileTestJava UP-TO-DATE
//> Task :processTestResources UP-TO-DATE
//> Task :testClasses UP-TO-DATE
//> Task :test
//PlainSequencesTest > testGetReverseComplement PASSED
//BUILD SUCCESSFUL in 312ms
//5 actionable tasks: 2 executed, 3 up-to-date
//Consider enabling configuration cache to speed up this build: https://docs.gradle.org/9.6.0/userguide/configuration_cache_enabling.html
//7:21:06 PM: Execution finished ':test --tests "edu.hanalways.cs143.Sequences.PlainSequencesTest.testGetReverseComplement"'.
