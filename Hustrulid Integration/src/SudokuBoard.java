//Stefan Anders Hustrulid
import java.util.Arrays;
import java.util.Random;

public class SudokuBoard {
  
  private static Random random = new Random();
  
  int[][] group = new int[9][9];
  int[][] column = new int[9][9];
  int[][] row = new int[9][9];
  boolean groupRequirementMet;
  boolean columnRequirementMet;
  boolean rowRequirementMet;
  
  public void setSudokuBoard() {
    do {
      //generates groups, fills in all squares, randomly generates points again if group requirements 
      //are not met
      generateGroups();
      int i;
      int j;
      
      System.out.println("groupbased");
      for(i = 0; i < 9; i++) {
        for(j = 0; j < 9; j++) {
          System.out.print(row[i][j] + " ");
        }
        System.out.print("\n");
      }
      System.out.print("\n");
    
      //randomly generates points again if column requirements are not met 
      generateColumns();
      
      System.out.println("columnbased");
      for(i = 0; i < 9; i++) {
        for(j = 0; j < 9; j++) {
          System.out.print(row[i][j] + " ");
        }
        System.out.print("\n");
      }
      System.out.print("\n");
    
      //randomly generates points again if row requirements are not met
      generateRows();
      
      System.out.println("rowbased");
      for(i = 0; i < 9; i++) {
        for(j = 0; j < 9; j++) {
          System.out.print(row[i][j] + " ");
        }
        System.out.print("\n");
      }
      System.out.print("\n");
      
      //same thing as generateGroups but in opposite order
      /*
      generateReverseGroups();
      
      System.out.println("reversegroupbased");
      for(i = 0; i < 9; i++) {
        for(j = 0; j < 9; j++) {
          System.out.print(row[i][j] + " ");
        }
        System.out.print("\n");
      }
      
      System.out.print("\n");
      */
      /*
     // generateColumns();
      System.out.println("columnbased2");
      for(i = 0; i < 9; i++) {
        for(j = 0; j < 9; j++) {
          System.out.print(row[i][j] + " ");
        }
        System.out.print("\n");
      }
      System.out.print("\n");
     
      
    //  generateRows();
      System.out.println("rowbased2");
      for(i = 0; i < 9; i++) {
        for(j = 0; j < 9; j++) {
          System.out.print(row[i][j] + " ");
        }
        System.out.print("\n");
      }
      System.out.print("\n");
      */
    }
    while(groupRequirementMet == false && columnRequirementMet == false && rowRequirementMet == 
        false);
    int i;
    int j;
    for(i = 0; i < 9; i++) {
      for(j = 0; j < 9; j++) {
        System.out.print(row[i][j]);
      }
      System.out.print("\n");
    }
  }
  
  public int generateRandomNumber(int itemNumber, int point) {
    int[][] item = new int[9][9];
    item[itemNumber][point] = random.nextInt(9)+1;
    return item[itemNumber][point];
  }
  
  public int compareRandomValues(int itemNumber, int point, int[][] source){
    int[][] item = new int[9][9];
    item = source;
    if(point > 0) {
      int pointComparedTo = 0;
      while(pointComparedTo < point) {
        if(item[itemNumber][point] == item[itemNumber][pointComparedTo]) {
          item[itemNumber][point] = generateRandomNumber(itemNumber, point);
          compareRandomValues(itemNumber, point, item);
        }
        else if (item[itemNumber][point] != item[itemNumber][pointComparedTo]) {
          pointComparedTo++;
        }
      }
    }
    return item[itemNumber][point];
  }
  
  public void generateGroups() {
    groupRequirementMet = false;
    int groupNumber = 0;
    int[][] group2 = new int[9][9];
    while(groupNumber < 9) {
      int groupPoint = 0;
      while(groupPoint < 9) {
        //generates point if nothing is there
        if(group[groupNumber][groupPoint] == 0) {
          group[groupNumber][groupPoint] = generateRandomNumber(groupNumber, groupPoint);
        }
        //compares to previous points and possible regenerates point
        group2[groupNumber][groupPoint] = compareRandomValues(groupNumber, groupPoint, group);
        groupPoint++;
      }
      groupNumber++;
    }
  //checks if there is any change
    if(Arrays.equals(group2, group)) {
      groupRequirementMet = true;
    }
    group = group2;
    //equate columns to groups here
    groupsToColumns();
    //equate rows to groups here, only needed if I change the order in the setSudokuBoard method
    groupsToRows();//using it to test print
  }
  
  public void groupsToColumns() {
    int columnNumber = 0;
    int groupNumberStartingPoint = 0;
    int columnSet = 3;
    for(groupNumberStartingPoint = 0; groupNumberStartingPoint < 3; groupNumberStartingPoint++) {
      int groupPointStartingPoint = 0;
      while(columnNumber < columnSet) {
        int groupNumber = groupNumberStartingPoint;
        int columnPoint = 0;
        int set = 3;
        while(groupNumber < 9) {
          int groupPoint = groupPointStartingPoint;
          while(groupPoint < 9 && columnPoint < set) {
            column[columnNumber][columnPoint] = group[groupNumber][groupPoint];
            groupPoint+=3;
            columnPoint++;
          }
          groupNumber+=3;
          set+=3;
        }
        groupPointStartingPoint++;
        columnNumber++;
      }
      columnSet+=3;
    }
  }
  
  public void groupsToRows() {
 //   System.out.println("group based");
    int rowNumber = 0;
    int groupNumberStartingPoint = 0;
    int rowSet = 3;
    int groupNumberEndingPoint = 3;
    for(groupNumberStartingPoint = 0; groupNumberStartingPoint < 7; groupNumberStartingPoint+=3) {
      int groupPointStartingPoint = 0;
      int groupPointEndingPoint = 3;
      while(rowNumber < rowSet) {
        int groupNumber = groupNumberStartingPoint;
        int rowPoint = 0;
        int set = 3;
        while(groupNumber < groupNumberEndingPoint) {
          int groupPoint = groupPointStartingPoint;
          while(groupPoint < groupPointEndingPoint && rowPoint < set) {
            row[rowNumber][rowPoint] = group[groupNumber][groupPoint];
    //        System.out.print(row[rowNumber][rowPoint] + " ");
            groupPoint++;
            rowPoint++;
          }
          groupNumber++;
          set+=3;
        }
   //     System.out.print("\n");
        groupPointEndingPoint+=3;
        groupPointStartingPoint+=3;
        rowNumber++;
      }
      groupNumberEndingPoint+=3;
      rowSet+=3;
    }
  //  System.out.println("\n");
  }
  
  public void generateColumns() {
    columnRequirementMet = false;
    int[][] column2 = new int[9][9];
    int columnNumber = 0;
    while(columnNumber < 9) {
      int columnPoint = 0;
      while(columnPoint < 9) {
        //compares to previous points and possible regenerates point
        column2[columnNumber][columnPoint] = compareRandomValues(columnNumber, columnPoint, column);
        columnPoint++;
      }
      columnNumber++;
    }
  //checks if there is any change
    if(Arrays.equals(column2, column)) {
      columnRequirementMet = true;
    }
    column = column2;
    //equate groups to columns here, only needed if I change the order in the setSudokuBoard method
  //  columnsToGroups();
    //equate rows to columns here
    columnsToRows();
  }
  
  public void columnsToRows() {
 //   System.out.println("column based");
    int rowNumber = 0;
    for(; rowNumber < 9; rowNumber++) {
      int columnNumber = 0;
      for(; columnNumber < 9; columnNumber++) {
        row[rowNumber][columnNumber]=column[columnNumber][rowNumber];
   //     System.out.print(row[rowNumber][columnNumber] + " ");
      }
  //    System.out.print("\n");
    }
 //   System.out.print("\n");
  }
  
  public void columnsToGroups() {
    int columnNumber = 0;
    int groupNumberStartingPoint = 0;
    int columnSet = 3;
    for(groupNumberStartingPoint = 0; groupNumberStartingPoint < 3; groupNumberStartingPoint++) {
      int groupPointStartingPoint = 0;
      while(columnNumber < columnSet) {
        int groupNumber = groupNumberStartingPoint;
        int columnPoint = 0;
        int set = 3;
        while(groupNumber < 9) {
          int groupPoint = groupPointStartingPoint;
          while(groupPoint < 9 && columnPoint < set) {
            group[groupNumber][groupPoint] = column[columnNumber][columnPoint];
            groupPoint+=3;
            columnPoint++;
          }
          groupNumber+=3;
          set+=3;
        }
        groupPointStartingPoint++;
        columnNumber++;
      }
      columnSet+=3;
    }
  }
  
  public void generateRows() {
 //   System.out.println("row based");
    rowRequirementMet = false;
    int[][] row2 = new int[9][9];
    int rowNumber = 0;
    while(rowNumber < 9) {
      int rowPoint = 0;
      while(rowPoint < 9) {
        //compares to previous points and possible regenerates point
        row2[rowNumber][rowPoint] = compareRandomValues(rowNumber, rowPoint, row);
  //      System.out.print(row2[rowNumber][rowPoint] + " ");
        rowPoint++;
      }
   //   System.out.print("\n");
      rowNumber++;
    }
  //checks if there is any change
    if(Arrays.equals(row2, row)) {
      rowRequirementMet = true;
    }
    row = row2;
    //equate groups to rows here
    rowsToGroups();
    //equate columns to rows here, only needed if I change the order in the setSudokuBoard method
  //  rowsToColumns();
//    System.out.println("\n");
  }
  
  public void rowsToColumns() {
    int columnNumber = 0;
    int rowNumber = 0;
    for(; rowNumber < 9; rowNumber++) {
      for(; columnNumber < 9; columnNumber++) {
        column[columnNumber][rowNumber]=row[rowNumber][columnNumber];
      }
    }
  }
  
  public void rowsToGroups() {
    int rowNumber = 0;
    int groupNumberStartingPoint = 0;
    int rowSet = 3;
    int groupNumberEndingPoint = 3;
    for(groupNumberStartingPoint = 0; groupNumberStartingPoint < 7; groupNumberStartingPoint+=3) {
      int groupPointStartingPoint = 0;
      int groupPointEndingPoint = 3;
      while(rowNumber < rowSet) {
        int groupNumber = groupNumberStartingPoint;
        int rowPoint = 0;
        int set = 3;
        while(groupNumber < groupNumberEndingPoint) {
          int groupPoint = groupPointStartingPoint;
          while(groupPoint < groupPointEndingPoint && rowPoint < set) {
            group[groupNumber][groupPoint] = row[rowNumber][rowPoint];
            groupPoint++;
            rowPoint++;
          }
          groupNumber++;
          set+=3;
        }
        groupPointEndingPoint+=3;
        groupPointStartingPoint+=3;
        rowNumber++;
      }
      groupNumberEndingPoint+=3;
      rowSet+=3;
    }
  }
  
  public void generateReverseGroups() {
    groupRequirementMet = false;
    int groupNumber = 0;
    int[][] group2 = new int[9][9];
    while(groupNumber < 9) {
      int groupPoint = 8;
      while(groupPoint >= 0) {
        //compares to previous points and possible regenerates point
        group2[groupNumber][groupPoint] = compareRandomValues(groupNumber, groupPoint, group);
        groupPoint--;
      }
      groupNumber++;
    }
  //checks if there is any change
    if(Arrays.equals(group2, group)) {
      groupRequirementMet = true;
    }
    group = group2;
    //equate columns to groups here
    groupsToColumns();
    //equate rows to groups here, only needed if I change the order in the setSudokuBoard method
    groupsToRows();//using it to test print
  }
  
  public void generateReverseColumns() {
    columnRequirementMet = false;
    int[][] column2 = new int[9][9];
    int columnNumber = 8;
    while(columnNumber >= 0) {
      int columnPoint = 8;
      while(columnPoint >= 0) {
        //compares to previous points and possible regenerates point
        column2[columnNumber][columnPoint] = compareRandomValues(columnNumber, columnPoint, column);
        columnPoint--;
      }
      columnNumber--;
    }
  //checks if there is any change
    if(Arrays.equals(column2, column)) {
      columnRequirementMet = true;
    }
    column = column2;
    //equate groups to columns here, only needed if I change the order in the setSudokuBoard method
  //  columnsToGroups();
    //equate rows to columns here
    columnsToRows();
  }
  
  public void generateReverseRows() {
    //   System.out.println("row based");
       rowRequirementMet = false;
       int[][] row2 = new int[9][9];
       int rowNumber = 8;
       while(rowNumber >= 0) {
         int rowPoint = 8;
         while(rowPoint >= 0) {
           //compares to previous points and possible regenerates point
           row2[rowNumber][rowPoint] = compareRandomValues(rowNumber, rowPoint, row);
     //      System.out.print(row2[rowNumber][rowPoint] + " ");
           rowPoint--;
         }
      //   System.out.print("\n");
         rowNumber--;
       }
     //checks if there is any change
       if(Arrays.equals(row2, row)) {
         rowRequirementMet = true;
       }
       row = row2;
       //equate groups to rows here
       rowsToGroups();
       //equate columns to rows here, only needed if I change the order in the setSudokuBoard method
     //  rowsToColumns();
//       System.out.println("\n");
     }
}
