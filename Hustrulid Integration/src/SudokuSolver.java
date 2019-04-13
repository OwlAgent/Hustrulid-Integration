//Stefan Anders Hustrulid
import java.util.Arrays;

public class SudokuSolver extends SudokuBoardV3 {
  
  int[][] testSudokuBoard;
  boolean[][] changeableSpotsBoard;
  ChangeableSpot[] changeableSpotsList;
  
  SudokuSolver(){
    psiRequirement();
  }
  
  SudokuSolver(int[][] sudokuBoard, boolean[][] changeableSpotsBoard){
    size = 9;
    testSudokuBoard = new int[size][size];
    setTestSudokuBoard(sudokuBoard);
    this.changeableSpotsBoard = changeableSpotsBoard;
    setChangeableSpotsList();
  }
  
  public void psiRequirement() {
    super.psiRequirement();
  }
  
  public int[][] getTestSudokuBoard(){
    return testSudokuBoard;
  }
  
  public void setTestSudokuBoard(int[][] sudokuBoard) {
    for(int rowNumber = 0; rowNumber < testSudokuBoard.length; rowNumber++) {
      for(int columnNumber = 0; columnNumber < testSudokuBoard[rowNumber].length; columnNumber++) {
        testSudokuBoard[rowNumber][columnNumber] = sudokuBoard[rowNumber][columnNumber];
      }
    }
  }
  
  public void setChangeableSpotsList() {
    int numberOfChangeableSpots = 0;
    for(int rowNumber = 0; rowNumber < changeableSpotsBoard.length; rowNumber++) {
      for(int columnNumber = 0; columnNumber < changeableSpotsBoard[rowNumber].length; 
          columnNumber++) {
        if(changeableSpotsBoard[rowNumber][columnNumber] == true) {
          numberOfChangeableSpots++;
        }
      }
    }
    changeableSpotsList = new ChangeableSpot[numberOfChangeableSpots];
    int spotNumber = 0;
    for(int rowNumber = 0; rowNumber < changeableSpotsBoard.length; rowNumber++) {
      for(int columnNumber = 0; columnNumber < changeableSpotsBoard[rowNumber].length; 
          columnNumber++) {
        if(changeableSpotsBoard[rowNumber][columnNumber] == true) {
          changeableSpotsList[spotNumber] = new ChangeableSpot(rowNumber, columnNumber);
          spotNumber++;
        }
      }
    }
  }
  public ChangeableSpot[] getchangeableSpotsList() {
    return changeableSpotsList;
  }
  
  public boolean solveSudokuBoard(int spotNumber, int[] possibleNumbers) {
    if(spotNumber > changeableSpotsList.length - 1) {
      return true;
    }
    int columnNumber = changeableSpotsList[spotNumber].getColumnNumber();
    int rowNumber = changeableSpotsList[spotNumber].getRowNumber();
    int[] possibleNumbersCopy = Arrays.copyOf(possibleNumbers, possibleNumbers.length);
    for(int numberCheck = 0; numberCheck < possibleNumbers.length; numberCheck++) {
      int possibleNumberIndexNumber = random.nextInt(possibleNumbers.length);
      while(possibleNumbersCopy[possibleNumberIndexNumber] == 0) {
        possibleNumberIndexNumber = random.nextInt(possibleNumbers.length);
      }
      changeableSpotsList[spotNumber].setNumber(possibleNumbersCopy[possibleNumberIndexNumber]);
      testSudokuBoard[rowNumber][columnNumber] = changeableSpotsList[spotNumber].getNumber();
      possibleNumbersCopy[possibleNumberIndexNumber] = 0;
      if (isSafe(columnNumber, rowNumber, testSudokuBoard[rowNumber][columnNumber], testSudokuBoard)) {
        continue;
      }
      if(solveSudokuBoard(spotNumber + 1, possibleNumbers)) {
        return true;
      }
    }
    changeableSpotsList[spotNumber].setNumber(0);
    testSudokuBoard[rowNumber][columnNumber] = changeableSpotsList[spotNumber].getNumber();
    return false;
  }
}
