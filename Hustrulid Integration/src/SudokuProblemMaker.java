import java.util.Random;
//constructer is only kind of method that does not require a return type;
//constructor name is always same as Class name

//Stefan Hustrulid
public class SudokuProblemMaker {
  //step 1: remove 1 random point
    //create a new grid to declare if each square is pernament or not
  
  //step 2: run a solver
  
  //step 3: check if solution is still unique
    //run a solver for every permutation of columns aka 9! times to guarantee the solution is still
    //unique
    //if step 3 returns false then backtrack to step 1 and break from the loop (aka step 5)
  
  //step 4: check that at least 1 empty square has only 1 possibility
    //step 4 is to make the game more enjoyable for the player
    //if step 4 returns false backtrack to step 1 again
  
  //step 5: repeat until solver is unable to find a solution, or solution is not unique
  
  final int N = 9;
  int[][] board;
    //assign all unremoved points as permanent
  boolean[][] pernamentPoints = {
      {true, false, false, true, false, false, false, false, false}, 
      {false, false, false, false, true, true, true, true, false}, 
      {false, false, true, false, false, true, true, true, false}, 
      {true, true, true, true, true, false, false, false, false}, 
      {true, false, true, false, true, false, true, false, true}, 
      {false, false, false, false, true, true, true, true, true}, 
      {false, true, true, true, false, false, true, false, false}, 
      {false, true, true, true, true, false, false, false, false}, 
      {false, false, false, false, false, true, false, false, true}};
  
  boolean createProblem(int[][] board) {
    Random rnd = new Random();   
    
    //Step 1
    int col = rnd.nextInt(N);     
    int row = rnd.nextInt(N);
      //remove random point and assign as nonpermanent
    /*
    pernamentPoints[col][row] = false;
    board[col][row] = 0;
    */
    
    for (row = 0; row < N; row++) 
    { 
        for (col = 0; col < N; col++) { 
            System.out.print(" " + pernamentPoints[col][row] + " "); 
        }
        System.out.println(); 
    } 
    printProblem(board);
    
    //Step 2
      //create solver with adjustable order that it runs through columns
      //use Heap's Algorithm from GeeksforGeeks to run the solver through every permutation of 
      //columns
    int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    heapPermutation(a, N, N, board);
    //Step 3
      //check that all of the solutions are identical
    return true;
  }
  //https://www.geeksforgeeks.org/heaps-algorithm-for-generating-permutations/
  //Generating permutation using Heap Algorithm 
  void heapPermutation(int order[], int size, int n, int[][] board) { 
    // if size becomes 1 then prints the obtained 
    // permutation 
    if (size == 1) { 
      
      //testing heaps algorithm
      for (int i=0; i<n; i++) 
        System.out.print(order[i]);
    System.out.println("\n");
    
      for(int number = 1; number <= 9; number++) {
        if(solveSudokuProblem(board, 0, number, order) == false) {
          solveSudokuProblem(board, 0, number, order);
        }
      }
      printProblem(board);
      System.out.println("\n");
    }
    for (int i=0; i<size; i++) { 
      heapPermutation(order, size-1, n, board); 

      // if size is odd, swap first and last 
      // element 
      if (size % 2 == 1) { 
        int temp = order[0]; 
        order[0] = order[size-1]; 
        order[size-1] = temp; 
      } 

      // If size is even, swap ith and last 
      // element 
      else {
        int temp = order[i]; 
        order[i] = order[size-1]; 
        order[size-1] = temp;        
      }
    }
  }
  
boolean solveSudokuProblem(int[][] board, int columnOrder, int number, int[] order) {
    if(columnOrder >= N) {
      //printProblem(board);
      //System.out.println("\n");
      return true;
    }
  
    for(int rowNumber = 0; rowNumber < N; rowNumber++) {
      if(isSafe(board, order[columnOrder], rowNumber, number)) {
        
        //if square is avaliable place number
        board[order[columnOrder]][rowNumber] = number;
        if(solveSudokuProblem(board, columnOrder+1, number, order) == true) {
          return true;
        }
        board[order[columnOrder]][rowNumber] = 0;
      }
    }
    return false;
  }

boolean isSafe(int[][] board, int columnNumber, int rowNumber, int number) {
  int comparedColumn;
  
//checks if number is already in that square
  if(board[columnNumber][rowNumber] == number) {
    return true;
  }
  
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
  
//checks that square is changeable
  if(pernamentPoints[columnNumber][rowNumber] == true) {
    return false;
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
  
  int[][] getSudokuProblem(){
    return board;
  }
  
  void printProblem(int board[][]) 
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
