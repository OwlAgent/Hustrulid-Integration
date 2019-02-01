// Stefan Anders Hustrulid

/*
 * Integration project I am not sure yet but I think I might go for a program 
 * similar to Minesweeper
 * If I can learn how to get buttons in a grid shape to display, I just need to 
 * program the logic in
 * as Minesweeper is a game based on numerical logic
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

import java.security.Permission;
import java.util.Scanner;


public class Main {

  public static void main(String[] args) {

    //final means that the object String.greeting does not change
    final String greeting = "Hello welcome to my integration project! \nWhat is your name?"; 
    System.out.println(greeting);
    Scanner nameInput = new Scanner(System.in);
    String name = nameInput.nextLine();
    nameInput.close();
    
    int nameLength = name.length();
    boolean difficultyMode;
    if(nameLength % 2 ==0) {
      difficultyMode = true;
    }
    else {
      difficultyMode = false;
    }
    if(difficultyMode == true) {
      System.out.println("Impossibly difficult omnipotent being mode activated \n"
          + "I pity you so I will give you some advice:\n"
          + "\tYou will need paper\n"
          + "\t\tOr a very good memory\n"
          + "\t you can only enter a select a square once unless Flagging it\n"
          + "\tOnly Flags will remain visible\n");
    }
    else {
      System.out.println("normal mode activated... \n...you got lucky...");
    }

    double probabilityOfMines = 0.2;

    // variables are locations in memory where data is stored
    // scope is whether the variables are limited within the method or within the class
    int numberOfColumns;
    String columnLengthRequest = "Please enter the number of columns";
    String rowLengthRequest = "Please enter the number of rows";
    Scanner dimensions = new Scanner(System.in);
    System.out.println(columnLengthRequest);
    numberOfColumns = dimensions.nextInt();
    System.out.println(rowLengthRequest);
    int numberOfRows = dimensions.nextInt();
    dimensions.close();
        
    }
  }

/*
 * logic ideas: generate minefield as 25x20 with 99 mines generated randomly 
 * 
 * using locations of mines program determines the number assigned to adjacent 
 * squares 
 * 
 * all squares will be displayed as HIDDEN (blank squares) until left clicked 
 * on. then they will display their number as an unrevealed square 
 * 
 * If a 0 square button is left clicked the surrounding will also be revealed, 
 * up to the inclusive bordering >0 squares 
 * 
 * If an unrevealed square is right clicked it will display F until right 
 * clicked again (It cannont be left clicked until unflagged) 
 * 
 * If left and right clicking buttons is not possible then perhaps drop down 
 * menus 
 * 
 * game ends when all numbered squares are revealed and all mines are flagged 
 * 
 * If the classic game does not incorperate all of the lessons then I can add 
 * my own twists and quirks to make sure it does. The way I am thinking of
 * setting this up is not based on graphics, only text and buttons placed in a 
 * matrix
 */






