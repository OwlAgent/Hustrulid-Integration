//Stefan Anders Hustrulid
import java.util.Scanner;

public class Setup {
  
  static Scanner scanner = new Scanner(System.in);
  
  static boolean difficultyMode;
  static int numberOfColumns;
  static int numberOfRows;
  static int numberOfMines;
  
  public void setDifficultyMode() {
    System.out.println("Creators Note: Warning the program has a god complex\n Sugestion: odd "
        + "number of characters and no \n If you do not with to see the answer when you play please "
        + "comment out mineField.testGrid(grid, numberOfColumns, numberOfRows); in the Minesweeper "
        + "Class");
    //final means that the object String.greeting does not change
    final String greeting = "Hello welcome to my integration project! \n\nWhat is your name?"; 
    System.out.println(greeting);
    String name = scanner.nextLine();
    int nameLength = name.length();
    if(nameLength % 2 ==0) {
      difficultyMode = true;
    }
    else {
      difficultyMode = false;
    }
    double choice = 0.0;
    if(!difficultyMode) {
      System.out.println("\nPerchance do you wish to increase the difficulty by a "
          + "teensy weensy amount?\nyes / no");
      String requestAnswer = scanner.nextLine();
      requestAnswer.toLowerCase();
      if(requestAnswer.equals("yes")) {
        choice =1.0;
      } else {
        System.out.println("\nnormal mode activated... \n...you got lucky...\n");
      }
    }
    byte castChoice = (byte)choice; //casting changes larger data types into smaller data types
    if(castChoice == 1) {
      difficultyMode = true;
    }
    
    if(difficultyMode == true) {
      System.out.println("\nYes!! Impossibly difficult omnipotent being mode activated!\n\n"
          + "I pity you so I will give you some advice:\n"
          + "\tYou will need paper\n"
          + "\t Or a very good memory\n"
          + "\tYou can only enter a select a square once unless Flagging it\n"
          + "\tOnly Flags will remain visible\n");
    }
  }
  
  public boolean getDifficultyMode() {
    return difficultyMode;
  }
  
  public void setNumberOfColumns(boolean difficultyMode) {
    String numberOfColumnsRequest = "Please enter the number of columns";
    System.out.println(numberOfColumnsRequest);
    numberOfColumns = scanner.nextInt();
    if(difficultyMode == true) {
      while(numberOfColumns != 109) {
        if(numberOfColumns < 109) {
          System.out.println("Hahaha! you have no free will in this mode \nthe "
              + "number of columns that the omnipotent being chose for you is much larger!");
        }
        if(numberOfColumns > 109) {
          System.out.println("Hahaha! you have no free will in this mode\nFortunately the "
              + "number of columns that the omnipotent being chose for you is much smaller!");
        }
        System.out.println("Now try again and return the number of columns that "
            + "the omnipotent being has chosen for you...");
        numberOfColumns = scanner.nextInt();
      }
        System.out.println("The omnipotent being has chosen for you...\n"
            + "...now procede...");
    }
    if(difficultyMode == false) {
      if(numberOfColumns == 109) {
        System.out.println("Fantastic Choice!!!");
      }
      if(numberOfColumns != 109) {
        System.out.println("Good choice");
      }
    }
  }
  
  public int getNumberOfColumns() {
    return numberOfColumns;
  }
  
  public void setNumberOfRows(boolean difficultyMode) {
    String numberOfRowsRequest = "Please enter the number of rows";
    System.out.println(numberOfRowsRequest);
    numberOfRows = scanner.nextInt();
    if(difficultyMode == true) {
      while(numberOfRows != 109) {
        if(numberOfRows < 109) {
          System.out.println("Hahaha! you have no free will in this mode \nthe "
              + "number of rows that the omnipotent being chose for you is much larger!");
        }
        if(numberOfRows > 109) {
          System.out.println("Hahaha! you have no free will in this mode\nFortunately the "
              + "number of rows that the omnipotent being chose for you is much smaller!");
        }
        System.out.println("Now try again and return the number of rows that "
            + "the omnipotent being has chosen for you...");
        numberOfRows = scanner.nextInt();
      }
        System.out.println("The omnipotent being has chosen for you...\n"
            + "...now procede...");
    }
    if(difficultyMode == false) {
      if(numberOfRows == 109) {
        System.out.println("Fantastic Choice!!!");
      }
      if(numberOfRows != 109) {
        System.out.println("Good choice");
      }
    }
  }
  
  public int getNumberOfRows() {
    return numberOfRows;
  }
  //this is a method header     this is a method parameter
  public void setNumberOfMines(boolean difficultyMode) {
    String numberOfMinesRequest = "Please enter the number of mines";
    System.out.println(numberOfMinesRequest);
    numberOfMines = scanner.nextInt();
    if(difficultyMode == true) {
      while(numberOfMines != 3000) {
        if(numberOfMines < 3000) {
          System.out.println("Hahaha! you have no free will in this mode \nthe "
              + "number of mines that the omnipotent being chose for you is much larger!");
        }
        if(numberOfMines > 3000) {
          System.out.println("Hahaha! you have no free will in this mode\nFortunately the "
              + "number of mines that the omnipotent being chose for you is much smaller!");
        }
        System.out.println("Now try again and return the number of mines that "
            + "the omnipotent being has chosen for you...");
        numberOfMines = scanner.nextInt();
      }
        System.out.println("The omnipotent being has chosen for you...\n"
            + "...now procede...");
    }
    if(difficultyMode == false) {
      if(numberOfMines == 3000) {
        System.out.println("Fantastic Choice!!!");
      }
      if(numberOfMines != 3000) {
        System.out.println("Good choice");
      }
    }
  }
  
  public int getNumberOfMines() {
    return numberOfMines;
  }
}
