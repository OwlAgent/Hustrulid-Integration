//Stefan Anders Hustrulid
public class PointRemover {

  int size;
  boolean[][] changeableSpotsBoard;
  
  PointRemover(){
    size = 9;
    changeableSpotsBoard = new boolean[size][size];
    setChangeableSpotsBoard();
  }
  
  public void setChangeableSpotsBoard() {
    for(int rowNumber = 0; rowNumber < changeableSpotsBoard.length; rowNumber++) {
      for(int columnNumber = 0; columnNumber < changeableSpotsBoard[rowNumber].length; 
          columnNumber++) {
        changeableSpotsBoard[rowNumber][columnNumber] = false;
      }
    }
  }
  
  public void setChangeableSpotsBoard(int rowNumber, int columnNumber, boolean result) {
    changeableSpotsBoard[rowNumber][columnNumber] = result;
  }
  
  public boolean[][] getChangeableSpotsBoard(){
    return changeableSpotsBoard;
  }
}
