import java.util.Arrays;
import java.util.Random;

public class SudokuMakerReferenceCode {
    
    public static void main(String[] args) {
      //possible numbers
      int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
      int[][] sodoku = new SudokuMakerReferenceCode().generateSodoku(9, numbers);
      
    //prints board
      for (int i = 0; i < sodoku.length; i++) {
        System.out.println(Arrays.toString(sodoku[i]));
      }
    }
    
    public int[][] generateSodoku(int size, int[] numbers) {
      
      int[][] sodoku = new int[size][size];
      generateSodokuHelper(sodoku, 0, numbers);
      
      return sodoku;
    }
    //grid and sudoku share the same location in memory
    public boolean generateSodokuHelper(int[][] grid, int index, int[] numbers) {
      if (index > grid.length * grid.length - 1) return true;
      
      Random ran = new Random();
      //goes through each point
      int x = index % grid.length;
      int y = index / grid.length;
      
      //copy of possible numbers array... unique numbers array for each point
      int[] numbersCopy = Arrays.copyOf(numbers, numbers.length);
      
      for (int i = 0; i < numbers.length; i++) {
        //checks random point in numbers array
        int numIndex = ran.nextInt(numbers.length);
        while (numbers[numIndex] == -1) {
          numIndex = ran.nextInt(numbers.length);
        }
        //when a number is tried it's spot in numbersCopy array is marked with -1
        //and is placed in grid
        grid[y][x] = numbers[numIndex];
        numbersCopy[numIndex] = -1;
        //if number is not valid then try another number... when 9 numbers have been tried the for 
        //loop will end
        if (!numberIsValid(grid, index)) continue;
        boolean res = generateSodokuHelper(grid, index + 1, numbers);
        //if next spot returns true then this spot return true... but if next spot returns false 
        //then the next number is tried
        if (res) return true;
      }
      //if all numbers are tested then return false for this spot
      grid[y][x] = 0;
      
      return false;
    }
    
    public boolean numberIsValid(int[][] grid, int index) {  
      int x = index % grid.length;
      int y = index / grid.length;
       
      int num = grid[y][x];
       
      for (int i = 0; i < grid.length; i++) {
        if (i != y) {
          if (grid[i][x] == num) return false; 
        }
        if (i != x) {
          if (grid[y][i] == num) return false; 
        }
      }
       
      int[] quadrant = new int[2];
      quadrant[0] = y / 3 * 3;
      quadrant[1] = x / 3 * 3;
      
      for (int i = 0; i < grid.length; i++) {
        int quadY = quadrant[0] + i / 3;
        int quadX = quadrant[1] + i % 3;
        
        if ((quadY != y && quadX != x) && grid[quadY][quadX] == num) return false; 
      }
      
      return true;
    }
}
