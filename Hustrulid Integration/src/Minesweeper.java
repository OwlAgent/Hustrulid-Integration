//Stefan Anders Hustrulid
public class Minesweeper {
  public void playMinesweeper() {
    Setup setup = new Setup();
    MineField mineField = new MineField();
    PlayerInterface player = new PlayerInterface();
    
    //determine if user customizes settings or not
    setup.setDifficultyMode();
    boolean difficultyMode = setup.getDifficultyMode();
    
    //establishes number of columns
    setup.setNumberOfColumns/*method call*/(difficultyMode/*arguments*/);
    int numberOfColumns = setup.getNumberOfColumns();
    
    //establishes number of Rows
    setup.setNumberOfRows(difficultyMode);
    int numberOfRows = setup.getNumberOfRows();
    
    //establishes number of Mines
    setup.setNumberOfMines(difficultyMode);
    int numberOfMines = setup.getNumberOfMines();
    
    System.out.println("the area of the minefield is: " + numberOfRows * numberOfColumns + "\n"
        + "the probability of hitting a mine is: " + 100*numberOfMines/(numberOfRows * 
            numberOfColumns) +"% \nthe remainder of the area divided by the number of mines is: "
                + (numberOfRows*numberOfColumns)%numberOfMines);
    
    //determines locations of mines
    mineField.setMinePositions(numberOfMines, numberOfColumns, numberOfRows);
    int[][] mine = mineField.getMinePositions();
    
    //creates and fills in the grid
    mineField.setGrid(mine, numberOfMines, numberOfColumns, numberOfRows);
    int[][] grid = mineField.getGrid();
    
    mineField.testGrid(grid, numberOfColumns, numberOfRows);
    
    //everything is hidden, player sees a blank minefield
    player.setInitialUserGrid(numberOfColumns, numberOfRows);
    
    boolean finished = false;
    boolean allMinesFlagged;
    boolean allSquaresShown;
    int mineNumber;
    int minesChecked;
    int squaresChecked;
    boolean lose = false;
    do { //play minesweeper
      
      player.setUserInputs(numberOfColumns, numberOfRows, grid);
      String[][] userInput = player.getUserInputs();
      
      player.displayUserGrid(numberOfColumns, numberOfRows, grid, userInput);
      
      minesChecked = 0;
      allMinesFlagged = false;
      squaresChecked = 0;
      allSquaresShown = false;
      lose = false;
      
      mineNumber = 1;
      while(mineNumber <= numberOfMines) {
        int columnNumber = mine[mineNumber][1];
        int rowNumber = mine[mineNumber][2];
        if(userInput[columnNumber][rowNumber].equals("Flagged")){
          minesChecked++;
        }
        //ends game if a mine is hit
        else if(userInput[columnNumber][rowNumber].equals("Shown")) {
          finished = true;
          //and you also lose
          lose = true;
        }
        mineNumber++;
        if(minesChecked == numberOfMines) {
          allMinesFlagged = true;
        }
      }
      
      int rowNumber = 1;
      while(rowNumber <= numberOfRows) {
        int columnNumber = 1;
        while(columnNumber <= numberOfColumns) {
          if(userInput[columnNumber][rowNumber].equals("Shown") && grid[columnNumber][rowNumber] 
              != 9) {
            squaresChecked++;
          }
          if(squaresChecked == ((numberOfColumns*numberOfRows)-numberOfMines)) {
            allSquaresShown = true;
          }
          columnNumber++;
        }
        rowNumber++;
      }
      if(allMinesFlagged == true && allSquaresShown == true) {
        finished = true;
      }
    }
    while(finished == false);
    
    if(finished == true && lose == false) {
      System.out.println("Congratulations! you beat me ;)");
    }
    else {
      System.out.println("Boom!!! try again");
    }
    // variables are locations in memory where data is stored
    // scope is whether the variables are limited within the method or within the class 
  }
}
