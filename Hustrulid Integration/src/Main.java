/*
 * Integration project I am not sure yet but I think I might go for a program similar to Minesweeper
 * If I can learn how to get buttons in a grid shape to display, I just need to program the logic in
 * as Minesweeper is a game based on numerical logic
 */

import java.security.Permission;
import java.util.Scanner;

// Stefan Anders Hustrulid
public class Main {

  public static void main(String[] args) {
    // TODO Auto-generated method stub

    final String greeting = "Hello welcome to my integration project!"; // greeting does not change
    System.out.println(greeting);

    /*
     * byte: The byte data type is an 8-bit signed two's complement integer. It has a minimum value
     * of -128 and a maximum value of 127 (inclusive). The byte data type can be useful for saving
     * memory in large arrays, where the memory savings actually matters. They can also be used in
     * place of int where their limits help to clarify your code; the fact that a variable's range
     * is limited can serve as a form of documentation.
     * 
     * short: The short data type is a 16-bit signed two's complement integer. It has a minimum
     * value of -32,768 and a maximum value of 32,767 (inclusive). As with byte, the same guidelines
     * apply: you can use a short to save memory in large arrays, in situations where the memory
     * savings actually matters.
     * 
     * int: By default, the int data type is a 32-bit signed two's complement integer, which has a
     * minimum value of -231 and a maximum value of 231-1. In Java SE 8 and later, you can use the
     * int data type to represent an unsigned 32-bit integer, which has a minimum value of 0 and a
     * maximum value of 232-1. Use the Integer class to use int data type as an unsigned integer.
     * Static methods like compareUnsigned, divideUnsigned etc have been added to the Integer class
     * to support the arithmetic operations for unsigned integers.
     * 
     * long: The long data type is a 64-bit two's complement integer. The signed long has a minimum
     * value of -263 and a maximum value of 263-1. In Java SE 8 and later, you can use the long data
     * type to represent an unsigned 64-bit long, which has a minimum value of 0 and a maximum value
     * of 264-1. Use this data type when you need a range of values wider than those provided by
     * int. The Long class also contains methods like compareUnsigned, divideUnsigned etc to support
     * arithmetic operations for unsigned long.
     * 
     * float: The float data type is a single-precision 32-bit IEEE 754 floating point. Its range of
     * values is beyond the scope of this discussion, but is specified in the Floating-Point Types,
     * Formats, and Values section of the Java Language Specification. As with the recommendations
     * for byte and short, use a float (instead of double) if you need to save memory in large
     * arrays of floating point numbers. This data type should never be used for precise values,
     * such as currency. For that, you will need to use the java.math.BigDecimal class instead.
     * Numbers and Strings covers BigDecimal and other useful classes provided by the Java platform.
     * 
     * double: The double data type is a double-precision 64-bit IEEE 754 floating point. Its range
     * of values is beyond the scope of this discussion, but is specified in the Floating-Point
     * Types, Formats, and Values section of the Java Language Specification. For decimal values,
     * this data type is generally the default choice. As mentioned above, this data type should
     * never be used for precise values, such as currency.
     * 
     * boolean: The boolean data type has only two possible values: true and false. Use this data
     * type for simple flags that track true/false conditions. This data type represents one bit of
     * information, but its "size" isn't something that's precisely defined.
     * 
     * char: The char data type is a single 16-bit Unicode character. It has a minimum value of
     * '\u0000' (or 0) and a maximum value of '\uffff' (or 65,535 inclusive).
     */

    boolean isAMine = true;
    double probabilityOfMines = 0.2;


    // variables are locations in memory where data is stored
    // scope is whether the variables are limited within the method or within the class

    String columnLengthRequest = "Please enter the number of columns";
    String rowLengthRequest = "Please enter the number of rows";
    Scanner dimensions = new Scanner(System.in);
    System.out.println(columnLengthRequest);
    int numberOfColumns = dimensions.nextInt();
    System.out.println(rowLengthRequest);
    int numberOfRows = dimensions.nextInt();
    dimensions.close();
    
    //everything 

    final double SALES_TAX_RATE = 0.065;
    // SALES_TAX_RATE = 0.075;

    int int1 = 2;
    double double1 = 2.0;
    String string1 = "2";


    // Declare and initialize second integer, double, and String variables.
    int int2 = 2;
    double double2 = 2.0;
    String string2 = "2";
    // Print the sum of both integer variables on a new line.
    System.out.println("The result of + with ints is " + (int1 + int2));
    // Print the sum of the double variables on a new line.
    System.out.println("The result of + with doubles is " + (double1 + double2));
    // Concatenate and print the String variables on a new line.
    System.out.println("The result of + with Strings is " + string1 + string2);

    Scanner scan = new Scanner(System.in);
    int i = scan.nextInt();
    double d = scan.nextDouble();
    scan.nextLine(); // when going from numbers to strings
    String s = scan.nextLine();

    // Write your code here.

    System.out.println("String: " + s);
    System.out.println("Double: " + d);
    System.out.println("Int: " + i);



    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();

    for (int ii = 0; ii < t; ii++) {

      try {
        long x = sc.nextLong();
        System.out.println(x + " can be fitted in:");
        if (x >= -128 && x <= 127)
          System.out.println("* byte");
        if (x >= -32768 && x <= 32767)
          System.out.println("* short");
        if (x >= -Math.pow(2, 31) && x <= (Math.pow(2, 31) - 1))
          System.out.println("* int");
        if (x >= -Math.pow(2, 63) && x <= (Math.pow(2, 63) - 1))
          System.out.println("* long");
        // Complete the code
      } catch (Exception e) {
        System.out.println(sc.next() + " can't be fitted anywhere.");
      }

    }
  }

}

/*
 * logic ideas: generate minefield as 25x20 with 99 mines generated randomly using locations of
 * mines program determines the number assigned to adjacent squares all squares will be displayed as
 * HIDDEN (blank squares) until left clicked on. then they will display their number as an
 * unrevealed square If a 0 square button is left clicked the surrounding will also be revealed up
 * to the inclusive bordering >0 squares If an unrevealed square is right clicked it will display F
 * until right clicked again (It cannont be left clicked until unflagged) If left and right clicking
 * buttons is not possible then perhaps drop down menus game ends when all numbered squares are
 * revealed and all mines are flagged If the classic game does not incorperate all of the lessons
 * then I can always add my own twists and quirks to make sure it does. The way I am thinking of
 * setting this up is not based on graphics, only text and buttons placed in a matrix
 */


public class Solution {
  public static void main(String[] args) {

    DoNotTerminate.forbidExit();

    try {
      Scanner in = new Scanner(System.in);
      int n = in.nextInt();
      in.close();
      // String s=???; Complete this line below

      if (n == Integer.parseInt(s)) {
        System.out.println("Good job");
      } else {
        System.out.println("Wrong answer.");
      }
    } catch (DoNotTerminate.ExitTrappedException e) {
      System.out.println("Unsuccessful Termination!!");
    }
  }
}


// The following class will prevent you from terminating the code using exit(0)!
class DoNotTerminate {

  public static class ExitTrappedException extends SecurityException {

    private static final long serialVersionUID = 1;
  }

  public static void forbidExit() {
    final SecurityManager securityManager = new SecurityManager() {
      @Override
      public void checkPermission(Permission permission) {
        if (permission.getName().contains("exitVM")) {
          throw new ExitTrappedException();
        }
      }
    };
    System.setSecurityManager(securityManager);
  }
}

