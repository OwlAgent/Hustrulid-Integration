// Stefan Anders Hustrulid
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Creates the sudoku board and removes points to create a problem.
 * 
 * @author Stefan
 */
public class SudokuProblemMakerV2 {

  /**
   * The answer board maker.
   */
  private SudokuBoardV3 sudokuBoard;
  /**
   * Edits and manages changebBoard.
   */
  private PointRemover pointRemover;
  /**
   * List of which points are removed and changeable.
   */
  private ArrayList<Integer> removablePoints;
  /**
   * The filled in sudoku.
   */
  private int[][] answerBoard;
  /**
   * The sudoku with points removed.
   */
  private int[][] problemBoard;
  /**
   * Grid of which points are changeable and which are not.
   */
  private boolean[][] changeBoard;

  /**
   * Creates the answer and problem sudoku boards.
   */
  SudokuProblemMakerV2() {
    sudokuBoard = new SudokuBoardV3();
    pointRemover = new PointRemover();
    int[] possibleNumbers = sudokuBoard.getPossibleNumbers();
    sudokuBoard.createSudokuBoard(0, possibleNumbers);
    answerBoard = sudokuBoard.getSudokuBoard();
    createProblemBoard();
    changeBoard = pointRemover.getChangeableSpotsBoard();
    setRemovablePoints();
    setProblemBoard();
    sudokuBoard.printSolution(answerBoard);
    sudokuBoard.printSolution(problemBoard);
  }

  public int[][] getAnswerBoard() {
    return answerBoard;
  }

  public int[][] getProblemBoard() {
    return problemBoard;
  }

  public boolean[][] getChangeBoard() {
    return changeBoard;
  }

  /**
   * Creates the initial problem board as a copy of the answer board.
   */
  public void createProblemBoard() {
    problemBoard = new int[answerBoard.length][answerBoard.length];
    for (int rowNumber = 0; rowNumber < answerBoard.length; rowNumber++) {
      for (int columnNumber = 0; columnNumber < answerBoard.length; columnNumber++) {
        problemBoard[rowNumber][columnNumber] = answerBoard[rowNumber][columnNumber];
      }
    }
  }

  /**
   * Removes points from the sudoku board as long as they keep the sudoku solvable.
   */
  public void setProblemBoard() {
    for (int removablePoint = 0; removablePoint < removablePoints.size(); removablePoint++) {
      int columnNumber = removablePoints.get(removablePoint) % answerBoard.length;
      int rowNumber = removablePoints.get(removablePoint) / answerBoard.length;
      pointRemover.setChangeableSpotsBoard(rowNumber, columnNumber, true);
      problemBoard[rowNumber][columnNumber] = 0;
      if (!test1() || !test2()) {
        pointRemover.setChangeableSpotsBoard(rowNumber, columnNumber, false);
        removablePoints.remove(removablePoint);
        problemBoard[rowNumber][columnNumber] = answerBoard[rowNumber][columnNumber];
        continue;// if both conditions are not met do not execute the next code and try the next
        // point
      }
      removablePoints.remove(removablePoint);
      removablePoints.trimToSize();
      removablePoint = 0;
    }
  }

  /**
   * Tests if problem has a unique solution by using multiple solvers.
   * 
   * @return If the sudoku problem has a unique solution.
   */
  public boolean test1() {
    int[] possibleNumbers = sudokuBoard.getPossibleNumbers();
    SudokuSolver tester = new SudokuSolver(problemBoard, changeBoard);
    int[][] testBoard;
    for (int test = 0; test < 1000; test++) {
      tester.solveSudokuBoard(0, possibleNumbers);
      testBoard = tester.getTestSudokuBoard();
      if (!Arrays.deepEquals(testBoard, answerBoard)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Tests if at least one spot has only one possibility.
   * 
   * @return If the condition is met.
   */
  public boolean test2() {
    for (int point = 0; point < ((changeBoard.length * changeBoard.length) - 1); point++) {
      int columnNumber = point % changeBoard.length;
      int rowNumber = point / changeBoard.length;
      if (changeBoard[rowNumber][columnNumber] == true) {
        int numberOfPossibilities = 0;
        int testNumber = 1;
        for (; testNumber < answerBoard.length + 1; testNumber++) {
          if (sudokuBoard.isSafe(columnNumber, rowNumber, testNumber, problemBoard)) {
            numberOfPossibilities++;
          }
          if (numberOfPossibilities > 1) {
            break;// when # of possibilities > 1 there is no point in looking for more so break out
            // of the loop
          }
        }
        if (numberOfPossibilities == 1) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Creates list of which points are removable.
   */
  public void setRemovablePoints() {
    int numberOfRemovablePoints = 0;
    for (int rowNumber = 0; rowNumber < changeBoard.length; rowNumber++) {
      for (int columnNumber = 0; columnNumber < changeBoard.length; columnNumber++) {
        if (!changeBoard[rowNumber][columnNumber]) {
          numberOfRemovablePoints++;
        }
      }
    }
    removablePoints = new ArrayList<Integer>(numberOfRemovablePoints);
    int index = 0;
    for (int rowNumber = 0; rowNumber < changeBoard.length; rowNumber++) {
      for (int columnNumber = 0; columnNumber < changeBoard.length; columnNumber++) {
        if (!changeBoard[rowNumber][columnNumber]) {
          removablePoints.add(index++);
        }
      }
    }
    Collections.shuffle(removablePoints);
  }
}
