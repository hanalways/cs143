package edu.hanalways.cs143.SudokuBoard;

import edu.hanalways.cs143.SudokuBoard.SudokuBoard;

import java.io.FileNotFoundException;

public class SudokuSolverEngineRecursion {

   public static void main(String[] args) throws FileNotFoundException {
      // Here I have called my class `MySudokuBoard` if you named your class
      // differently, modify the line below to use your own class name
      SudokuBoard board = new SudokuBoard("sudoku/boards/very-fast-solve.sdk");
      System.out.println("Initial board");
      System.out.println(board);
      System.out.println();

      System.out.print("Solving board...");
      long start = System.currentTimeMillis();
      board.solve();
      long stop = System.currentTimeMillis();
      System.out.printf("SOLVED in %.3f seconds.\n", ((stop-start)/1000.0));
      System.out.println();
      System.out.println(board);

      SudokuBoard invalidBoard = new SudokuBoard("sudoku/boards/row-violation.sdk");
      System.out.println(invalidBoard.isValid());
   }
}
