// Stefan Anders Hustrulid
import java.util.Scanner;

/**
 * Accepts user inputs and displays the minefield board to the user.
 * 
 * @author Stefan
 */
public class PlayerInterface {

  static Scanner scanner = new Scanner(System.in);
  /**
   * Grid that holds information on whether a point is Shown, Hidden, or Flagged.
   */
  String[][] userInput;
  /**
   * Variable used to loop try blocks.
   */
  boolean error;

  /**
   * Sets entire minefield board as hidden and displays a blank grid to the user.
   * 
   * @param numberOfColumns The number of columns in the minefield.
   * @param numberOfRows The number of rows in the minefield.
   */
  public void setInitialUserGrid(int numberOfColumns, int numberOfRows) {
    userInput = new String[numberOfColumns + 1][numberOfRows + 1];
    int rowNumber;
    int columnNumber;
    for (rowNumber = 1; rowNumber <= numberOfRows; rowNumber++) {
      for (columnNumber = 1; columnNumber <= numberOfColumns; columnNumber++) {
        userInput[columnNumber][rowNumber] = "Hidden";
        System.out.print("# ");
      }
      System.out.print("\n");
    }
  }

  /**
   * Displays the minefield grid to the user. What is displayed is dependent on the userInput
   * 
   * @param numberOfColumns The number of columns in the minefield.
   * @param numberOfRows The number of rows in the minefield.
   * @param points The answer minefield grid.
   * @param userInput The grid that shows the state of each point.
   */
  public void displayUserGrid(int numberOfColumns, int numberOfRows, int[][] points,
      String[][] userInput) {
    int rowNumber;
    int columnNumber;
    for (rowNumber = 1; rowNumber <= numberOfRows; rowNumber++) {
      for (columnNumber = 1; columnNumber <= numberOfColumns; columnNumber++) {
        if (userInput[columnNumber][rowNumber].equals("Hidden")) {
          System.out.print("# ");
        } else if (userInput[columnNumber][rowNumber].equals("Shown")) {
          if (points[columnNumber][rowNumber] == 9) {

            System.out.print("X ");
          } else {
            System.out.print(points[columnNumber][rowNumber] + " ");
          }
        } else if (userInput[columnNumber][rowNumber].equals("Flagged")) {
          System.out.print("* ");
        }
      }
      System.out.print("\n");
    }
  }

  /**
   * Gets coordinate from user then initiates collectAct.
   * 
   * @param numberOfColumns The number of columns in the minefield.
   * @param numberOfRows The number of rows in the minefield.
   * @param point The answer minefield grid.
   */
  public void setUserInputs(int numberOfColumns, int numberOfRows, int[][] point) {
    System.out.println("Please Enter the Column you wish to select");
    String columnNumberString = scanner.nextLine();
    if (columnNumberString.equals("esc")) {
      setUserInputs(numberOfColumns, numberOfRows, point);
    } else {
      error = true;
      int columnNumber = 0;
      do {
        try {
          columnNumber = Integer.parseInt(columnNumberString);
          if (columnNumber > numberOfColumns || columnNumber < 1) {
            throw new ArrayIndexOutOfBoundsException();
          }
          System.out.println("You have chosen column number: " + Math.abs(columnNumber));
          error = false;
        } catch (NumberFormatException e) {
          System.out.println("Please enter a number");
          columnNumberString = scanner.nextLine();
        } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("Please try another column");
          columnNumberString = scanner.nextLine();
        }
      } while (error == true);

      System.out.println("Please Enter the Row you wish to select");
      String rowNumberString = scanner.nextLine();
      if (rowNumberString.equals("esc")) {
        setUserInputs(numberOfColumns, numberOfRows, point);
      } else {
        error = true;
        int rowNumber = 0;
        do {
          try {
            rowNumber = Integer.parseInt(rowNumberString);
            if (rowNumber > numberOfRows || rowNumber < 1) {
              throw new ArrayIndexOutOfBoundsException();
            }
            System.out.println("You have chosen row number: " + Math.abs(rowNumber));
            error = false;
          } catch (NumberFormatException e) {
            System.out.println("Please enter a number");
            rowNumberString = scanner.nextLine();
          } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please try another row");
            rowNumberString = scanner.nextLine();
          }
        } while (error == true);
        if (userInput[columnNumber][rowNumber] == "Shown") {
          System.out.println("This square is already Shown... \nTry another square");
          setUserInputs(numberOfColumns, numberOfRows, point);
        }
        collectAct(numberOfColumns, numberOfRows, columnNumber, rowNumber, point);
      }
    }
  }

  /**
   * Updates userInput[] with the action the user inputs.
   * 
   * @param numberOfColumns The number of columns in the minefield.
   * @param numberOfRows The number of rows in the minefield.
   * @param columnNumber The x coordinate.
   * @param rowNumber The y coordinate.
   * @param point The answer minefield grid.
   */
  public void collectAct(int numberOfColumns, int numberOfRows, int columnNumber, int rowNumber,
      int[][] point) {
    System.out.println("Do you wish to SELECT, FLAG, or UNFLAG?");
    String act = scanner.nextLine();
    if (act.equals("esc")) {
      setUserInputs(numberOfColumns, numberOfRows, point);
    } else if (act.equals("UNFLAG")) {
      if (userInput[columnNumber][rowNumber] != "Flagged") {
        System.out.println("this is not a Flagged square... \nTry another command");
        collectAct(numberOfColumns, numberOfRows, columnNumber, rowNumber, point);
      } else {
        userInput[columnNumber][rowNumber] = "Hidden";
      }
    } else if (act.equals("FLAG")) {
      if (userInput[columnNumber][rowNumber] == "Flagged") {
        System.out.println("This square is already Flagged... \nTry another command");
        collectAct(numberOfColumns, numberOfRows, columnNumber, rowNumber, point);
      } else {
        userInput[columnNumber][rowNumber] = "Flagged";
      }
    } else if (act.equals("SELECT")) {
      if (userInput[columnNumber][rowNumber] == "Flagged") {
        System.out.println(
            "This square is Flagged, if you wish to SELECT it you must first " + "UNFLAG it");
        collectAct(numberOfColumns, numberOfRows, columnNumber, rowNumber, point);
      } else if (point[columnNumber][rowNumber] == 0) {
        showGroups(numberOfColumns, numberOfRows, userInput, point, columnNumber, rowNumber);
      }
      userInput[columnNumber][rowNumber] = "Shown";
    } else {
      System.out.println("I'm sorry, could you repeat that?");
      collectAct(numberOfColumns, numberOfRows, columnNumber, rowNumber, point);
    }
  }

  // this next method fills in the groups of zeroes when one is revealed. I did some research and
  // learned that I needed something called a recursive method, then I did some more research to
  // figure out how to use a recursive method for this particular problem.
  // I learned the following:
  // 1st step: check if point is in bounds, if it is not in bounds the method will be returned;
  // (back to itself)
  // 2nd step: check if point meets conditions (equals 0 and is not already shown)
  // 3rd step: call itself to check adjacent points, if one is out of bounds it will return to
  // where it was on the previous run of the method
  // 4st step: return

  // https://introcs.cs.princeton.edu/java/23recursion/
  // http://www.bowdoin.edu/~ltoma/teaching/cs210/fall07/Lectures/Blob.pdf
  // https://stackoverflow.com/questions/19106911/recursive-minesweeper-0-fill
  // http://www.cs.loyola.edu/~jglenn/631/S2007/Examples/Recursion/flood_fill.html
  // https://www.dreamincode.net/forums/topic/102551-minesweeper-recursion/
  /**
   * Fills in the groups of zeroes when one is revealed.
   * 
   * @param numberOfColumns The number of columns in the minefield.
   * @param numberOfRows The number of rows in the minefield.
   * @param userInput The grid that shows the state of each point.
   * @param point The answer minesweeper grid.
   * @param columnNumber The x coordinate.
   * @param rowNumber The y coordinate.
   */
  public void showGroups(int numberOfColumns, int numberOfRows, String[][] userInput, int[][] point,
      int columnNumber, int rowNumber) {
    // check if in bounds
    if (columnNumber < 1 || columnNumber > numberOfColumns || rowNumber < 1
        || rowNumber > numberOfRows) {
      // if not in bounds, return to previous run of method
      return;
    }
    // check if 0 and not shown(aka hidden)
    if (point[columnNumber][rowNumber] == 0
        && userInput[columnNumber][rowNumber].equals("Hidden")) {
      // set point as Shown
      userInput[columnNumber][rowNumber] = "Shown";
      // top left
      showGroups(numberOfColumns, numberOfRows, userInput, point, columnNumber - 1, rowNumber - 1);
      // top middle
      showGroups(numberOfColumns, numberOfRows, userInput, point, columnNumber, rowNumber - 1);
      // top right
      showGroups(numberOfColumns, numberOfRows, userInput, point, columnNumber + 1, rowNumber - 1);
      // middle right
      showGroups(numberOfColumns, numberOfRows, userInput, point, columnNumber + 1, rowNumber);
      // bottom right
      showGroups(numberOfColumns, numberOfRows, userInput, point, columnNumber + 1, rowNumber + 1);
      // bottom middle
      showGroups(numberOfColumns, numberOfRows, userInput, point, columnNumber, rowNumber + 1);
      // bottom left
      showGroups(numberOfColumns, numberOfRows, userInput, point, columnNumber - 1, rowNumber + 1);
      // middle left
      showGroups(numberOfColumns, numberOfRows, userInput, point, columnNumber - 1, rowNumber);
      // my own addition: also shows adjacent squares that are not equal to 0
    } else if (point[columnNumber][rowNumber] != 0
        && userInput[columnNumber][rowNumber].equals("Hidden")) {
      userInput[columnNumber][rowNumber] = "Shown";
      return;
    } else {
      return;
    }
  }

  public String[][] getUserInputs() {
    return userInput;
  }
}
