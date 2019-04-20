// Stefan Anders Hustrulid
/**
 * The Minesweeper program driver.
 * @author Stefan
 */
public class Minesweeper {
  
  /**
   * Creates the problem.
   * Accept user inputs.
   * Update the minefield the user is completing.
   * Know when to end game and if the user won or lost.
   */
  public void playMinesweeper() {
    final MineField mineField = new MineField();
    final PlayerInterface player = new PlayerInterface();

    // determine if user customizes settings or not
    Setup.setDifficultyMode();
    boolean difficultyMode = Setup.getDifficultyMode();

    // establishes number of columns
    // arguments
    Setup.setNumberOfColumns(difficultyMode);// method call
    int numberOfColumns = Setup.getNumberOfColumns();

    // establishes number of Rows
    Setup.setNumberOfRows(difficultyMode);
    int numberOfRows = Setup.getNumberOfRows();

    // establishes number of Mines
    Setup.setNumberOfMines(difficultyMode);
    int numberOfMines = Setup.getNumberOfMines();

    // determines locations of mines
    mineField.setMinePositions(numberOfMines, numberOfColumns, numberOfRows);
    int[][] mine = mineField.getMinePositions();

    // creates and fills in the grid
    mineField.setGrid(mine, numberOfMines, numberOfColumns, numberOfRows);
    int[][] grid = mineField.getGrid();

    // mineField.testGrid(grid, numberOfColumns, numberOfRows);

    // everything is hidden, player sees a blank minefield
    player.setInitialUserGrid(numberOfColumns, numberOfRows);

    boolean finished = false;
    boolean allMinesFlagged;
    boolean allSquaresShown;
    int mineNumber;
    int minesChecked;
    int squaresChecked;
    boolean lose = false;
    do { // play minesweeper

      player.setUserInputs(numberOfColumns, numberOfRows, grid);
      String[][] userInput = player.getUserInputs();

      player.displayUserGrid(numberOfColumns, numberOfRows, grid, userInput);

      minesChecked = 0;
      allMinesFlagged = false;
      squaresChecked = 0;
      allSquaresShown = false;
      lose = false;

      mineNumber = 1;
      while (mineNumber <= numberOfMines) {
        int columnNumber = mine[mineNumber][1];
        int rowNumber = mine[mineNumber][2];
        if (userInput[columnNumber][rowNumber].equals("Flagged")) {
          minesChecked++;
          // ends game if a mine is hit
        } else if (userInput[columnNumber][rowNumber].equals("Shown")) {
          finished = true;
          // and you also lose
          lose = true;
        }
        mineNumber++;
        if (minesChecked == numberOfMines) {
          allMinesFlagged = true;
        }
      }

      int rowNumber = 1;
      while (rowNumber <= numberOfRows) {
        int columnNumber = 1;
        while (columnNumber <= numberOfColumns) {
          if (userInput[columnNumber][rowNumber].equals("Shown")
              && grid[columnNumber][rowNumber] != 9) {
            squaresChecked++;
          }
          if (squaresChecked == ((numberOfColumns * numberOfRows) - numberOfMines)) {
            allSquaresShown = true;
          }
          columnNumber++;
        }
        rowNumber++;
      }
      if (allMinesFlagged == true && allSquaresShown == true) {
        finished = true;
      }
    } while (finished == false);

    if (finished == true && lose == false) {
      System.out.println("Congratulations! you beat me ;)");
    } else {
      System.out.println("Boom!!! try again");
    }
  }
}
