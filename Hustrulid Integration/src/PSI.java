//Stefan Anders Hustrulid
public class PSI {

 public PSI() {
   System.out.println("There are comments and code in here as well\n");
   System.out.println("byte: \n"
       + "The byte data type is an 8-bit signed two's complement integer. It has \n" + 
       "a minimum value of -128 and a maximum value of 127 (inclusive). The byte data\n" + 
       "type can be useful for saving memory in large arrays, where the memory \n" + 
       "savings actually matters. They can also be used in place of int where their \n" + 
       "limits help to clarify your code; the fact that a variable's range is \n" + 
       "limited can serve as a form of documentation.\n\n"
       + "short: \n"
       + "The short data type is a 16-bit signed two's complement integer. It \n" + 
       "has a minimum value of -32,768 and a maximum value of 32,767 (inclusive). As \n" + 
       "with byte, the same guidelines apply: you can use a short to save memory in \n" + 
       "large arrays, in situations where the memory savings actually matters.\n" + 
       "\n" + 
       "int: \n"
       + "By default, the int data type is a 32-bit signed two's complement \n" + 
       "integer, which has a minimum value of -231 and a maximum value of 231-1. In \n" + 
       "Java SE 8 and later, you can use the int data type to represent an unsigned \n" + 
       "32-bit integer, which has a minimum value of 0 and a maximum value of 232-1. \n" + 
       "Use the Integer class to use int data type as an unsigned integer. Static \n" + 
       "methods like compareUnsigned, divideUnsigned etc have been added to the \n" + 
       "Integer class to support the arithmetic operations for unsigned integers.\n" + 
       "\n" + 
       "long: \n"
       + "The long data type is a 64-bit two's complement integer. The signed \n" + 
       "long has a minimum value of -263 and a maximum value of 263-1. In Java SE 8 \n" + 
       "and later, you can use the long data type to represent an unsigned 64-bit \n" + 
       "long, which has a minimum value of 0 and a maximum value of 264-1. Use this \n" + 
       "data type when you need a range of values wider than those provided by int. \n" + 
       "The Long class also contains methods like compareUnsigned, divideUnsigned \n" + 
       "etc to support arithmetic operations for unsigned long.\n" + 
       "\n" + 
       "float: \n"
       + "The float data type is a single-precision 32-bit IEEE 754 floating \n" + 
       "point. Its range of values is beyond the scope of this discussion, but is \n" + 
       "specified in the Floating-Point Types, Formats, and Values section of the \n" + 
       "Java Language Specification. As with the recommendations for byte and short, \n" + 
       "use a float (instead of double) if you need to save memory in large arrays \n" + 
       "of floating point numbers. This data type should never be used for precise \n" + 
       "values, such as currency. For that, you will need to use the \n" + 
       "java.math.BigDecimal class instead. Numbers and Strings covers BigDecimal \n" + 
       "and other useful classes provided by the Java platform.\n" + 
       "\n" + 
       "double: \n"
       + "The double data type is a double-precision 64-bit IEEE 754 floating \n" + 
       "point. Its range of values is beyond the scope of this discussion, but is \n" + 
       "specified in the Floating-Point Types, Formats, and Values section of the \n" + 
       "Java Language Specification. For decimal values, this data type is generally \n" + 
       "the default choice. As mentioned above, this data type should never be used \n" + 
       "for precise values, such as currency.\n" + 
       " * \n" + 
       "boolean: \n"
       + "The boolean data type has only two possible values: true and false. \n" + 
       "Use this data type for simple flags that track true/false conditions. \n" + 
       "This data type represents one bit of information, but its \"size\" isn't \n" + 
       "something that's precisely defined.\n" + 
       "\n" + 
       "char: \n"
       + "The char data type is a single 16-bit Unicode character. It has a \n" + 
       "minimum value of '\\u0000' (or 0) and a maximum value of '\\uffff' (or 65,535 \n" + 
       "inclusive).\n");
   
   System.out.println("PSI 1: Use a boolean variable with an appropriate name  (singular noun, "
       + "lowerCamelCase)"
       + "\n\tlocation: Main.main()\n");
   
   System.out.println("PSI 1: Use a int variable with an appropriate name  (singular noun, "
       + "lowerCamelCase)"
       + "\n\tlocation: Minesweeper.playMinesweeper()\n");
   
   System.out.println("PSI 1: Use a int variable with an appropriate name  (singular noun, "
       + "lowerCamelCase)"
       + "\n\tlocation: Setup.setDifficultyMode()\n");
   
   System.out.println("PSI 1: Use a String object with an appropriate name  (singular noun, "
       + "lowerCamelCase)"
       + "\n\tlocation: Setup.setDifficultyMode()\n");
   
   System.out.println("A variable refers to a specific location in memory where information is "
       + "stored");
   
   System.out.println("Scope is the area in code that a variable can be immediately accessed:\n"
       + "\twithin a loop\n\t"
       + "within a method\n\t"
       + "within a class\n");
   
   System.out.println("PSI 1: Use final with a variable and describe what it means in a comment"
       + "\n\tlocation: Setup.setDifficultyMode()\n");
   
   System.out.println("PSI 1: Use casting and describe what it means in a comment"
       + "\n\tlocation: Setup.setDifficultyMode()\n");
   
   System.out.println("PSI 2: Create a method with arguments and return values"
       + "\n\tlocation: MineField.generateRandomCoordinate(int, int, int)\n");
   
   System.out.println("PSI 2: Identify a method call and argument in comments"
       + "\n\tlocation: Minesweeper.playMinesweeper()\n");
   
   System.out.println("PSI 2: Identify a header and parameter in comments"
       + "\n\tlocation: Setup.setNumberOfMines(boolean)\n");
   
   System.out.println("PSI 2: Use the Random class"
       + "\n\tlocation: SudokuBoardV3.createSudokuBoard(int, int[])\n");
   
   System.out.println("PSI 2: Use the Math class"
       + "\n\tlocation: SudokuBoardV3.checkGroup(int, int, int, int[][])\n");
   
   System.out.println("PSI 2: Create if/else constructs"
       + "\n\tlocation: MineField.setMinePositions(int, int, int)\n");
   
   System.out.println("PSI 2: Create ternary constructs"
       + "\n\tlocation: MineField.testGrid(int[][], int, int)\n");
   
   System.out.println("PSI 2: Use a switch statement"
       + "\n\tlocation: Main.main()\n");
   
   System.out.println("PSI 2: Compare two String objects by using the compareTo and equals methods "
       + "and make a comment describing how == works with objects"
       + "\n\tlocation: Main.main()"
       + "\n\tlocation: Setup.setDifficultyMode()\n");
   // == checks to see if 2 objects are the same objects and occupy the same space in memory... 
   
   int i = 0;
   i += 3;
   i--;
   System.out.println("PSI 2: Use +, -, *, /, %, ++, --, += "
       + "\n\tlocation: SudokuBoardV3.createSudokuBoard(int, int[])"
       + "\n\tlocation: SudokuBoardV3.checkGroup(int, int, int, int[][]\n\t" + i + "\n");
   
   System.out.println("PSI 2: Use relational operators "
       + "\n\tlocation: Found in almost every class\n");
   
   System.out.println("PSI 2: Use relational operators "
       + "\n\tlocation: SudokuBoardV3\n");
   
   System.out.println("PSI 2: Use conditional operators "
       + "\n\tlocation: SudokuBoardV3\n");
   
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
   
   System.out.println("PSI 2: Use while loops "
       + "\n\tlocation: Main.main()\n");
   
   System.out.println("PSI 2: Use for loops "
       + "\n\tlocation: SudokuBoardV3.printSolution(int[][])\n");
   
   System.out.println("PSI 2: Use do/while loops "
       + "\n\tlocation: Minesweeper.playMinesweeper()\n");
   
   System.out.println("PSI 2: Use break in a loop with a comment describing what it does "
       + "\n\tlocation: SudokuProblemMaker.test2()\n");
   
   System.out.println("PSI 2: Use continue in a loop with a comment describing what it does "
       + "\n\tlocation: SudokuProblemMaker.setProblemBoard()\n");
   
   System.out.println("PSI 3: Make a class in a separate file with private fields, public getters "
       + "and setters, a constructor with and without parameters "
       + "\n\tlocation: ChangeableSpot()"
       + "\n\tHas 2 different constructors both with parameters\n");
   
   System.out.println("PSI 3: Overload a method "
       + "\n\tlocation: PointRemover.setChangeableSpotsBoard()"
       + "\n\tlocation: PointRemover.setChangeableSoptsBoard(int, int, boolean)\n");
   
   System.out.println("PSI 3: Describe inheritance and its benefits "
       + "\n\tInheritance allows a subclass access to the fields and methods of its superclass."
       + "\n\tCreation of a subclass object will also run the constructor of its superclass."
       + "\n\tSubclasses have an is-a relation with its superclass.\n");
   
   System.out.println("PSI 3: Develop code that makes use of polymorphism "
       + "\n\tlocation: SudokuSolver"
       + "\n\tExtends SudokuProblemMaker but also has its own fields and methods\n");
   
   SudokuSolver ss = new SudokuSolver();
   
   System.out.println("PSI 3: Declare, instantiate, initialize and use a one-dimensional array "
       + "\n\tlocation: SudokuSolver.changeableSpotsList\n");
   
   int[] psiRequirement = {7, 8, 5, 8, 2, 9};
   int small = psiRequirement[1];
   for(int num: psiRequirement) {
     if(num <= small) {
       small = num;
     }
   }
   System.out.println(small + "\n");
   int sum = 0;
   for(int num: psiRequirement) {
     sum+=num;
   }
   System.out.println(sum + "\n");
   int val = 8;
   System.out.println("index");
   for(int index = 0; index < psiRequirement.length; index++) {
     if(psiRequirement[index] == val) {
       System.out.println(index);
     }
   }
   System.out.print("\n");
   
   System.out.println("PSI 3: Declare, instantiate, initialize and use multi-dimensional arrays "
       + "\n\tlocation: SudokuProblemMaker\n");
   
   System.out.println("PSI 3: Declare and use an ArrayList of a given type "
       + "\n\tlocation: SudokuProblemMaker.setRemovablePoints()\n");
   
   int[][] psiRequirement2 = {{7, 8, 5, 8, 2, 9}, {4, 6, 8, 2, 8, 6}};
   System.out.println("row\tcolumn");
   for(int row = 0; row < 2; row++) {
     for(int column = 0; column < 6; column++) {
       if(psiRequirement2[row][column] == val) {
         System.out.println(row + "\t" + column);
       }
     }
   }
   System.out.println("\n");
 }
}
