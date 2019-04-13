//Stefan Anders Hustrulid
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class SudokuBoardV3 {
  int size = 9;
  int[] possibleNumbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
  int[][] sudokuBoard = new int[size][size];
  Random random = new Random();
  
  public void psiRequirement() {
    System.out.println("PSI 3: Use super and this to access objects and constructors\n"
        + "\tlocation: SudokuSolver.psiRequirement()"
        + "\n\tlocation: SudokuSolver(int[][], boolean[][]\n");
  }
  
  public int[][] getSudokuBoard(){
    return sudokuBoard;
  }
  
  public int[] getPossibleNumbers() {
    return possibleNumbers;
  }
  
  public int getNumber(int rowNumber, int columnNumber) {
    return sudokuBoard[rowNumber][columnNumber];
  }
  
  public void setPointTo(int rowNumber, int columnNumber, int number) {
    sudokuBoard[rowNumber][columnNumber] = number;
  }
  
  public boolean createSudokuBoard(int point, int[] possibleNumbers){
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
      sudokuBoard[rowNumber][columnNumber] = possibleNumbersCopy[possibleNumberIndexNumber];
      possibleNumbersCopy[possibleNumberIndexNumber] = 0;
      if (!isSafe(columnNumber, rowNumber, sudokuBoard[rowNumber][columnNumber], sudokuBoard)) {
        continue;
      }
      if(createSudokuBoard(point+1, possibleNumbers)) {
        return true;
      }
    }
    sudokuBoard[rowNumber][columnNumber] = 0;
    return false;
  }
  
  public boolean isSafe(int columnNumber, int rowNumber, int number, int[][] sudokuBoard) {
    if(!checkRow(rowNumber, columnNumber, number, sudokuBoard)) {
      return false;
    }
    if(!checkColumn(rowNumber, columnNumber, number, sudokuBoard)) {
      return false;
    }
    if(!checkGroup(rowNumber, columnNumber, number, sudokuBoard)) {
      return false;
    }
    return true;
  }
  
  public boolean checkRow(int rowNumber, int columnNumber, int number, int[][] sudokuBoard) {
    for (int comparedColumn = 0; comparedColumn < sudokuBoard.length; comparedColumn++) {
      if(comparedColumn == columnNumber) {
        continue;
      }
      if (sudokuBoard[rowNumber][comparedColumn] == number) {
          return false; 
      }
    }
    return true;
  }
  
  public boolean checkColumn(int rowNumber, int columnNumber, int number, int[][] sudokuBoard) {
    for (int comparedRow = 0; comparedRow < sudokuBoard.length; comparedRow++) {
      if(comparedRow == rowNumber) {
        continue;
      }
      if (sudokuBoard[comparedRow][columnNumber] == number) {
          return false; 
      }
    }
    return true;
  }
  
  public boolean checkGroup(int rowNumber, int columnNumber, int number, int[][] sudokuBoard) {
    int groupSize = (int)Math.sqrt(sudokuBoard.length);
    int startingColumn = columnNumber / groupSize * groupSize;
    int startingRow = rowNumber / groupSize * groupSize;
    int endingColumn = startingColumn + groupSize;
    int endingRow = startingRow + groupSize;
    for(;startingRow < endingRow; startingRow++) {
      for(;startingColumn < endingColumn; startingColumn++) {
        if(startingColumn == columnNumber && startingRow == rowNumber) {
          continue;
        }
        if(sudokuBoard[startingRow][startingColumn] == number) {
          return false;
        }
      }
      startingColumn = columnNumber / groupSize * groupSize;
    }
    return true;
  }
  
  public void printSolution(int[][] sudokuBoard) { 
    System.out.println("_______________________________");
    for(int rowNumber = 0; rowNumber < size; rowNumber++) { 
      System.out.print("|");
      for(int columnNumber = 0; columnNumber < size; columnNumber++) {
        if(sudokuBoard[rowNumber][columnNumber] == 0) {
          System.out.print("   "); 
        }
        else {
          System.out.print(" " + sudokuBoard[rowNumber][columnNumber] + " "); 
        }
        //System.out.print(" " + sudokuBoard[rowNumber][columnNumber] + " "); 
        if(columnNumber == 2 || columnNumber == 5) {
          System.out.print("|");
        }
      }
      System.out.println("|"); 
      if(rowNumber == 2 || rowNumber == 5 || rowNumber == 8) {
        System.out.println("|_________|_________|_________|");
      }
    } 
  }
}
