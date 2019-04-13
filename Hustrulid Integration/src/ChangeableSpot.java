//Stefan Anders Hustrulid
public class ChangeableSpot {
  
  private int number;
  private int rowNumber;
  private int columnNumber;
  
  ChangeableSpot(int rowNumber, int columnNumber){
    this.rowNumber = rowNumber;
    this.columnNumber = columnNumber;
  }
  
  ChangeableSpot(int number, int rowNumber, int columnNumber){
    this.number = number;
    this.rowNumber = rowNumber;
    this.columnNumber = columnNumber;
  }

  public void setNumber(int number) {
    this.number = number;
  }
  
  public int getNumber() {
    return number;
  }
  
  public void setRowNumber(int rowNumber) {
    this.rowNumber = rowNumber;
  }
  
  public int getRowNumber() {
    return rowNumber;
  }
  
  public void setColumnNumber(int columnNumber) {
    this.columnNumber = columnNumber;
  }
  
  public int getColumnNumber() {
    return columnNumber;
  }
}
