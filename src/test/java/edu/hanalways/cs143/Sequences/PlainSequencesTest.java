package edu.hanalways.cs143.Sequences;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import java.io.FileNotFoundException;

public class PlainSequencesTest {

    @Test
    public void testReadSequences() {
        Sequences tester = new PlainSequences();
        String fileName = "peptide_mRNA.text";

        try {
            tester.readSequences(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("e");
        }

        assertEquals("after reading file the number of sequences should be 1", 1, tester.getSequences().size());
    }

    @Test(expected = FileNotFoundException.class)
    public void testFileNotFoundExceptionIsThrown() throws FileNotFoundException {
        Sequences tester = new PlainSequences();
        String fileName = "noFileHere.txt";
        tester.readSequences(fileName);
    }

    @Test
    public void testIsValidSequences() {
        Sequences tester = new PlainSequences();
        String fileName = "peptide_mRNA.text";

        try {
            tester.readSequences(fileName);
        } catch (FileNotFoundException e) {
        }

        assertEquals("the first sequence read should be valid", true, tester.isValidSequence(0));
    }

    @Test
    public void testIsValidSequence_returnsFalse() {
        Sequences tester = new PlainSequences();

        try {
            tester.readSequences("boo");
        } catch (Exception e) {}

        assertFalse("an empty sequence should return false", tester.isValidSequence(0));
    }

    @Test
    public void formatInGroups() {
        Sequences tester = new PlainSequences();
        String fileName = "peptide_mRNA.text";
        String expected =
                        "ACAAGATGCC ATTGTCCCCC GGCCTCCTGC TGCTGCTGCT CTCCGGGGCC ACGGCCACCG \n" +
                        "CTGCCCTGCC CCTGGAGGGT GGCCCCACCG GCCGAGACAG CGAGCATATG CAGGAAGCGG \n" +
                        "CAGGAATAAG GAAAAGCAGC CTCCTGACTT TCCTCGCTTG GTGGTTTGAG TGGACCTCCC \n" +
                        "AGGCCAGTGC CGGGCCCCTC ATAGGAGAGG AAGCTCGGGA GGTGGCCAGG CGGCAGGAAG \n" +
                        "GCGCACCCCC CCAGCAATCC GCGCGCCGGG ACAGAATGCC CTGCAGGAAC TTCTTCTGGA \n" +
                        "AGACCTTCTC CTCCTGCAAA TAAAACCTCA CCCATGAATG CTCACGCAAG TTTAATTACA \n" +
                        "GACCTGAA";

        try {
            tester.readSequences(fileName);
        } catch (FileNotFoundException e) {
        }

        String actual = tester.formatInGroups(0, 10, 6);
        assertEquals("the string for sequence 0 in goups of 10 bases with 6 groups per line", expected, actual);
    }

}
