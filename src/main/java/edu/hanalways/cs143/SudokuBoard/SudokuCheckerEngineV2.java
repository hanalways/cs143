// Hana Clements
// CS143
// This program runs a bunch of asserts to ensure that the SudokuBoard is implemented correctly so far

package edu.hanalways.cs143.SudokuBoard;

import edu.hanalways.cs143.SudokuBoard.SudokuBoard;

import java.io.FileNotFoundException;

public class SudokuCheckerEngineV2 {

   public static void main(String[] args) throws FileNotFoundException {
      // Note that here I am calling the board object MySudokuBoard
      // if you named your class something different, you should
      // find and replace all `MySudokuBoard` with your class name
      boolean allTests = true;
      
      // an empty board is valid, but not solved
      System.out.print("Checking empty board...");
      SudokuBoard board1 = new SudokuBoard("sudoku/boards/empty.sdk");
      assert board1.isValid() : "isValid: should be true";
      assert !board1.isSolved() : "isSolved: should be false";
      if(board1.isValid() && !board1.isSolved()) 
         System.out.println("passed.");
      else {
         System.out.println("failed.");
         allTests = false;
      }
   
      // an incomplete, valid board is valid, but not solved
      System.out.print("Checking incomplete, valid board...");
      SudokuBoard board2 = new SudokuBoard("sudoku/boards/valid-incomplete.sdk");
      assert board2.isValid() : "isValid: should be true";
      assert !board2.isSolved() : "isSolved: should be false";
      if(board2.isValid() && !board2.isSolved()) 
         System.out.println("passed.");
      else {
         System.out.println("failed.");
         allTests = false;
      }
      
      // a complete, valid board is valid and solved
      System.out.print("Checking complete, valid board...");
      SudokuBoard board3 = new SudokuBoard("sudoku/boards/valid-complete.sdk");
      assert board3.isValid() : "isValid: should be true";
      assert board3.isSolved() : "isSolved: should be true";
      if(board3.isValid() && board3.isSolved()) 
         System.out.println("passed.");
      else {
         System.out.println("failed.");
         allTests = false;
      }
      
      // a board with dirty data is not valid and not solved
      System.out.print("Checking dirty data board...");
      SudokuBoard board4 = new SudokuBoard("sudoku/boards/dirty-data.sdk");
      assert !board4.isValid() : "isValid: should be false";
      assert !board4.isSolved() : "isSolved: should be false";
      if(!board4.isValid() && !board4.isSolved()) 
         System.out.println("passed.");
      else {
         System.out.println("failed.");
         allTests = false;
      }
      
      // a board with a row violation is not valid and not solved
      System.out.print("Checking row violating board...");
      SudokuBoard board5 = new SudokuBoard("sudoku/boards/row-violation.sdk");
      assert !board5.isValid() : "isValid: should be false";
      assert !board5.isSolved() : "isSolved: should be false";
      if(!board5.isValid() && !board5.isSolved()) 
         System.out.println("passed.");
      else {
         System.out.println("failed.");
         allTests = false;
      }
      
      // a board with a column violation is not valid and not solved
      System.out.print("Checking col violating board...");
      SudokuBoard board6 = new SudokuBoard("sudoku/boards/col-violation.sdk");
      assert !board6.isValid() : "isValid: should be false";
      assert !board6.isSolved() : "isSolved: should be false";
      if(!board6.isValid() && !board6.isSolved()) 
         System.out.println("passed.");
      else {
         System.out.println("failed.");
         allTests = false;
      }
      
      // a board with both a row and a column violation is not valid and not solved
      System.out.print("Checking row&col violating board...");
      SudokuBoard board7 = new SudokuBoard("sudoku/boards/row-and-col-violation.sdk");
      assert !board7.isValid() : "isValid: should be false";
      assert !board7.isSolved() : "isSolved: should be false";
      if(!board7.isValid() && !board7.isSolved()) 
         System.out.println("passed.");
      else {
         System.out.println("failed.");
         allTests = false;
      }
      
      // a board with a mini-square violation is not valid and not solved
      System.out.print("Checking mini-square violating board...");
      SudokuBoard board8 = new SudokuBoard("sudoku/boards/grid-violation.sdk");
      assert !board8.isValid() : "isValid: should be false";
      assert !board8.isSolved() : "isSolved: should be false";
      if(!board8.isValid() && !board8.isSolved()) 
         System.out.println("passed.");
      else {
         System.out.println("failed.");
         allTests = false;
      }       
      
      if(allTests)
         System.out.println("**** HORRAY: ALL TESTS PASSED ****");
   }
}

// 5:40:48 PM: Executing ':edu.hanalways.cs143.SudokuBoard.SudokuCheckerEngineV2.main()'…
//
//> Task :compileJava UP-TO-DATE
//> Task :processResources UP-TO-DATE
//> Task :classes UP-TO-DATE
//
//> Task :edu.hanalways.cs143.SudokuBoard.SudokuCheckerEngineV2.main()
//Checking empty board...passed.
//Checking incomplete, valid board...passed.
//Checking complete, valid board...passed.
//Checking dirty data board...failed.
//Checking row violating board...passed.
//Checking col violating board...passed.
//Checking row&col violating board...passed.
//Checking mini-square violating board...passed.
//
//BUILD SUCCESSFUL in 75ms
//3 actionable tasks: 1 executed, 2 up-to-date
//Consider enabling configuration cache to speed up this build: https://docs.gradle.org/9.6.0/userguide/configuration_cache_enabling.html
//5:40:49 PM: Execution finished ':edu.hanalways.cs143.SudokuBoard.SudokuCheckerEngineV2.main()'.
