//Stefan Anders Hustrulid
import java.util.Random;

public class MineField {
  
  private int[][] point;
  private static Random random = new Random();
  private static int[][] mine;
  
  public int[][] generateRandomCoordinate(int mineNumber, int numberOfColumns, int numberOfRows) {
    int X1Y2 = 1;
    while(X1Y2 <= 2) {
      if(X1Y2 == 1) {
        mine[mineNumber][X1Y2] = random.nextInt(numberOfColumns) + 1;
      }
      if(X1Y2 == 2) {
        mine[mineNumber][X1Y2] = random.nextInt(numberOfRows) + 1;
      }
      X1Y2++;
    }
    return mine;
  }
  
  public void setMinePositions(int numberOfMines, int numberOfColumns, int numberOfRows){
    mine = new int[numberOfMines+1][3];
    int mineNumber = 1; 
    while(mineNumber <= numberOfMines) {
      mine = generateRandomCoordinate(mineNumber, numberOfColumns, numberOfRows);
      if(mineNumber > 1) {
        int mineComparedTo = 1;
        while(mineComparedTo < mineNumber ) {
          if(mine[mineNumber][1] == mine[mineComparedTo][1] && mine[mineNumber][2] == 
              mine[mineComparedTo][2]) {
            mine = generateRandomCoordinate(mineNumber, numberOfColumns, numberOfRows);
          }
          else if(mine[mineNumber][1] != mine[mineComparedTo][1] || mine[mineNumber][2] != 
              mine[mineComparedTo][2]) {
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
  
  public void setGrid(int[][] mine, int numberOfMines, int numberOfColumns, int numberOfRows) {
    int mineNumber = 1;
    point = new int[numberOfColumns+1][numberOfRows+1];
    int rowNumber = 1;
    while(rowNumber <= numberOfRows) {
      int columnNumber = 1;
      while(columnNumber <= numberOfColumns) {
        if(mineNumber <= numberOfMines) {
          while(mineNumber <= numberOfMines) {
            if(columnNumber == mine[mineNumber][1] && rowNumber == mine[mineNumber][2]) {
              point[columnNumber][rowNumber] = 9;
            }
           mineNumber++;
          }
          mineNumber = 1;
        } 
        else {
          point[columnNumber][rowNumber] = 0;
        }
        columnNumber++;
      }
      rowNumber++;
    }
    point = getPlotNumbers(point, numberOfMines, numberOfColumns, numberOfRows);
  }
  public int[][] getGrid(){
    return point;
  }
  
  public int[][] getPlotNumbers(int[][] point, int numberOfMines, int numberOfColumns, 
      int numberOfRows){
    int mineNumber = 1;
    while(mineNumber <= numberOfMines) {
      int columnNumber = mine[mineNumber][1];
      int rowNumber = mine[mineNumber][2];
      
      if(columnNumber == 1 && rowNumber == 1) { //top left corner
        if(point[columnNumber+1][rowNumber] != 9) {
          point[columnNumber+1][rowNumber]++;
        }
        if(point[columnNumber+1][rowNumber+1] != 9) {
          point[columnNumber+1][rowNumber+1]++;
        }
        if(point[columnNumber][rowNumber+1] != 9) {
          point[columnNumber][rowNumber+1]++;
        }
      }
      else if(columnNumber == 1 && (rowNumber != 1 && rowNumber != numberOfRows)) { //left edge
        if(point[columnNumber][rowNumber-1] != 9) {
          point[columnNumber][rowNumber-1]++;
        }
        if(point[columnNumber+1][rowNumber-1] != 9) {
          point[columnNumber+1][rowNumber-1]++;
        }
        if(point[columnNumber+1][rowNumber] != 9) {
          point[columnNumber+1][rowNumber]++;
        }
        if(point[columnNumber+1][rowNumber+1] != 9) {
          point[columnNumber+1][rowNumber+1]++;
        }
        if(point[columnNumber][rowNumber+1] != 9) {
          point[columnNumber][rowNumber+1]++;
        }
      }
      else if(columnNumber == 1 && rowNumber == numberOfRows) { //bottom left corner
        if(point[columnNumber][rowNumber-1] != 9) {
          point[columnNumber][rowNumber-1]++;
        }
        if(point[columnNumber+1][rowNumber-1] != 9) {
          point[columnNumber+1][rowNumber-1]++;
        }
        if(point[columnNumber+1][rowNumber] != 9) {
          point[columnNumber+1][rowNumber]++;
        }
      }
      else if((columnNumber != 1 && columnNumber != numberOfColumns) && rowNumber == 1) { // top edge
        if(point[columnNumber+1][rowNumber] != 9) {
          point[columnNumber+1][rowNumber]++;
        }
        if(point[columnNumber+1][rowNumber+1] != 9) {
          point[columnNumber+1][rowNumber+1]++;
        }
        if(point[columnNumber][rowNumber+1] != 9) {
          point[columnNumber][rowNumber+1]++;
        }
        if(point[columnNumber-1][rowNumber+1] != 9) {
          point[columnNumber-1][rowNumber+1]++;
        }
        if(point[columnNumber-1][rowNumber] != 9) {
          point[columnNumber-1][rowNumber]++;
        }
      }
      else if(columnNumber == numberOfColumns && rowNumber == 1) { //top right corner
        if(point[columnNumber-1][rowNumber] != 9) {
          point[columnNumber-1][rowNumber]++;
        }
        if(point[columnNumber-1][rowNumber+1] != 9) {
          point[columnNumber-1][rowNumber+1]++;
        }
        if(point[columnNumber][rowNumber+1] != 9) {
          point[columnNumber][rowNumber+1]++;
        }
      }
      else if(columnNumber == numberOfColumns && (rowNumber != 1 && rowNumber != numberOfRows)) { 
        //right edge
        if(point[columnNumber][rowNumber-1] != 9) {
          point[columnNumber][rowNumber-1]++;
        }
        if(point[columnNumber-1][rowNumber-1] != 9) {
          point[columnNumber-1][rowNumber-1]++;
        }
        if(point[columnNumber-1][rowNumber] != 9) {
          point[columnNumber-1][rowNumber]++;
        }
        if(point[columnNumber-1][rowNumber+1] != 9) {
          point[columnNumber-1][rowNumber+1]++;
        }
        if(point[columnNumber][rowNumber+1] != 9) {
          point[columnNumber][rowNumber+1]++;
        }
      }
      else if(columnNumber == numberOfColumns && rowNumber == numberOfRows) { //bottom right corner
        if(point[columnNumber-1][rowNumber] != 9) {
          point[columnNumber-1][rowNumber]++;
        }
        if(point[columnNumber-1][rowNumber-1] != 9) {
          point[columnNumber-1][rowNumber-1]++;
        }
        if(point[columnNumber][rowNumber-1] != 9) {
          point[columnNumber][rowNumber-1]++;
        }
      }
      else if((columnNumber != 1 && columnNumber != numberOfColumns) && rowNumber == numberOfRows) {
        //bottom edge
        if(point[columnNumber-1][rowNumber] != 9) {
          point[columnNumber-1][rowNumber]++;
        }
        if(point[columnNumber-1][rowNumber-1] != 9) {
          point[columnNumber-1][rowNumber-1]++;
        }
        if(point[columnNumber][rowNumber-1] != 9) {
          point[columnNumber][rowNumber-1]++;
        }
        if(point[columnNumber+1][rowNumber-1] != 9) {
          point[columnNumber+1][rowNumber-1]++;
        }
        if(point[columnNumber+1][rowNumber] != 9) {
          point[columnNumber+1][rowNumber]++;
        }
      }
      else if((columnNumber != 1 && columnNumber != numberOfColumns) && (rowNumber != 1 && rowNumber 
          != numberOfRows)) { //not on the edge or corner
        if(point[columnNumber-1][rowNumber-1] != 9) {
          point[columnNumber-1][rowNumber-1]++;
        }
        if(point[columnNumber][rowNumber-1] != 9) {
          point[columnNumber][rowNumber-1]++;
        }
        if(point[columnNumber+1][rowNumber-1] != 9) {
          point[columnNumber+1][rowNumber-1]++;
        }
        if(point[columnNumber+1][rowNumber] != 9) {
          point[columnNumber+1][rowNumber]++;
        }
        if(point[columnNumber+1][rowNumber+1] != 9) {
          point[columnNumber+1][rowNumber+1]++;
        }
        if(point[columnNumber][rowNumber+1] != 9) {
          point[columnNumber][rowNumber+1]++;
        }
        if(point[columnNumber-1][rowNumber+1] != 9) {
          point[columnNumber-1][rowNumber+1]++;
        }
        if(point[columnNumber-1][rowNumber] != 9) {
          point[columnNumber-1][rowNumber]++;
        }
      }
      mineNumber++;
    }
    return point;
  }
  public void testGrid(int[][] point, int numberOfColumns, int numberOfRows) {
    int j=1;
    while(j<=numberOfRows) {
      int i = 1;
      while(i<=numberOfColumns) {
        System.out.print(point[i][j] == 9 ? "* " : point[i][j] + " ");
        i++;
      }
      System.out.print("\n");
      j++;
    }
    
  }
}
