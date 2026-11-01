package edu.hanalways.cs143.SudokuBoard;

import edu.hanalways.cs143.SudokuBoard.SudokuBoard;

import java.io.FileNotFoundException;

public class SudokuSolverEngineRecursion {

   public static void main(String[] args) throws FileNotFoundException {
      // Before trying to solve the board, check if the board is in an invalid state.
      // If it is, print a message to the screen that says that the board cannot be solved.
      SudokuBoard invalidBoard = new SudokuBoard("sudoku/boards/row-violation.sdk");
      System.out.println("Verifying behavior of an invalid board");
      System.out.println("Can this board be solved?: " + invalidBoard.isValid());

      // Before trying to solve the board, check if the board is already solved.
      // If it is, print a message to the screen that says that the board is already solved.
      SudokuBoard solvedBoard = new SudokuBoard("sudoku/boards/valid-complete.sdk");
      System.out.println("Verifying behavior of a pre-solved board");
      System.out.println("Is this board already solved?: " + solvedBoard.isSolved());

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
   }
}
