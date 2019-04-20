// Stefan Anders Hustrulid

/**
 * The Sudoku program driver.
 * 
 * @author Stefan
 */
public class Sudoku {

  /**
   * Generate a solution. Remove numbers. Let user play. Determine when user has won.
   */
  public void playSudoku() {
    SudokuBoardV3 sudokuBoard = new SudokuBoardV3();
    SudokuProblemMakerV2 sudoku = new SudokuProblemMakerV2();
    int[][] problem = sudoku.getProblemBoard();
    sudokuBoard.printSolution(problem);
    System.out.println("There is a chance that this problem is solvable but it is not guarenteed.");
  }
}
