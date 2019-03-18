// Stefan Anders Hustrulid

/*
 * Integration project: A compilation of logic/number games. currently contains a complete version 
 * of basic Minesweeper, and Sudoku is in progress
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

/*
 * byte: The byte data type is an 8-bit signed two's complement integer. It has 
 * a minimum value of -128 and a maximum value of 127 (inclusive). The byte data
 * type can be useful for saving memory in large arrays, where the memory 
 * savings actually matters. They can also be used in place of int where their 
 * limits help to clarify your code; the fact that a variable's range is 
 * limited can serve as a form of documentation.
 * 
 * short: The short data type is a 16-bit signed two's complement integer. It 
 * has a minimum value of -32,768 and a maximum value of 32,767 (inclusive). As 
 * with byte, the same guidelines apply: you can use a short to save memory in 
 * large arrays, in situations where the memory savings actually matters.
 * 
 * int: By default, the int data type is a 32-bit signed two's complement 
 * integer, which has a minimum value of -231 and a maximum value of 231-1. In 
 * Java SE 8 and later, you can use the int data type to represent an unsigned 
 * 32-bit integer, which has a minimum value of 0 and a maximum value of 232-1. 
 * Use the Integer class to use int data type as an unsigned integer. Static 
 * methods like compareUnsigned, divideUnsigned etc have been added to the 
 * Integer class to support the arithmetic operations for unsigned integers.
 * 
 * long: The long data type is a 64-bit two's complement integer. The signed 
 * long has a minimum value of -263 and a maximum value of 263-1. In Java SE 8 
 * and later, you can use the long data type to represent an unsigned 64-bit 
 * long, which has a minimum value of 0 and a maximum value of 264-1. Use this 
 * data type when you need a range of values wider than those provided by int. 
 * The Long class also contains methods like compareUnsigned, divideUnsigned 
 * etc to support arithmetic operations for unsigned long.
 * 
 * float: The float data type is a single-precision 32-bit IEEE 754 floating 
 * point. Its range of values is beyond the scope of this discussion, but is 
 * specified in the Floating-Point Types, Formats, and Values section of the 
 * Java Language Specification. As with the recommendations for byte and short, 
 * use a float (instead of double) if you need to save memory in large arrays 
 * of floating point numbers. This data type should never be used for precise 
 * values, such as currency. For that, you will need to use the 
 * java.math.BigDecimal class instead. Numbers and Strings covers BigDecimal 
 * and other useful classes provided by the Java platform.
 * 
 * double: The double data type is a double-precision 64-bit IEEE 754 floating 
 * point. Its range of values is beyond the scope of this discussion, but is 
 * specified in the Floating-Point Types, Formats, and Values section of the 
 * Java Language Specification. For decimal values, this data type is generally 
 * the default choice. As mentioned above, this data type should never be used 
 * for precise values, such as currency.
 * 
 * boolean: The boolean data type has only two possible values: true and false. 
 * Use this data type for simple flags that track true/false conditions. 
 * This data type represents one bit of information, but its "size" isn't 
 * something that's precisely defined.
 * 
 * char: The char data type is a single 16-bit Unicode character. It has a 
 * minimum value of '\u0000' (or 0) and a maximum value of '\uffff' (or 65,535 
 * inclusive).
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
    
    String yesFinished = "yes";
    boolean finished = false;
    while(finished == false) {
      System.out.println("Do you wish to play Minesweeper or Sudoku? Or are you finished? (yes/no)"
          );
      System.out.println("\n\nThe SudokuBoardV2 class will most likely produce a completely solved "
          + "Sudoku board, however it does not always succeed, \nI have tried to get it to try again"
          + "until it produces a complete and correct board but I am starting to confuse myself. \n"
          + "do you have any suggestions?");
      String game = scanner.next();
      switch(game) {
        case "Minesweeper":
          minesweeper.playMinesweeper();
          break;
        case "Sudoku":
          sudoku.playSudoku();
          break;
        case "yes":
          break;
        case "no":
          break;
      }
      // == checks to see if 2 objects are the same objects and occupy the same space in memory... 
      //.equals() compares if the 2 objects are identical
      if(game.compareTo(yesFinished) == 0) {
        break; // if a break is hit in a loop it will end the loop 
      }
      else {
        continue; // if a continue is hit in a loop it will skip to the next iteration
      }
    }
  }
}






