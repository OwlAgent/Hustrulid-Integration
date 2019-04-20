// Stefan Anders Hustrulid
/**
 * Keeps track of which spots are removable/changeable and not.
 * 
 * @author Stefan
 */
public class PointRemover {

  /**
   * Size of the sudoku board.
   */
  int size;
  /**
   * Which spots on the board are changeable or fixed.
   */
  boolean[][] changeableSpotsBoard;

  PointRemover() {
    size = 9;
    changeableSpotsBoard = new boolean[size][size];
    setChangeableSpotsBoard();
  }

  /**
   * Initially sets everything as unchangeable.
   */
  public void setChangeableSpotsBoard() {
    for (int rowNumber = 0; rowNumber < changeableSpotsBoard.length; rowNumber++) {
      for (int columnNumber =
          0; columnNumber < changeableSpotsBoard[rowNumber].length; columnNumber++) {
        changeableSpotsBoard[rowNumber][columnNumber] = false;
      }
    }
  }

  /**
   * Changes a spot to either changeable or unchangeable.
   * 
   * @param rowNumber What row the spot is on.
   * @param columnNumber What column the spot is on.
   * @param result If the spot is changeable or unchangeable
   */
  public void setChangeableSpotsBoard(int rowNumber, int columnNumber, boolean result) {
    changeableSpotsBoard[rowNumber][columnNumber] = result;
  }

  public boolean[][] getChangeableSpotsBoard() {
    return changeableSpotsBoard;
  }
}
