// Stefan Anders Hustrulid
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Establishes the dimensions of the minesweeper board and the number of mines.
 * 
 * @author Stefan
 */
public class Setup {

  static Scanner scanner = new Scanner(System.in);

  /**
   * Determines if player decides the difficulty of the minesweeper or not.
   */
  static boolean difficultyMode;
  /**
   * How many columns are in the minesweeper.
   */
  static int numberOfColumns;
  /**
   * How many rows are in the minesweeper.
   */
  static int numberOfRows;
  /**
   * How many mines are in the minesweeper.
   */
  static int numberOfMines;
  /**
   * Used to loop try blocks if required.
   */
  static boolean error;

  /**
   * Determines if user chooses dimensions and number of mines or not.
   */
  public static void setDifficultyMode() {
    System.out.println("Creators Note: Warning the Minesweeper program may develope a god complex"
        + " depending on your inputs \nSugestion: odd number of characters and no");

    // final means that the object String.greeting does not change
    final String greeting = "Hello welcome to my integration project! \n\nWhat is your name?";
    System.out.println(greeting);
    String name = scanner.nextLine();
    int nameLength = name.length();
    if (nameLength % 2 == 0) {
      difficultyMode = true;
    } else {
      difficultyMode = false;
    }
    double choice = 0.0;
    if (!difficultyMode) {
      System.out.println("\nPerchance do you wish to increase the difficulty by a "
          + "teensy weensy amount?\nyes / no");
      String requestAnswer = scanner.nextLine();
      String lowRequestAnswer = requestAnswer.toLowerCase();
      if (lowRequestAnswer.equals("no")) {
        System.out.println("\nnormal mode activated... \n...you got lucky...\n");
        choice = 2.0;
      } else if (lowRequestAnswer.equals("yes")) {
        choice = 1.0;
      } else {
        System.out.println("\nDefault answer of \"yes\" inputed");
        choice = 1.0;
      }
    }
    byte castChoice = (byte) choice; // casting changes larger data types into smaller data types
    if (castChoice == 1) {
      difficultyMode = true;
    }

    if (difficultyMode == true) {
      System.out.println("\nYes!! Impossibly difficult Omnipotent Being mode activated!\n");
    }
  }

  public static boolean getDifficultyMode() {
    return difficultyMode;
  }

  /**
   * establishes the number of columns.
   * 
   * @param difficultyMode Whether or not the user chooses the number of columns.
   */
  public static void setNumberOfColumns(boolean difficultyMode) {
    String numberOfColumnsRequest = "Please enter the number of columns";
    System.out.println(numberOfColumnsRequest);
    error = true;
    do {
      try {
        numberOfColumns = scanner.nextInt();
        scanner.nextLine();// fixing
        error = false;
      } catch (InputMismatchException e) {
        System.out.println("Please enter a number.");
        scanner.next();
      }
    } while (error == true);
    if (difficultyMode == true) {
      while (numberOfColumns != 109) {
        if (numberOfColumns < 109) {
          System.out.println("Hahaha! you have no free will in this mode\n\nThe "
              + "number of columns that the Omnipotent Being chose for you is much larger!");
        }
        if (numberOfColumns > 109) {
          System.out.println("\nHahaha! you have no free will in this mode\n\nFortunately the "
              + "number of columns that the Omnipotent Being chose for you is much smaller!");
        }
        System.out.println("\nNow try again and return the number of columns that "
            + "the Omnipotent Being has chosen for you...");
        error = true;
        do {
          try {
            numberOfColumns = scanner.nextInt();
            scanner.nextLine(); // fixing
            error = false;
          } catch (InputMismatchException e) {
            System.out.println("The Omnipotent Being demands that you enter a number.\n");
            scanner.next();
          }
        } while (error == true);
      }
      System.out.println("The Omnipotent Being has chosen for you...\n" + "...now procede...");
    }
    if (difficultyMode == false) {
      if (numberOfColumns == 109) {
        System.out.println("Fantastic Choice!!!");
      }
      if (numberOfColumns != 109) {
        System.out.println("Good choice");
      }
    }
  }

  public static int getNumberOfColumns() {
    return numberOfColumns;
  }

  /**
   * establishes the number of rows.
   * 
   * @param difficultyMode Whether or not the user chooses the number of rows.
   */
  public static void setNumberOfRows(boolean difficultyMode) {
    String numberOfRowsRequest = "Please enter the number of rows";
    System.out.println(numberOfRowsRequest);
    error = true;
    do {
      try {
        numberOfRows = scanner.nextInt();
        scanner.nextLine(); // fixing
        error = false;
      } catch (InputMismatchException e) {
        System.out.println("Please enter a number.");
        scanner.next();
      }
    } while (error == true);
    if (difficultyMode == true) {
      while (numberOfRows != 109) {
        if (numberOfRows < 109) {
          System.out.println("Hahaha! you have no free will in this mode\n\nthe "
              + "number of rows that the Omnipotent Being chose for you is much larger!");
        }
        if (numberOfRows > 109) {
          System.out.println("Hahaha! you have no free will in this mode\n\nFortunately the "
              + "number of rows that the Omnipotent Being chose for you is much smaller!");
        }
        System.out.println("\nNow try again and return the number of rows that "
            + "the Omnipotent Being has chosen for you...");
        error = true;
        do {
          try {
            numberOfRows = scanner.nextInt();
            scanner.nextLine();
            error = false;
          } catch (InputMismatchException e) {
            System.out.println("The Omnipotent Being demands that you enter a number.");
            scanner.next();
          }
        } while (error == true);
      }
      System.out.println("The Omnipotent Being has chosen for you...\n" + "...now procede...");
    }
    if (difficultyMode == false) {
      if (numberOfRows == 109) {
        System.out.println("Fantastic Choice!!!");
      }
      if (numberOfRows != 109) {
        System.out.println("Good choice");
      }
    }
  }

  public static int getNumberOfRows() {
    return numberOfRows;
  }

  // this is a method header this is a method parameter
  /**
   * establishes the number of mines.
   * 
   * @param difficultyMode Whether or not the user chooses the number of mines.
   */
  public static void setNumberOfMines(boolean difficultyMode) {
    // this is a method header   this is a method parameter
    String numberOfMinesRequest = "Please enter the number of mines";
    System.out.println(numberOfMinesRequest);
    error = true;
    do {
      try {
        numberOfMines = scanner.nextInt();
        scanner.nextLine();// fixing
        if (numberOfMines > (numberOfColumns * numberOfRows)) {
          System.out.println("too many mines!!! try again");
          continue;
        }
        error = false;
      } catch (InputMismatchException e) {
        System.out.println("Please enter a number.");
        scanner.next();
      }
    } while (error == true);
    if (difficultyMode == true) {
      while (numberOfMines != 3000) {
        if (numberOfMines < 3000) {
          System.out.println("Hahaha! you have no free will in this mode\n\nthe "
              + "number of mines that the Omnipotent Being chose for you is much larger!");
        }
        if (numberOfMines > 3000) {
          System.out.println("Hahaha! you have no free will in this mode\n\nFortunately the "
              + "number of mines that the Omnipotent Being chose for you is much smaller!");
        }
        System.out.println("\nNow try again and return the number of mines that "
            + "the Omnipotent Being has chosen for you...");
        error = true;
        do {
          try {
            numberOfMines = scanner.nextInt();
            scanner.nextLine();// fixing
            error = false;
          } catch (InputMismatchException e) {
            System.out.println("The Omnipotent Being demands that you enter a number.");
            scanner.next();
          }
        } while (error == true);
      }
      System.out.println("The Omnipotent Being has chosen for you...\n" + "...now procede...");
    }
    if (difficultyMode == false) {
      if (numberOfMines == 3000) {
        System.out.println("Fantastic Choice!!!");
      }
      if (numberOfMines != 3000) {
        System.out.println("Good choice");
      }
    }
  }

  public static int getNumberOfMines() {
    return numberOfMines;
  }
}
