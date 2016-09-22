import java.util.ArrayList;
 
/*
 * File: sudoku.java
 * The program creates and solves a sudoku puzzle
 * Author: Ram Longman
 * Date: 4/16/2016
 */
public class sudoku {
 
    /*
     * Main method. Initializes a sudoku puzzle, calls the solve function
     * to solve it, prints the solution to the screen, measures the time
     * it takes to solve it, and prints the time to the screen.
     */
    public static void main(String args[]) {
        long startTime = System.nanoTime();//start time
        int[][] grid = { //Sudoku puzzle to solve
                { 5, 3, 0, 0, 7, 0, 0, 0, 0},
                { 6, 0, 0, 1, 9, 5, 0, 0, 0},
                { 0, 9, 8, 0, 0, 0, 0, 6, 0},
                { 8, 0, 0, 0, 6, 0, 0, 0, 3},
                { 4, 0, 0, 8, 0, 3, 0, 0, 1},
                { 7, 0, 0, 0, 2, 0, 0, 0, 6},
                { 0, 6, 0, 0, 0, 0, 2, 8, 0},
                { 0, 0, 0, 4, 1, 9, 0, 0, 5},
                { 0, 0, 0, 0, 8, 0, 0, 7, 9},
        };
 
        int a = solve(grid); //solve;
 
        if(0==a) { //if there is a solution
            //print solution
            for(int i=0; i<9; i++) {
                for(int j=0; j<9; j++) {
                    System.out.print(grid[i][j]+" ");
                }
                System.out.print("\n");
            }
        }
        long endTime = System.nanoTime(); //end time
        //print time it took to run to the screen
        System.out.println("Run time: "+((endTime-startTime)*Math.pow(10, -9))+" seconds");
    }
 
    /*
     * solves a Sudoku puzzle
     * @param grid - the Sudoku puzzle to solve
     * @return 0 if successful, -1 if there is no solution.
     */
    public static int solve(int[][] grid) {
 
        ArrayList<Coordinate> takenCoordinates = new ArrayList<Coordinate>();
 
        //create a list of the coordinates which already have a pre-defined value
        for(int r=0; r<9; r++) {
            for(int c=0; c<9; c++) {
                if(0!=grid[r][c]) {
                    takenCoordinates.add(new Coordinate(r,c));
                }
            }
        }
 
        int row=0;
        while(row<9) { //rows
            int column=0;
            while(column<9) { //columns
                boolean taken=false;
                for(int i=0; i<takenCoordinates.size(); i++) {
                    if(takenCoordinates.contains(new Coordinate(row,column))) {
                        //if coordinate has a pre-defined value, skip the coordinate
                        taken=true;
                        break;
                    }
                }
                if(taken==false) { //if coordinate doesn't have a pre-defined value
                    int val=1;
                    while(val<10) { //values to try for the coordinate
                        boolean isValid=true;
                        for(int temp=0; temp<9; temp++) { //iterate to see if coordinate is valid
                            //    same row                  same column                same 3x3
                            if(grid[row][temp]==val || grid[temp][column]==val ||
                                    grid[(temp/3)+((row/3)*3)][(temp%3)+((column/3)*3)]==val) {
                                isValid = false; //coordinate is NOT valid
                                break;
                            }
                        }
                        if(isValid){ //if valid
                            grid[row][column] = val; //put value
                            break; //move to next coordinate
                        }
                        while(9==val || (takenCoordinates.contains(new Coordinate(row,column)))){
                            //Backtrack while not valid and last value or if has a pre-defined value
                            if(0==column) {
                                if(0==row) { //no solution in first coordinate -> no solution
                                    System.out.println("No Solution");
                                    return -1;
                                }
                                else { //backtrack to previous row last column
                                    row--;
                                    column = 8;
                                }
                            }
                            else { //backtrack one column back
                                column--;
                            }
                            val = grid[row][column];//the value in the new coordinate after backtracking
                            if (!(takenCoordinates.contains(new Coordinate(row,column)))) {
                                grid[row][column] = 0; //reset to 0 "future" squares when backtracking
                            }
                        }
                        val++;
                    }
                }
                column++;
            }
            row++;
        }
        return 0;
    }
}