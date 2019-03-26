import java.util.Random;
import java.util.Arrays;

public class SudokuBoardV3 {
  
  int[] possibleNumbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
  int[][] sudokuBoard;
  Random random = new Random();
  
  SudokuBoardV3(int size){
    sudokuBoard = new int[size][size];
    createSudokuBoard(size, 0, possibleNumbers);
    printSolution(size);
  }
  
  public boolean createSudokuBoard(int size, int point, int[] possibleNumbers){
    if(point > (size * size) - 1) {
      return true;
    }
    int columnNumber = point % size;
    int rowNumber = point / size;
    int[] possibleNumbersCopy = Arrays.copyOf(possibleNumbers, possibleNumbers.length);
    for(int numberCheck = 0; numberCheck < possibleNumbers.length; numberCheck++) {
      int possibleNumberIndexNumber = random.nextInt(possibleNumbers.length);
      while(possibleNumbersCopy[possibleNumberIndexNumber] == 0) {
        possibleNumberIndexNumber = random.nextInt(possibleNumbers.length);
      }
      sudokuBoard[columnNumber][rowNumber] = possibleNumbersCopy[possibleNumberIndexNumber];
      possibleNumbersCopy[possibleNumberIndexNumber] = 0;
      if (!isSafe(columnNumber, rowNumber, sudokuBoard[columnNumber][rowNumber])) {
        continue;
      }
      if(createSudokuBoard(size, point+1, possibleNumbers)) {
        return true;
      }
    }
    sudokuBoard[columnNumber][rowNumber] = 0;
    return false;
  }
  
  public boolean isSafe(int columnNumber, int rowNumber, int number) {
    //checks on the same row
    for (int comparedColumn = 0; comparedColumn < sudokuBoard.length; comparedColumn++) {
      if(comparedColumn == columnNumber) {
        continue;
      }
      if (sudokuBoard[comparedColumn][rowNumber] == number) {
          return false; 
      }
    }
    //checks on the same column
    for (int comparedRow = 0; comparedRow < sudokuBoard.length; comparedRow++) {
      if(comparedRow == rowNumber) {
        continue;
      }
      if (sudokuBoard[columnNumber][comparedRow] == number) {
          return false; 
      }
    }
    //checks in the same 3x3
  //top left
    if((columnNumber == 0 && rowNumber == 0) || (columnNumber == 3 && rowNumber == 0) || 
        (columnNumber == 6 && rowNumber == 0) || (columnNumber == 0 && rowNumber == 3) || 
        (columnNumber == 3 && rowNumber == 3) || (columnNumber == 6 && rowNumber == 3) || 
        (columnNumber == 0 && rowNumber == 6) || (columnNumber == 3 && rowNumber == 6) || 
        (columnNumber == 6 && rowNumber == 6)) {
      if(sudokuBoard[columnNumber][rowNumber+1] == number || sudokuBoard[columnNumber+1][rowNumber+1] == number 
          || sudokuBoard[columnNumber+2][rowNumber+1] == number || sudokuBoard[columnNumber][rowNumber+2] == 
          number || sudokuBoard[columnNumber+1][rowNumber+2] == number || 
          sudokuBoard[columnNumber+2][rowNumber+2] == number) {
        return false;
      }
    }
    //top middle
    else if((columnNumber == 1 && rowNumber == 0) || (columnNumber == 4 && rowNumber == 0) || 
        (columnNumber == 7 && rowNumber == 0) || (columnNumber == 1 && rowNumber == 3) || 
        (columnNumber == 4 && rowNumber == 3) || (columnNumber == 7 && rowNumber == 3) || 
        (columnNumber == 1 && rowNumber == 6) || (columnNumber == 4 && rowNumber == 6) || 
        (columnNumber == 7 && rowNumber == 6)) {
      if(sudokuBoard[columnNumber][rowNumber+1] == number || sudokuBoard[columnNumber+1][rowNumber+1] == number 
          || sudokuBoard[columnNumber-1][rowNumber+1] == number || sudokuBoard[columnNumber][rowNumber+2] == 
          number || sudokuBoard[columnNumber+1][rowNumber+2] == number || 
          sudokuBoard[columnNumber-1][rowNumber+2] == number) {
        return false;
      }
    }
    //top right
    else if((columnNumber == 2 && rowNumber == 0) || (columnNumber == 5 && rowNumber == 0) || 
        (columnNumber == 8 && rowNumber == 0) || (columnNumber == 2 && rowNumber == 3) || 
        (columnNumber == 5 && rowNumber == 3) || (columnNumber == 8 && rowNumber == 3) || 
        (columnNumber == 2 && rowNumber == 6) || (columnNumber == 5 && rowNumber == 6) || 
        (columnNumber == 8 && rowNumber == 6)) {
      if(sudokuBoard[columnNumber][rowNumber+1] == number || sudokuBoard[columnNumber-1][rowNumber+1] == number || 
          sudokuBoard[columnNumber-2][rowNumber+1] == number || sudokuBoard[columnNumber][rowNumber+2] == number || 
          sudokuBoard[columnNumber-1][rowNumber+2] == number || sudokuBoard[columnNumber-2][rowNumber+2] == number) {
        return false;
      }
    }
    //middle left
    else if((columnNumber == 0 && rowNumber == 1) || (columnNumber == 3 && rowNumber == 1) || 
        (columnNumber == 6 && rowNumber == 1) || (columnNumber == 0 && rowNumber == 4) || 
        (columnNumber == 3 && rowNumber == 4) || (columnNumber == 6 && rowNumber == 4) || 
        (columnNumber == 0 && rowNumber == 7) || (columnNumber == 3 && rowNumber == 7) || 
        (columnNumber == 6 && rowNumber == 7)) {
      if(sudokuBoard[columnNumber][rowNumber+1] == number || sudokuBoard[columnNumber+1][rowNumber+1] == number 
          || sudokuBoard[columnNumber+2][rowNumber+1] == number || sudokuBoard[columnNumber][rowNumber-1] == 
          number || sudokuBoard[columnNumber+1][rowNumber-1] == number || 
          sudokuBoard[columnNumber+2][rowNumber-1] == number) {
        return false;
      }
    }
    //middle middle
    else if((columnNumber == 1 && rowNumber == 1) || (columnNumber == 4 && rowNumber == 1) || 
        (columnNumber == 7 && rowNumber == 1) || (columnNumber == 1 && rowNumber == 4) || 
        (columnNumber == 4 && rowNumber == 4) || (columnNumber == 7 && rowNumber == 4) || 
        (columnNumber == 1 && rowNumber == 7) || (columnNumber == 4 && rowNumber == 7) || 
        (columnNumber == 7 && rowNumber == 7)) {
      if(sudokuBoard[columnNumber-1][rowNumber-1] == number || sudokuBoard[columnNumber][rowNumber+1] == number 
          || sudokuBoard[columnNumber+1][rowNumber+1] == number || sudokuBoard[columnNumber-1][rowNumber+1] == 
          number || sudokuBoard[columnNumber][rowNumber-1] == number || sudokuBoard[columnNumber+1][rowNumber-1] 
          == number) {
        return false;
      }
    }
    //middle right
    else if((columnNumber == 2 && rowNumber == 1) || (columnNumber == 5 && rowNumber == 1) || 
        (columnNumber == 8 && rowNumber == 1) || (columnNumber == 2 && rowNumber == 4) || 
        (columnNumber == 5 && rowNumber == 4) || (columnNumber == 8 && rowNumber == 4) || 
        (columnNumber == 2 && rowNumber == 7) || (columnNumber == 5 && rowNumber == 7) || 
        (columnNumber == 8 && rowNumber == 7)) {
      if( sudokuBoard[columnNumber][rowNumber-1] == number || sudokuBoard[columnNumber-1][rowNumber-1] == number || 
          sudokuBoard[columnNumber-2][rowNumber-1] == number || sudokuBoard[columnNumber][rowNumber+1] == number || 
          sudokuBoard[columnNumber-1][rowNumber+1] == number || sudokuBoard[columnNumber-2][rowNumber+1] == number) {
        return false;
      }
    }
    //bottom left
    else if((columnNumber == 0 && rowNumber == 2) || (columnNumber == 3 && rowNumber == 2) || 
        (columnNumber == 6 && rowNumber == 2) || (columnNumber == 0 && rowNumber == 5) || 
        (columnNumber == 3 && rowNumber == 5) || (columnNumber == 6 && rowNumber == 5) || 
        (columnNumber == 0 && rowNumber == 8) || (columnNumber == 3 && rowNumber == 8) || 
        (columnNumber == 6 && rowNumber == 8)) {
      if(sudokuBoard[columnNumber][rowNumber-1] == number || sudokuBoard[columnNumber+1][rowNumber-1] == number 
          || sudokuBoard[columnNumber+2][rowNumber-1] == number || sudokuBoard[columnNumber][rowNumber-2] == 
          number || sudokuBoard[columnNumber+1][rowNumber-2] == number || 
          sudokuBoard[columnNumber+2][rowNumber-2] == number) {
        return false;
      }
    }
    //bottom middle
    else if((columnNumber == 1 && rowNumber == 2) || (columnNumber == 4 && rowNumber == 2) || 
        (columnNumber == 7 && rowNumber == 2) || (columnNumber == 1 && rowNumber == 5) || 
        (columnNumber == 4 && rowNumber == 5) || (columnNumber == 7 && rowNumber == 5) || 
        (columnNumber == 1 && rowNumber == 8) || (columnNumber == 4 && rowNumber == 8) || 
        (columnNumber == 7 && rowNumber == 8)) {
      if(sudokuBoard[columnNumber][rowNumber-1] == number || sudokuBoard[columnNumber+1][rowNumber-1] == number 
          || sudokuBoard[columnNumber-1][rowNumber-1] == number || sudokuBoard[columnNumber][rowNumber-2] == 
          number || sudokuBoard[columnNumber+1][rowNumber-2] == number || 
          sudokuBoard[columnNumber-1][rowNumber-2] == number) {
        return false;
      }
    }
    //bottom right
    else if((columnNumber == 2 && rowNumber == 2) || (columnNumber == 5 && rowNumber == 2) || 
        (columnNumber == 8 && rowNumber == 2) || (columnNumber == 2 && rowNumber == 5) || 
        (columnNumber == 5 && rowNumber == 5) || (columnNumber == 8 && rowNumber == 5) || 
        (columnNumber == 2 && rowNumber == 8) || (columnNumber == 5 && rowNumber == 8) || 
        (columnNumber == 8 && rowNumber == 8)) {
      if(sudokuBoard[columnNumber][rowNumber-1] == number || sudokuBoard[columnNumber-1][rowNumber-1] == number || 
          sudokuBoard[columnNumber-2][rowNumber-1] == number || sudokuBoard[columnNumber][rowNumber-2] == number || 
          sudokuBoard[columnNumber-1][rowNumber-2] == number || sudokuBoard[columnNumber-2][rowNumber-2] == number) {
        return false;
      }
    }
    return true;
  }
  
  void printSolution(int size) { 
    for(int rowNumber = 0; rowNumber < size; rowNumber++) { 
      for(int columnNumber = 0; columnNumber < size; columnNumber++) {
        System.out.print(" " + sudokuBoard[columnNumber][rowNumber] + " "); 
      }
      System.out.println(); 
    } 
  } 
}
