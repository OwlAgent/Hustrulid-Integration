import java.util.Random;

//Stefan Hustrulid
public class SudokuBoardV2 {
  final int N = 9;
  
  int[][] createSudokuBoard() {
    int[][] board = {{0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0, 0, 0}};
    //randomize the order of the columns
    Random rnd = new Random();
    int[] order = new int[N];
    for(int counter = 0; counter < N; counter ++) {
      order[counter] = rnd.nextInt(N);
      if(counter > 0) {
        int counterComparedTo = 0;
        while(counterComparedTo < counter) {
          if(order[counter] == order[counterComparedTo]) {
            order[counter] = rnd.nextInt(N);
            counterComparedTo = 0;
          }
          else {
            ++counterComparedTo;
          }
        }    
      }
    }
   //run for each number
    
    for(int number = 1; number <= 9; number++) {
      
      boolean sudokuBoard;
      do {
        sudokuBoard = solveSudokuBoard(board, 0, number, order);
        if(sudokuBoard == false){
          board = {{0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
              {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0},
              {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
              {0, 0, 0, 0, 0, 0, 0, 0, 0}};
        }
      }
      while(sudokuBoard == false); // run while it returns false
      
      /*
      if(solveSudokuBoard(board, 0, number, order) == false) {
        solveSudokuBoard(board, 0, number, order);
        //createSudokuBoard();
      }
      */
     // printSolution(board);
   //   System.out.println("\n");
    }
    printSolution(board);
    return board;  
  }
  
  boolean solveSudokuBoard(int[][] board, int columnOrder, int number, int[] order) {
    //return when at end of columns
    if(columnOrder >= N) {
      return true;
    }
    //on each row of the set column
    for(int rowNumber = 0; rowNumber < N; rowNumber++) {
      if(isSafe(board, order[columnOrder], rowNumber, number)) {
        //if safe set spot to number
        board[order[columnOrder]][rowNumber] = number;
        if(solveSudokuBoard(board, columnOrder+1, number, order) == true) {
          // if next column is safe return
          return true;
        }
        //if next column is not safe set spot back to 0 and return false so next row on the column 
        //is checked
        board[order[columnOrder]][rowNumber] = 0;
      }
    }
    return false;
  }
  
  boolean isSafe(int[][] board, int columnNumber, int rowNumber, int number) {
    
    int comparedColumn;
    //checks on the same row to the left
    for (comparedColumn = 0; comparedColumn < columnNumber; comparedColumn++) {
      if (board[comparedColumn][rowNumber] == number) {
          return false; 
      }
    }
    
    //checks on the same row to the right
    for (comparedColumn = 8; comparedColumn > columnNumber; comparedColumn--) {
      if (board[comparedColumn][rowNumber] == number) {
          return false; 
      }
    }
    
    //checks that space is empty
    if(board[columnNumber][rowNumber] > 0) {
      return false;
    }
    
    //checks if number is already in that square
    if(board[columnNumber][rowNumber] == number) {
      return true;
    }
    
    //checks all of the other spaces in the 3x3 group
    //differs depending on location of square in the group
    //do not need to check squares on the same row again
    //top left
    if((columnNumber == 0 && rowNumber == 0) || (columnNumber == 3 && rowNumber == 0) || 
        (columnNumber == 6 && rowNumber == 0) || (columnNumber == 0 && rowNumber == 3) || 
        (columnNumber == 3 && rowNumber == 3) || (columnNumber == 6 && rowNumber == 3) || 
        (columnNumber == 0 && rowNumber == 6) || (columnNumber == 3 && rowNumber == 6) || 
        (columnNumber == 6 && rowNumber == 6)) {
      if(board[columnNumber][rowNumber+1] == number || board[columnNumber+1][rowNumber+1] == number 
          || board[columnNumber+2][rowNumber+1] == number || board[columnNumber][rowNumber+2] == 
          number || board[columnNumber+1][rowNumber+2] == number || 
          board[columnNumber+2][rowNumber+2] == number) {
        return false;
      }
    }
    //top middle
    else if((columnNumber == 1 && rowNumber == 0) || (columnNumber == 4 && rowNumber == 0) || 
        (columnNumber == 7 && rowNumber == 0) || (columnNumber == 1 && rowNumber == 3) || 
        (columnNumber == 4 && rowNumber == 3) || (columnNumber == 7 && rowNumber == 3) || 
        (columnNumber == 1 && rowNumber == 6) || (columnNumber == 4 && rowNumber == 6) || 
        (columnNumber == 7 && rowNumber == 6)) {
      if(board[columnNumber][rowNumber+1] == number || board[columnNumber+1][rowNumber+1] == number 
          || board[columnNumber-1][rowNumber+1] == number || board[columnNumber][rowNumber+2] == 
          number || board[columnNumber+1][rowNumber+2] == number || 
          board[columnNumber-1][rowNumber+2] == number) {
        return false;
      }
    }
    //top right
    else if((columnNumber == 2 && rowNumber == 0) || (columnNumber == 5 && rowNumber == 0) || 
        (columnNumber == 8 && rowNumber == 0) || (columnNumber == 2 && rowNumber == 3) || 
        (columnNumber == 5 && rowNumber == 3) || (columnNumber == 8 && rowNumber == 3) || 
        (columnNumber == 2 && rowNumber == 6) || (columnNumber == 5 && rowNumber == 6) || 
        (columnNumber == 8 && rowNumber == 6)) {
      if(board[columnNumber][rowNumber+1] == number || board[columnNumber-1][rowNumber+1] == number || 
          board[columnNumber-2][rowNumber+1] == number || board[columnNumber][rowNumber+2] == number || 
          board[columnNumber-1][rowNumber+2] == number || board[columnNumber-2][rowNumber+2] == number) {
        return false;
      }
    }
    //middle left
    else if((columnNumber == 0 && rowNumber == 1) || (columnNumber == 3 && rowNumber == 1) || 
        (columnNumber == 6 && rowNumber == 1) || (columnNumber == 0 && rowNumber == 4) || 
        (columnNumber == 3 && rowNumber == 4) || (columnNumber == 6 && rowNumber == 4) || 
        (columnNumber == 0 && rowNumber == 7) || (columnNumber == 3 && rowNumber == 7) || 
        (columnNumber == 6 && rowNumber == 7)) {
      if(board[columnNumber][rowNumber+1] == number || board[columnNumber+1][rowNumber+1] == number 
          || board[columnNumber+2][rowNumber+1] == number || board[columnNumber][rowNumber-1] == 
          number || board[columnNumber+1][rowNumber-1] == number || 
          board[columnNumber+2][rowNumber-1] == number) {
        return false;
      }
    }
    //middle middle
    else if((columnNumber == 1 && rowNumber == 1) || (columnNumber == 4 && rowNumber == 1) || 
        (columnNumber == 7 && rowNumber == 1) || (columnNumber == 1 && rowNumber == 4) || 
        (columnNumber == 4 && rowNumber == 4) || (columnNumber == 7 && rowNumber == 4) || 
        (columnNumber == 1 && rowNumber == 7) || (columnNumber == 4 && rowNumber == 7) || 
        (columnNumber == 7 && rowNumber == 7)) {
      if(board[columnNumber-1][rowNumber-1] == number || board[columnNumber][rowNumber+1] == number 
          || board[columnNumber+1][rowNumber+1] == number || board[columnNumber-1][rowNumber+1] == 
          number || board[columnNumber][rowNumber-1] == number || board[columnNumber+1][rowNumber-1] 
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
      if( board[columnNumber][rowNumber-1] == number || board[columnNumber-1][rowNumber-1] == number || 
          board[columnNumber-2][rowNumber-1] == number || board[columnNumber][rowNumber+1] == number || 
          board[columnNumber-1][rowNumber+1] == number || board[columnNumber-2][rowNumber+1] == number) {
        return false;
      }
    }
    //bottom left
    else if((columnNumber == 0 && rowNumber == 2) || (columnNumber == 3 && rowNumber == 2) || 
        (columnNumber == 6 && rowNumber == 2) || (columnNumber == 0 && rowNumber == 5) || 
        (columnNumber == 3 && rowNumber == 5) || (columnNumber == 6 && rowNumber == 5) || 
        (columnNumber == 0 && rowNumber == 8) || (columnNumber == 3 && rowNumber == 8) || 
        (columnNumber == 6 && rowNumber == 8)) {
      if(board[columnNumber][rowNumber-1] == number || board[columnNumber+1][rowNumber-1] == number 
          || board[columnNumber+2][rowNumber-1] == number || board[columnNumber][rowNumber-2] == 
          number || board[columnNumber+1][rowNumber-2] == number || 
          board[columnNumber+2][rowNumber-2] == number) {
        return false;
      }
    }
    //bottom middle
    else if((columnNumber == 1 && rowNumber == 2) || (columnNumber == 4 && rowNumber == 2) || 
        (columnNumber == 7 && rowNumber == 2) || (columnNumber == 1 && rowNumber == 5) || 
        (columnNumber == 4 && rowNumber == 5) || (columnNumber == 7 && rowNumber == 5) || 
        (columnNumber == 1 && rowNumber == 8) || (columnNumber == 4 && rowNumber == 8) || 
        (columnNumber == 7 && rowNumber == 8)) {
      if(board[columnNumber][rowNumber-1] == number || board[columnNumber+1][rowNumber-1] == number 
          || board[columnNumber-1][rowNumber-1] == number || board[columnNumber][rowNumber-2] == 
          number || board[columnNumber+1][rowNumber-2] == number || 
          board[columnNumber-1][rowNumber-2] == number) {
        return false;
      }
    }
    //bottom right
    else if((columnNumber == 2 && rowNumber == 2) || (columnNumber == 5 && rowNumber == 2) || 
        (columnNumber == 8 && rowNumber == 2) || (columnNumber == 2 && rowNumber == 5) || 
        (columnNumber == 5 && rowNumber == 5) || (columnNumber == 8 && rowNumber == 5) || 
        (columnNumber == 2 && rowNumber == 8) || (columnNumber == 5 && rowNumber == 8) || 
        (columnNumber == 8 && rowNumber == 8)) {
      if(board[columnNumber][rowNumber-1] == number || board[columnNumber-1][rowNumber-1] == number || 
          board[columnNumber-2][rowNumber-1] == number || board[columnNumber][rowNumber-2] == number || 
          board[columnNumber-1][rowNumber-2] == number || board[columnNumber-2][rowNumber-2] == number) {
        return false;
      }
    }
    
    return true;
  }
  
  void printSolution(int board[][]) 
  { 
      for (int rowNumber = 0; rowNumber < N; rowNumber++) 
      { 
          for (int columnNumber = 0; columnNumber < N; columnNumber++) { 
              System.out.print(" " + board[columnNumber][rowNumber] + " "); 
          }
          System.out.println(); 
      } 
  } 
}
