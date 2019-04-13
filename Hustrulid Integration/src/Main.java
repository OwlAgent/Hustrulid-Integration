// Stefan Anders Hustrulid

/*
 * Integration project: A compilation of logic/number games. Currently contains a functional 
 * version of Minesweeper, and Sudoku is in progress
 */

/*
 * operator precedence: closer to top is higher precedence
 * expr++ expr--
 * ++expr --expr +expr -expr ~ !
 * * / %
 * + -
 * << >> >>>
 * < > <= >= instanceof
 * == !=
 * &
 * ^
 * |
 * &&
 * ||
 * ? :
 * = += -= *= /= %= &= ^= |= <<= >>= >>>=
 */

import java.util.Scanner;

public class Main {
  
  static Scanner scanner = new Scanner(System.in);

  //this is a header
  //public is an access modifier
  //void is the return type, meaning this method doesn't return anything
  //method names should be in lowerCamelCase and be named with a verb
  
  public static void main(String[] args) { 
    Minesweeper minesweeper = new Minesweeper();
    Sudoku sudoku = new Sudoku();
    
    String yesFinished = "Finished";
    boolean finished = false;
    while(finished == false) {
      System.out.println("Hello Player!"
          + "\nPlease enter one of the following:"
          + "\n\tMinesweeper "
          + "\n\tSudoku"
          + "\n\tPSI"
          + "\n\tFinished");
      System.out.println();
      String game = scanner.next();
      switch(game) {
        case "Minesweeper":
          minesweeper.playMinesweeper();
          break;
        case "Sudoku":
          sudoku.playSudoku();
          break;
        case "Finished":
          break;
        case "PSI":
          PSI psi = new PSI();
          break;
      }
      //.equals() compares if the 2 objects are identical
      if(game.compareTo(yesFinished) == 0) {
        break; // if a break is hit in a loop it will end the loop 
      }
    }
  }
}






