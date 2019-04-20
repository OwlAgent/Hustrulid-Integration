// Stefan Anders Hustrulid
import java.util.Random;

/**
 * Generates the initial minefield problem.
 * 
 * @author Stefan
 */
public class MineField {

  /**
   * The minefield solution grid.
   */
  private int[][] point;
  private Random random = new Random();
  /**
   * The double array that stores mine number and position. Each mine has an array of an x and a y
   * position.
   */
  private int[][] mine;

  /**
   * Generates a random coordinate for a mine.
   * 
   * @param mineNumber Which mine is being placed.
   * @param numberOfColumns How many columns are in the minefield.
   * @param numberOfRows How many rows are in the minefield.
   * @return the x and y coordinate of that particular mine.
   */
  public int[][] generateRandomCoordinate(int mineNumber, int numberOfColumns, int numberOfRows) {
    int columnNumberIsOneRowNumberIsTwo = 1;
    while (columnNumberIsOneRowNumberIsTwo <= 2) {
      if (columnNumberIsOneRowNumberIsTwo == 1) {
        mine[mineNumber][columnNumberIsOneRowNumberIsTwo] = random.nextInt(numberOfColumns) + 1;
      }
      if (columnNumberIsOneRowNumberIsTwo == 2) {
        mine[mineNumber][columnNumberIsOneRowNumberIsTwo] = random.nextInt(numberOfRows) + 1;
      }
      columnNumberIsOneRowNumberIsTwo++;
    }
    return mine;
  }

  /**
   * Determines where each mine is located.
   * 
   * @param numberOfMines The number of mines to be placed.
   * @param numberOfColumns How many Columns are in the minefield.
   * @param numberOfRows How many Rows are in the minefield.
   */
  public void setMinePositions(int numberOfMines, int numberOfColumns, int numberOfRows) {
    mine = new int[numberOfMines + 1][3];
    int mineNumber = 1;
    while (mineNumber <= numberOfMines) {
      mine = generateRandomCoordinate(mineNumber, numberOfColumns, numberOfRows);
      if (mineNumber > 1) {
        int mineComparedTo = 1;
        while (mineComparedTo < mineNumber) {
          if (mine[mineNumber][1] == mine[mineComparedTo][1]
              && mine[mineNumber][2] == mine[mineComparedTo][2]) {
            mine = generateRandomCoordinate(mineNumber, numberOfColumns, numberOfRows);
          } else if (mine[mineNumber][1] != mine[mineComparedTo][1]
              || mine[mineNumber][2] != mine[mineComparedTo][2]) {
            mineComparedTo++;
          }
        }
      }
      mineNumber++;
    }
    System.out.println("\n");
  }

  public int[][] getMinePositions() {
    return mine;
  }

  /**
   * Creates the initial minefield with mines marked with 9 and all other points as 0. The other
   * numbers are to be added later.
   * 
   * @param mine The list of mines and locations.
   * @param numberOfMines How many mines are to be placed.
   * @param numberOfColumns How many columns are in the minefield.
   * @param numberOfRows How many rows are in the minefield.
   */
  public void setGrid(int[][] mine, int numberOfMines, int numberOfColumns, int numberOfRows) {
    int mineNumber = 1;
    point = new int[numberOfColumns + 1][numberOfRows + 1];
    int rowNumber = 1;
    while (rowNumber <= numberOfRows) {
      int columnNumber = 1;
      while (columnNumber <= numberOfColumns) {
        if (mineNumber <= numberOfMines) {
          while (mineNumber <= numberOfMines) {
            if (columnNumber == mine[mineNumber][1] && rowNumber == mine[mineNumber][2]) {
              point[columnNumber][rowNumber] = 9;
            }
            mineNumber++;
          }
          mineNumber = 1;
        } else {
          point[columnNumber][rowNumber] = 0;
        }
        columnNumber++;
      }
      rowNumber++;
    }
    point = getPlotNumbers(point, numberOfMines, numberOfColumns, numberOfRows);
  }

  public int[][] getGrid() {
    return point;
  }

  /**
   * At the location of each mine, add 1 to every point around the mine unless encountering another
   * mine. The surrounding points differ based on if the mine is located on an edge, in a corner, or
   * in the middle.
   * 
   * @param point The initial grid.
   * @param numberOfMines How many mines are placed.
   * @param numberOfColumns How many columns are in the minefield.
   * @param numberOfRows How many rows are in the minefield.
   * @return the completed grid.
   */
  public int[][] getPlotNumbers(int[][] point, int numberOfMines, int numberOfColumns,
      int numberOfRows) {
    int mineNumber = 1;
    while (mineNumber <= numberOfMines) {
      int columnNumber = mine[mineNumber][1];
      int rowNumber = mine[mineNumber][2];

      if (columnNumber == 1 && rowNumber == 1) { // top left corner
        if (point[columnNumber + 1][rowNumber] != 9) {
          point[columnNumber + 1][rowNumber]++;
        }
        if (point[columnNumber + 1][rowNumber + 1] != 9) {
          point[columnNumber + 1][rowNumber + 1]++;
        }
        if (point[columnNumber][rowNumber + 1] != 9) {
          point[columnNumber][rowNumber + 1]++;
        }
      } else if (columnNumber == 1 && (rowNumber != 1 && rowNumber != numberOfRows)) { // left edge
        if (point[columnNumber][rowNumber - 1] != 9) {
          point[columnNumber][rowNumber - 1]++;
        }
        if (point[columnNumber + 1][rowNumber - 1] != 9) {
          point[columnNumber + 1][rowNumber - 1]++;
        }
        if (point[columnNumber + 1][rowNumber] != 9) {
          point[columnNumber + 1][rowNumber]++;
        }
        if (point[columnNumber + 1][rowNumber + 1] != 9) {
          point[columnNumber + 1][rowNumber + 1]++;
        }
        if (point[columnNumber][rowNumber + 1] != 9) {
          point[columnNumber][rowNumber + 1]++;
        }
      } else if (columnNumber == 1 && rowNumber == numberOfRows) { // bottom left corner
        if (point[columnNumber][rowNumber - 1] != 9) {
          point[columnNumber][rowNumber - 1]++;
        }
        if (point[columnNumber + 1][rowNumber - 1] != 9) {
          point[columnNumber + 1][rowNumber - 1]++;
        }
        if (point[columnNumber + 1][rowNumber] != 9) {
          point[columnNumber + 1][rowNumber]++;
        }
      } else if ((columnNumber != 1 && columnNumber != numberOfColumns) && rowNumber == 1) {
        // top edge
        if (point[columnNumber + 1][rowNumber] != 9) {
          point[columnNumber + 1][rowNumber]++;
        }
        if (point[columnNumber + 1][rowNumber + 1] != 9) {
          point[columnNumber + 1][rowNumber + 1]++;
        }
        if (point[columnNumber][rowNumber + 1] != 9) {
          point[columnNumber][rowNumber + 1]++;
        }
        if (point[columnNumber - 1][rowNumber + 1] != 9) {
          point[columnNumber - 1][rowNumber + 1]++;
        }
        if (point[columnNumber - 1][rowNumber] != 9) {
          point[columnNumber - 1][rowNumber]++;
        }
      } else if (columnNumber == numberOfColumns && rowNumber == 1) { // top right corner
        if (point[columnNumber - 1][rowNumber] != 9) {
          point[columnNumber - 1][rowNumber]++;
        }
        if (point[columnNumber - 1][rowNumber + 1] != 9) {
          point[columnNumber - 1][rowNumber + 1]++;
        }
        if (point[columnNumber][rowNumber + 1] != 9) {
          point[columnNumber][rowNumber + 1]++;
        }
      } else if (columnNumber == numberOfColumns && (rowNumber != 1 && rowNumber != numberOfRows)) {
        // right edge
        if (point[columnNumber][rowNumber - 1] != 9) {
          point[columnNumber][rowNumber - 1]++;
        }
        if (point[columnNumber - 1][rowNumber - 1] != 9) {
          point[columnNumber - 1][rowNumber - 1]++;
        }
        if (point[columnNumber - 1][rowNumber] != 9) {
          point[columnNumber - 1][rowNumber]++;
        }
        if (point[columnNumber - 1][rowNumber + 1] != 9) {
          point[columnNumber - 1][rowNumber + 1]++;
        }
        if (point[columnNumber][rowNumber + 1] != 9) {
          point[columnNumber][rowNumber + 1]++;
        }
      } else if (columnNumber == numberOfColumns && rowNumber == numberOfRows) {
        // bottom right corner
        if (point[columnNumber - 1][rowNumber] != 9) {
          point[columnNumber - 1][rowNumber]++;
        }
        if (point[columnNumber - 1][rowNumber - 1] != 9) {
          point[columnNumber - 1][rowNumber - 1]++;
        }
        if (point[columnNumber][rowNumber - 1] != 9) {
          point[columnNumber][rowNumber - 1]++;
        }
      } else if ((columnNumber != 1 && columnNumber != numberOfColumns)
          && rowNumber == numberOfRows) {
        // bottom edge
        if (point[columnNumber - 1][rowNumber] != 9) {
          point[columnNumber - 1][rowNumber]++;
        }
        if (point[columnNumber - 1][rowNumber - 1] != 9) {
          point[columnNumber - 1][rowNumber - 1]++;
        }
        if (point[columnNumber][rowNumber - 1] != 9) {
          point[columnNumber][rowNumber - 1]++;
        }
        if (point[columnNumber + 1][rowNumber - 1] != 9) {
          point[columnNumber + 1][rowNumber - 1]++;
        }
        if (point[columnNumber + 1][rowNumber] != 9) {
          point[columnNumber + 1][rowNumber]++;
        }
      } else if ((columnNumber != 1 && columnNumber != numberOfColumns)
          && (rowNumber != 1 && rowNumber != numberOfRows)) { // not on the edge or corner
        if (point[columnNumber - 1][rowNumber - 1] != 9) {
          point[columnNumber - 1][rowNumber - 1]++;
        }
        if (point[columnNumber][rowNumber - 1] != 9) {
          point[columnNumber][rowNumber - 1]++;
        }
        if (point[columnNumber + 1][rowNumber - 1] != 9) {
          point[columnNumber + 1][rowNumber - 1]++;
        }
        if (point[columnNumber + 1][rowNumber] != 9) {
          point[columnNumber + 1][rowNumber]++;
        }
        if (point[columnNumber + 1][rowNumber + 1] != 9) {
          point[columnNumber + 1][rowNumber + 1]++;
        }
        if (point[columnNumber][rowNumber + 1] != 9) {
          point[columnNumber][rowNumber + 1]++;
        }
        if (point[columnNumber - 1][rowNumber + 1] != 9) {
          point[columnNumber - 1][rowNumber + 1]++;
        }
        if (point[columnNumber - 1][rowNumber] != 9) {
          point[columnNumber - 1][rowNumber]++;
        }
      }
      mineNumber++;
    }
    return point;
  }

  /**
   * Prints the solution grid with "*" marking the mines.
   * 
   * @param point The solution grid.
   * @param numberOfColumns How many columns are in the minefield.
   * @param numberOfRows How many rows are in the minefield.
   */
  public void testGrid(int[][] point, int numberOfColumns, int numberOfRows) {
    int j = 1;
    while (j <= numberOfRows) {
      int i = 1;
      while (i <= numberOfColumns) {
        System.out.print(point[i][j] == 9 ? "* " : point[i][j] + " ");
        i++;
      }
      System.out.print("\n");
      j++;
    }

  }
}
