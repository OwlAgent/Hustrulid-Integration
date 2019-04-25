import java.util.ArrayList;
import java.util.Arrays;

// Stefan Hustrulid
public class HumanBasedSolver {
  int size = 9;
  int groupSize = (int) Math.sqrt(size);
  int[][] problemBoard;
  ArrayList<ArrayList<ArrayList<Integer>>> possibilitiesBoard =
      new ArrayList<ArrayList<ArrayList<Integer>>>(size);

  HumanBasedSolver(int[][] problemBoard) {
    // set problemBoard
    this.problemBoard = new int[size][size];
    for (int rowNumber = 0; rowNumber < this.problemBoard.length; rowNumber++) {
      for (int columnNumber =
          0; columnNumber < this.problemBoard[rowNumber].length; columnNumber++) {
        this.problemBoard[rowNumber][columnNumber] = problemBoard[rowNumber][columnNumber];
      }
    }
    // create initial possibilitiesBoard
    for (int rowNumber = 0; rowNumber < size; rowNumber++) {
      ArrayList<ArrayList<Integer>> rows = new ArrayList<ArrayList<Integer>>(size);
      for (int columnNumber = 0; columnNumber < size; columnNumber++) {
        ArrayList<Integer> columns = new ArrayList<Integer>(size);
        for (int possibility = 0; possibility < size;) {
          // If a spot is not removed, it initially only has 1 possibility.
          columns.add(++possibility);
          if (problemBoard[rowNumber][columnNumber] != 0
              && possibility != problemBoard[rowNumber][columnNumber]) {
            columns.remove(Integer.valueOf(possibility));
          }
        }
        rows.add(columns);
      }
      possibilitiesBoard.add(rows);
    }
  }

  public void solveSudoku() {
    boolean test1;
    do {
      test1 = tryPattern1();
      if (!test1) {
        boolean test2 = tryPattern2();
        if (!test2) {
          boolean test3 = tryPattern3();
          if (!test3) {
            //boolean test4 = tryPattern4();
            //if (!test4) {
           // } else {
           //   solveSudoku();
           // }
          } else {
            solveSudoku();
          }
        } else {
          solveSudoku();
        }
      }
    } while (test1);
    printSolution(problemBoard);
  }

  public boolean checkIfCorrect(int[][] answerBoard) {
    if (Arrays.deepEquals(problemBoard, answerBoard)) {
      return true;
    } else {
      return false;
    }
  }

  public boolean tryPattern1() {
    // make a copy
    ArrayList<ArrayList<ArrayList<Integer>>> possibilitiesBoardCopy = makeCopy(possibilitiesBoard);
    // Update possibilitesBoard
    for (int rowNumber = 0; rowNumber < size; rowNumber++) {
      for (int columnNumber = 0; columnNumber < size; columnNumber++) {
        int numberOfPossibilities = possibilitiesBoard.get(rowNumber).get(columnNumber).size();
        if (numberOfPossibilities == 1) {
          int possibility = possibilitiesBoard.get(rowNumber).get(columnNumber).get(0);
          // Update problem board.
          problemBoard[rowNumber][columnNumber] = possibility;
          // Remove possibility from the rest of the row.
          for (int comparedColumn = 0; comparedColumn < size; comparedColumn++) {
            if (comparedColumn == columnNumber) {
              continue;
            }
            if (possibilitiesBoard.get(rowNumber).get(comparedColumn).contains(possibility)) {
              possibilitiesBoard.get(rowNumber).get(comparedColumn)
                  .remove(Integer.valueOf(possibility));
            }
          }
          // Remove possibility from the rest of the column.
          for (int comparedRow = 0; comparedRow < size; comparedRow++) {
            if (comparedRow == rowNumber) {
              continue;
            }
            if (possibilitiesBoard.get(comparedRow).get(columnNumber).contains(possibility)) {
              possibilitiesBoard.get(comparedRow).get(columnNumber)
                  .remove(Integer.valueOf(possibility));
            }
          }
          // Remove possibility from the rest of the group.
          int startingColumn = columnNumber / groupSize * groupSize;
          int startingRow = rowNumber / groupSize * groupSize;
          int endingColumn = startingColumn + groupSize;
          int endingRow = startingRow + groupSize;
          for (; startingRow < endingRow; startingRow++) {
            for (; startingColumn < endingColumn; startingColumn++) {
              if (startingColumn == columnNumber && startingRow == rowNumber) {
                continue;
              }
              if (possibilitiesBoard.get(startingRow).get(startingColumn).contains(possibility)) {
                possibilitiesBoard.get(startingRow).get(startingColumn)
                    .remove(Integer.valueOf(possibility));
              }
            }
            startingColumn = columnNumber / groupSize * groupSize;
          }
        }
      }
    }
    // check for change.
    if (!possibilitiesBoard.equals(possibilitiesBoardCopy)) {
      return true;
    } else {
      return false;
    }
  }

  public boolean tryPattern2() {
    // make a copy
    ArrayList<ArrayList<ArrayList<Integer>>> possibilitiesBoardCopy = makeCopy(possibilitiesBoard);
    for (int possibility = 0; possibility < size;) {
      possibility++;
      // Check if only one possible spot in the row.
      for (int rowNumber = 0; rowNumber < size; rowNumber++) {
        int numberOfPossibleSpots = 0;
        for (int columnNumber = 0; columnNumber < size; columnNumber++) {
          if (possibilitiesBoard.get(rowNumber).get(columnNumber).contains(possibility)) {
            numberOfPossibleSpots++;
          }
        }
        if (numberOfPossibleSpots == 1) {
          for (int columnNumber = 0; columnNumber < size; columnNumber++) {
            if (possibilitiesBoard.get(rowNumber).get(columnNumber).contains(possibility)) {
              possibilitiesBoard.get(rowNumber).get(columnNumber).clear();
              possibilitiesBoard.get(rowNumber).get(columnNumber).add(possibility);
              break;
            }
          }
        }
      }
      // Check if only one possible spot in the column.
      for (int columnNumber = 0; columnNumber < size; columnNumber++) {
        int numberOfPossibleSpots = 0;
        for (int rowNumber = 0; rowNumber < size; rowNumber++) {
          if (possibilitiesBoard.get(rowNumber).get(columnNumber).contains(possibility)) {
            numberOfPossibleSpots++;
          }
        }
        if (numberOfPossibleSpots == 1) {
          for (int rowNumber = 0; rowNumber < size; rowNumber++) {
            if (possibilitiesBoard.get(rowNumber).get(columnNumber).contains(possibility)) {
              possibilitiesBoard.get(rowNumber).get(columnNumber).clear();
              possibilitiesBoard.get(rowNumber).get(columnNumber).add(possibility);
              break;
            }
          }
        }
      }
      // Check if only one possible spot in the group.
      for (int startingRow = 0; startingRow < size; startingRow += groupSize) {
        for (int startingColumn = 0; startingColumn < size; startingColumn += groupSize) {
          int numberOfPossibleSpots = 0;
          int columnNumber = startingColumn / groupSize * groupSize;
          int rowNumber = startingRow / groupSize * groupSize;
          int endingColumn = columnNumber + groupSize;
          int endingRow = rowNumber + groupSize;
          for (; rowNumber < endingRow; rowNumber++) {
            for (; columnNumber < endingColumn; columnNumber++) {
              if (possibilitiesBoard.get(rowNumber).get(columnNumber).contains(possibility)) {
                numberOfPossibleSpots++;
              }
            }
            columnNumber = startingColumn / groupSize * groupSize;
          }
          rowNumber = startingRow / groupSize * groupSize;
          if (numberOfPossibleSpots == 1) {
            for (; rowNumber < endingRow; rowNumber++) {
              boolean nextGroup = false;
              for (; columnNumber < endingColumn; columnNumber++) {
                if (possibilitiesBoard.get(rowNumber).get(columnNumber).contains(possibility)) {
                  possibilitiesBoard.get(rowNumber).get(columnNumber).clear();
                  possibilitiesBoard.get(rowNumber).get(columnNumber).add(possibility);
                  nextGroup = true;
                  break;
                }
              }
              if (nextGroup) {
                break;
              }
              columnNumber = startingColumn / groupSize * groupSize;
            }
          }
        }
      }
    }
    // check for change.
    if (!possibilitiesBoard.equals(possibilitiesBoardCopy)) {
      return true;
    } else {
      return false;
    }
  }

  public boolean tryPattern3() {
    // make a copy
    ArrayList<ArrayList<ArrayList<Integer>>> possibilitiesBoardCopy = makeCopy(possibilitiesBoard);
    for (int possibility = 0; possibility < size;) {
      possibility++;
      // Check if only two or three possible spots in the row.
      for (int rowNumber = 0; rowNumber < size; rowNumber++) {
        int numberOfPossibleSpots = 0;
        // Count how many in a row.
        for (int columnNumber = 0; columnNumber < size; columnNumber++) {
          if (possibilitiesBoard.get(rowNumber).get(columnNumber).contains(possibility)) {
            numberOfPossibleSpots++;
          }
        }
        // If only 2 or 3 spots, check what groups they are in
        if (numberOfPossibleSpots == 2 || numberOfPossibleSpots == 3) {
          int numberInColumnGroup0 = 0;
          int numberInColumnGroup1 = 0;
          int numberInColumnGroup2 = 0;
          int numberOfSpotsLocated = 0;
          for (int columnNumber = 0; columnNumber < size; columnNumber++) {
            if (possibilitiesBoard.get(rowNumber).get(columnNumber).contains(possibility)) {
              numberOfSpotsLocated++;
              int columnGroupNumber = columnNumber / groupSize;
              if (columnGroupNumber == 0) {
                numberInColumnGroup0++;
              } else if (columnGroupNumber == 1) {
                numberInColumnGroup1++;
              } else if (columnGroupNumber == 2) {
                numberInColumnGroup2++;
              }
              if (numberOfSpotsLocated == numberOfPossibleSpots) {
                break;
              }
            }
          }
          // If all spots are in a single group, remove the possibilitiesfrom the rest of the group.
          if (numberInColumnGroup0 == numberOfPossibleSpots) {
            int startingRow = rowNumber / groupSize * groupSize;
            int columnNumber = 0;
            int endingColumn = columnNumber + groupSize;
            int endingRow = startingRow + groupSize;
            for (; startingRow < endingRow; startingRow++) {
              if (startingRow == rowNumber) {
                continue;
              }
              for (; columnNumber < endingColumn; columnNumber++) {
                if (possibilitiesBoard.get(startingRow).get(columnNumber).contains(possibility)) {
                  possibilitiesBoard.get(startingRow).get(columnNumber)
                      .remove(Integer.valueOf(possibility));
                }
              }
              columnNumber = 0;
            }
          } else if (numberInColumnGroup1 == numberOfPossibleSpots) {
            int startingRow = rowNumber / groupSize * groupSize;
            int columnNumber = 3;
            int endingColumn = columnNumber + groupSize;
            int endingRow = startingRow + groupSize;
            for (; startingRow < endingRow; startingRow++) {
              if (startingRow == rowNumber) {
                continue;
              }
              for (; columnNumber < endingColumn; columnNumber++) {
                if (possibilitiesBoard.get(startingRow).get(columnNumber).contains(possibility)) {
                  possibilitiesBoard.get(startingRow).get(columnNumber)
                      .remove(Integer.valueOf(possibility));
                }
              }
              columnNumber = 3;
            }
          } else if (numberInColumnGroup2 == numberOfPossibleSpots) {
            int startingRow = rowNumber / groupSize * groupSize;
            int columnNumber = 6;
            int endingColumn = columnNumber + groupSize;
            int endingRow = startingRow + groupSize;
            for (; startingRow < endingRow; startingRow++) {
              if (startingRow == rowNumber) {
                continue;
              }
              for (; columnNumber < endingColumn; columnNumber++) {
                if (possibilitiesBoard.get(startingRow).get(columnNumber).contains(possibility)) {
                  possibilitiesBoard.get(startingRow).get(columnNumber)
                      .remove(Integer.valueOf(possibility));
                }
              }
              columnNumber = 6;
            }
          }
        }
      }
      // Check if only two or three possible spots in the column.
      for (int columnNumber = 0; columnNumber < size; columnNumber++) {
        int numberOfPossibleSpots = 0;
        // Count how many in a column.
        for (int rowNumber = 0; rowNumber < size; rowNumber++) {
          if (possibilitiesBoard.get(rowNumber).get(columnNumber).contains(possibility)) {
            numberOfPossibleSpots++;
          }
        }
        // If only 2 or 3 spots, check what groups they are in
        if (numberOfPossibleSpots == 2 || numberOfPossibleSpots == 3) {
          int numberInRowGroup0 = 0;
          int numberInRowGroup1 = 0;
          int numberInRowGroup2 = 0;
          int numberOfSpotsLocated = 0;
          for (int rowNumber = 0; rowNumber < size; rowNumber++) {
            if (possibilitiesBoard.get(rowNumber).get(columnNumber).contains(possibility)) {
              numberOfSpotsLocated++;
              int rowGroupNumber = rowNumber / groupSize;
              if (rowGroupNumber == 0) {
                numberInRowGroup0++;
              } else if (rowGroupNumber == 1) {
                numberInRowGroup1++;
              } else if (rowGroupNumber == 2) {
                numberInRowGroup2++;
              }
              if (numberOfSpotsLocated == numberOfPossibleSpots) {
                break;
              }
            }
          }
          // If all spots are in a single group, remove the possibilities from the rest of the
          // group.
          if (numberInRowGroup0 == numberOfPossibleSpots) {
            int startingColumn = columnNumber / groupSize * groupSize;
            int rowNumber = 0;
            int endingRow = rowNumber + groupSize;
            int endingColumn = startingColumn + groupSize;
            for (; rowNumber < endingRow; rowNumber++) {
              for (; startingColumn < endingColumn; startingColumn++) {
                if (startingColumn == columnNumber) {
                  continue;
                }
                if (possibilitiesBoard.get(rowNumber).get(startingColumn).contains(possibility)) {
                  possibilitiesBoard.get(rowNumber).get(startingColumn)
                      .remove(Integer.valueOf(possibility));
                }
              }
              startingColumn = columnNumber / groupSize * groupSize;;
            }
          }
          if (numberInRowGroup1 == numberOfPossibleSpots) {
            int startingColumn = columnNumber / groupSize * groupSize;
            int rowNumber = 3;
            int endingRow = rowNumber + groupSize;
            int endingColumn = startingColumn + groupSize;
            for (; rowNumber < endingRow; rowNumber++) {
              for (; startingColumn < endingColumn; startingColumn++) {
                if (startingColumn == columnNumber) {
                  continue;
                }
                if (possibilitiesBoard.get(rowNumber).get(startingColumn).contains(possibility)) {
                  possibilitiesBoard.get(rowNumber).get(startingColumn)
                      .remove(Integer.valueOf(possibility));
                }
              }
              startingColumn = columnNumber / groupSize * groupSize;;
            }
          }
          if (numberInRowGroup2 == numberOfPossibleSpots) {
            int startingColumn = columnNumber / groupSize * groupSize;
            int rowNumber = 6;
            int endingRow = rowNumber + groupSize;
            int endingColumn = startingColumn + groupSize;
            for (; rowNumber < endingRow; rowNumber++) {
              for (; startingColumn < endingColumn; startingColumn++) {
                if (startingColumn == columnNumber) {
                  continue;
                }
                if (possibilitiesBoard.get(rowNumber).get(startingColumn).contains(possibility)) {
                  possibilitiesBoard.get(rowNumber).get(startingColumn)
                      .remove(Integer.valueOf(possibility));
                }
              }
              startingColumn = columnNumber / groupSize * groupSize;;
            }
          }
        }
      }
      // Check if only two or three possibilities in the group
      for (int startingRow = 0; startingRow < size; startingRow += groupSize) {
        for (int startingColumn = 0; startingColumn < size; startingColumn += groupSize) {
          int numberOfPossibleSpots = 0;
          int columnNumber = startingColumn;
          int rowNumber = startingRow;
          int endingColumn = columnNumber + groupSize;
          int endingRow = rowNumber + groupSize;
          for (; rowNumber < endingRow; rowNumber++) {
            for (; columnNumber < endingColumn; columnNumber++) {
              if (possibilitiesBoard.get(rowNumber).get(columnNumber).contains(possibility)) {
                numberOfPossibleSpots++;
              }
            }
            columnNumber = startingColumn;
          }
          rowNumber = startingRow;
          if (numberOfPossibleSpots == 2 || numberOfPossibleSpots == 3) {
            int numberInRow0 = 0;
            int numberInRow1 = 0;
            int numberInRow2 = 0;
            int numberInColumn0 = 0;
            int numberInColumn1 = 0;
            int numberInColumn2 = 0;
            int numberOfSpotsLocated = 0;
            for (; rowNumber < endingRow; rowNumber++) {
              boolean nextGroup = false;
              for (; columnNumber < endingColumn; columnNumber++) {
                if (possibilitiesBoard.get(rowNumber).get(columnNumber).contains(possibility)) {
                  numberOfSpotsLocated++;
                  if (rowNumber % groupSize == 0) {
                    numberInRow0++;
                  } else if (rowNumber % groupSize == 1) {
                    numberInRow1++;
                  } else if (rowNumber % groupSize == 2) {
                    numberInRow2++;
                  }
                  if (columnNumber % groupSize == 0) {
                    numberInColumn0++;
                  } else if (columnNumber % groupSize == 1) {
                    numberInColumn1++;
                  } else if (columnNumber % groupSize == 2) {
                    numberInColumn2++;
                  }
                  if (numberOfSpotsLocated == numberOfPossibleSpots) {
                    nextGroup = true;
                    break;
                  }
                }
              }
              columnNumber = startingColumn;
              if (nextGroup) {
                break;
              }
            }
            if (numberInRow0 == numberOfPossibleSpots) {
              rowNumber = startingRow;
              for (columnNumber = 0; columnNumber < size;) {
                if (columnNumber == startingColumn) {
                  columnNumber += groupSize;
                  continue;
                }
                if (possibilitiesBoard.get(rowNumber).get(columnNumber).contains(possibility)) {
                  possibilitiesBoard.get(rowNumber).get(columnNumber)
                      .remove(Integer.valueOf(possibility));
                }
                columnNumber++;
              }
            } else if (numberInRow1 == numberOfPossibleSpots) {
              rowNumber = startingRow + 1;
              for (columnNumber = 0; columnNumber < size; columnNumber++) {
                if (columnNumber == startingColumn) {
                  columnNumber += 2;
                  continue;
                }
                if (possibilitiesBoard.get(rowNumber).get(columnNumber).contains(possibility)) {
                  possibilitiesBoard.get(rowNumber).get(columnNumber)
                      .remove(Integer.valueOf(possibility));
                }
              }
            } else if (numberInRow2 == numberOfPossibleSpots) {
              rowNumber = startingRow + 2;
              for (columnNumber = 0; columnNumber < size; columnNumber++) {
                if (columnNumber == startingColumn) {
                  columnNumber += 2;
                  continue;
                }
                if (possibilitiesBoard.get(rowNumber).get(columnNumber).contains(possibility)) {
                  possibilitiesBoard.get(rowNumber).get(columnNumber)
                      .remove(Integer.valueOf(possibility));
                }
              }
            } else if (numberInColumn0 == numberOfPossibleSpots) {
              columnNumber = startingColumn;
              for (rowNumber = 0; rowNumber < size; rowNumber++) {
                if (rowNumber == startingRow) {
                  rowNumber += 2;
                  continue;
                }
                if (possibilitiesBoard.get(rowNumber).get(columnNumber).contains(possibility)) {
                  possibilitiesBoard.get(rowNumber).get(columnNumber)
                      .remove(Integer.valueOf(possibility));
                }
              }
            } else if (numberInColumn1 == numberOfPossibleSpots) {
              columnNumber = startingColumn + 1;
              for (rowNumber = 0; rowNumber < size; rowNumber++) {
                if (rowNumber == startingRow) {
                  rowNumber += 2;
                  continue;
                }
                if (possibilitiesBoard.get(rowNumber).get(columnNumber).contains(possibility)) {
                  possibilitiesBoard.get(rowNumber).get(columnNumber)
                      .remove(Integer.valueOf(possibility));
                }
              }
            } else if (numberInColumn2 == numberOfPossibleSpots) {
              columnNumber = startingColumn + 2;
              for (rowNumber = 0; rowNumber < size; rowNumber++) {
                if (rowNumber == startingRow) {
                  rowNumber += 2;
                  continue;
                }
                if (possibilitiesBoard.get(rowNumber).get(columnNumber).contains(possibility)) {
                  possibilitiesBoard.get(rowNumber).get(columnNumber)
                      .remove(Integer.valueOf(possibility));
                }
              }
            }
          }
        }
      }
    }
    // check for change.
    if (!possibilitiesBoard.equals(possibilitiesBoardCopy)) {
      return true;
    } else {
      return false;
    }
  }

  public boolean tryPattern4() {
    // make a copy
    ArrayList<ArrayList<ArrayList<Integer>>> possibilitiesBoardCopy = makeCopy(possibilitiesBoard);
    for (int rowNumber = 0; rowNumber < size; rowNumber++) {
      for (int columnNumber = 0; columnNumber < size; columnNumber++) {
        int setLength = possibilitiesBoard.get(rowNumber).get(columnNumber).size();
        int startingColumn = columnNumber / groupSize * groupSize;
        int startingRow = rowNumber / groupSize * groupSize;
        int endingColumn = startingColumn + groupSize;
        int endingRow = startingRow + groupSize;
        int numberOfSet = 0;
        for (; startingRow < endingRow; startingRow++) {
          for (; startingColumn < endingColumn; startingColumn++) {
            if (possibilitiesBoard.get(rowNumber).get(columnNumber)
                .equals(possibilitiesBoard.get(startingRow).get(startingColumn))) {
              numberOfSet++;
            }
          }
          startingColumn = columnNumber / groupSize * groupSize;
        }
        startingRow = rowNumber / groupSize * groupSize;
        if (setLength == numberOfSet) {
          for (; startingRow < endingRow; startingRow++) {
            for (; startingColumn < endingColumn; startingColumn++) {
              if (!possibilitiesBoard.get(rowNumber).get(columnNumber)
                  .equals(possibilitiesBoard.get(startingRow).get(startingColumn))) {
                for (Integer possibility : possibilitiesBoard.get(rowNumber).get(columnNumber)) {
                  int possibilityInt = possibility;
                  possibilitiesBoard.get(startingRow).get(startingColumn)
                      .remove(Integer.valueOf(possibilityInt));
                }
              }
            }
            startingColumn = columnNumber / groupSize * groupSize;
          }
        }
        numberOfSet = 0;
        for (int comparedColumn = 0; comparedColumn < size; comparedColumn++) {
          if (possibilitiesBoard.get(rowNumber).get(columnNumber)
              .equals(possibilitiesBoard.get(rowNumber).get(comparedColumn))) {
            numberOfSet++;
          }
        }
        if (setLength == numberOfSet) {
          for (int comparedColumn = 0; comparedColumn < size; comparedColumn++) {
            if (!possibilitiesBoard.get(rowNumber).get(columnNumber)
                .equals(possibilitiesBoard.get(rowNumber).get(comparedColumn))) {
              for (Integer possibility : possibilitiesBoard.get(rowNumber).get(columnNumber)) {
                int possibilityInt = possibility;
                possibilitiesBoard.get(rowNumber).get(comparedColumn)
                    .remove(Integer.valueOf(possibilityInt));
              }
            }
          }
        }
        numberOfSet = 0;
        for (int comparedRow = 0; comparedRow < size; comparedRow++) {
          if (possibilitiesBoard.get(rowNumber).get(columnNumber)
              .equals(possibilitiesBoard.get(comparedRow).get(columnNumber))) {
            numberOfSet++;
          }
        }
        if (setLength == numberOfSet) {
          for (int comparedRow = 0; comparedRow < size; comparedRow++) {
            if (!possibilitiesBoard.get(rowNumber).get(columnNumber)
                .equals(possibilitiesBoard.get(comparedRow).get(columnNumber))) {
              for (Integer possibility : possibilitiesBoard.get(rowNumber).get(columnNumber)) {
                int possibilityInt = possibility;
                possibilitiesBoard.get(comparedRow).get(columnNumber)
                    .remove(Integer.valueOf(possibilityInt));
              }
            }
          }
        }
      }
    }
    // check for change.
    if (!possibilitiesBoard.equals(possibilitiesBoardCopy)) {
      return true;
    } else {
      return false;
    }
  }

  public ArrayList<ArrayList<ArrayList<Integer>>> makeCopy(
      ArrayList<ArrayList<ArrayList<Integer>>> possibilitiesBoard) {
    ArrayList<ArrayList<ArrayList<Integer>>> possibilitiesBoardCopy =
        new ArrayList<ArrayList<ArrayList<Integer>>>(size);
    for (int rowNumber = 0; rowNumber < size; rowNumber++) {
      ArrayList<ArrayList<Integer>> rows = new ArrayList<ArrayList<Integer>>(size);
      for (int columnNumber = 0; columnNumber < size; columnNumber++) {
        ArrayList<Integer> columns = new ArrayList<Integer>();
        for (int possibility = 0; possibility < possibilitiesBoard.get(rowNumber).get(columnNumber)
            .size();) {
          columns.add(possibilitiesBoard.get(rowNumber).get(columnNumber).get(possibility++));
        }
        rows.add(columns);
      }
      possibilitiesBoardCopy.add(rows);
    }
    return possibilitiesBoardCopy;
  }

  public void printSolution(int[][] sudokuBoard) {
    System.out.println("_______________________________");
    for (int rowNumber = 0; rowNumber < size; rowNumber++) {
      System.out.print("|");
      for (int columnNumber = 0; columnNumber < size; columnNumber++) {
        if (sudokuBoard[rowNumber][columnNumber] == 0) {
          System.out.print("   ");
        } else {
          System.out.print(" " + sudokuBoard[rowNumber][columnNumber] + " ");
        }
        // System.out.print(" " + sudokuBoard[rowNumber][columnNumber] + " ");
        if (columnNumber == 2 || columnNumber == 5) {
          System.out.print("|");
        }
      }
      System.out.println("|");
      if (rowNumber == 2 || rowNumber == 5 || rowNumber == 8) {
        System.out.println("|_________|_________|_________|");
      }
    }
  }
}
