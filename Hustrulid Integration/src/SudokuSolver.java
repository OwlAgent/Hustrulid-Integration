// Stefan Anders Hustrulid
import java.util.Arrays;

/**
 * Solves sudoku puzzles.
 * 
 * @author Stefan
 */
public class SudokuSolver extends SudokuBoardV3 {

  /**
   * Copy of problemBoard to be solved.
   */
  int[][] testSudokuBoard;
  /**
   * board that shows what spots are changeable and which are not.
   */
  boolean[][] changeableSpotsBoard;
  /**
   * Contains the location of changeable spots and the numbers displayed there.
   */
  ChangeableSpot[] changeableSpotsList;

  SudokuSolver() {
    psiRequirement();
  }

  /**
   * Solves sudoku puzzle.
   * 
   * @param sudokuBoard The problem board.
   * @param changeableSpotsBoard Which spots are changeable and which are not.
   */
  SudokuSolver(int[][] sudokuBoard, boolean[][] changeableSpotsBoard) {
    size = 9;
    testSudokuBoard = new int[size][size];
    setTestSudokuBoard(sudokuBoard);
    this.changeableSpotsBoard = changeableSpotsBoard;
    setChangeableSpotsList();
  }

  public void methodUsedByPsiToFixBug() {

  }

  public void psiRequirement() {
    super.psiRequirement();
  }

  public int[][] getTestSudokuBoard() {
    return testSudokuBoard;
  }

  /**
   * Makes a copy of the problem board to be tested and solved.
   * 
   * @param sudokuBoard The problem board.
   */
  public void setTestSudokuBoard(int[][] sudokuBoard) {
    for (int rowNumber = 0; rowNumber < testSudokuBoard.length; rowNumber++) {
      for (int columnNumber = 0; columnNumber < testSudokuBoard[rowNumber].length; columnNumber++) {
        testSudokuBoard[rowNumber][columnNumber] = sudokuBoard[rowNumber][columnNumber];
      }
    }
  }

  /**
   * Creates list of what spots are changeable and their locations.
   */
  public void setChangeableSpotsList() {
    int numberOfChangeableSpots = 0;
    for (int rowNumber = 0; rowNumber < changeableSpotsBoard.length; rowNumber++) {
      for (int columnNumber =
          0; columnNumber < changeableSpotsBoard[rowNumber].length; columnNumber++) {
        if (changeableSpotsBoard[rowNumber][columnNumber] == true) {
          numberOfChangeableSpots++;
        }
      }
    }
    changeableSpotsList = new ChangeableSpot[numberOfChangeableSpots];
    int spotNumber = 0;
    for (int rowNumber = 0; rowNumber < changeableSpotsBoard.length; rowNumber++) {
      for (int columnNumber =
          0; columnNumber < changeableSpotsBoard[rowNumber].length; columnNumber++) {
        if (changeableSpotsBoard[rowNumber][columnNumber] == true) {
          changeableSpotsList[spotNumber] = new ChangeableSpot(rowNumber, columnNumber);
          spotNumber++;
        }
      }
    }
  }

  public ChangeableSpot[] getchangeableSpotsList() {
    return changeableSpotsList;
  }

  /**
   * Places a number at each changeable spot as long as it meets the sudoku requirements. Only goes
   * through changeable spots, not the entire board.
   * 
   * @param spotNumber Which of the changeable spots is being placed.
   * @param possibleNumbers The numbers that can be placed at that spot.
   * @return If a number has successfully been placed at that spot.
   */
  public boolean solveSudokuBoard(int spotNumber, int[] possibleNumbers) {
    if (spotNumber > changeableSpotsList.length - 1) {
      return true;
    }
    int columnNumber = changeableSpotsList[spotNumber].getColumnNumber();
    int rowNumber = changeableSpotsList[spotNumber].getRowNumber();
    int[] possibleNumbersCopy = Arrays.copyOf(possibleNumbers, possibleNumbers.length);
    for (int numberCheck = 0; numberCheck < possibleNumbers.length; numberCheck++) {
      int possibleNumberIndexNumber = random.nextInt(possibleNumbers.length);
      while (possibleNumbersCopy[possibleNumberIndexNumber] == 0) {
        possibleNumberIndexNumber = random.nextInt(possibleNumbers.length);
      }
      changeableSpotsList[spotNumber].setNumber(possibleNumbersCopy[possibleNumberIndexNumber]);
      testSudokuBoard[rowNumber][columnNumber] = changeableSpotsList[spotNumber].getNumber();
      possibleNumbersCopy[possibleNumberIndexNumber] = 0;
      if (isSafe(columnNumber, rowNumber, testSudokuBoard[rowNumber][columnNumber],
          testSudokuBoard)) {
        continue;
      }
      if (solveSudokuBoard(spotNumber + 1, possibleNumbers)) {
        return true;
      }
    }
    changeableSpotsList[spotNumber].setNumber(0);
    testSudokuBoard[rowNumber][columnNumber] = changeableSpotsList[spotNumber].getNumber();
    return false;
  }
}
