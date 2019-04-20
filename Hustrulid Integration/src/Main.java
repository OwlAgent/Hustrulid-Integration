// Stefan Anders Hustrulid

/*
 * Integration project: A compilation of logic/number games. Currently contains a functional version
 * of Minesweeper, and Sudoku is in progress
 */

/*
 * operator precedence: closer to top is higher precedence expr++ expr-- ++expr --expr +expr -expr ~
 * ! * / % + - << >> >>> < > <= >= instanceof == != & ^ | && || ? : = += -= *= /= %= &= ^= |= <<=
 * >>= >>>=
 */

import java.util.Scanner;

/**
 * The driver code that chooses what program to run.
 * 
 * @author Stefan
 */
public class Main {

  static Scanner scanner = new Scanner(System.in);
  static Minesweeper minesweeper;
  static Sudoku sudoku;
  static Psi psi;

  // this is a header
  // public is an access modifier
  // void is the return type, meaning this method doesn't return anything
  // method names should be in lowerCamelCase and be named with a verb

  /**
   * Accepts user inputs to determine which program. Ends when user is Finished
   * 
   * @param args Required for main method.
   */
  public static void main(String[] args) {
    minesweeper = new Minesweeper();
    sudoku = new Sudoku();
    psi = new Psi();

    String yesFinished = "Finished";
    boolean finished = false;
    while (finished == false) {
      System.out.println("Hello Player!" + "\nPlease enter one of the following:"
          + "\n\tMinesweeper " + "\n\tSudoku" + "\n\tPSI" + "\n\tFinished");
      System.out.println();
      String game = scanner.next();
      switch (game) {
        case "Minesweeper":
          minesweeper.playMinesweeper();
          break;
        case "Sudoku":
          sudoku.playSudoku();
          break;
        case "Finished":
          break;
        case "PSI":
          psi.runPsi();
          break;
        default:
          break;
      }
      // .equals() compares if the 2 objects are identical
      if (game.compareTo(yesFinished) == 0) {
        break; // if a break is hit in a loop it will end the loop
      }
    }
  }
}


