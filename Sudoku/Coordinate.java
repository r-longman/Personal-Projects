/*
 * File: Coordinate.java
 * A basic coordinate class to be used in the Sudoku program
 * Author: Ram Longman
 * Date: 4/16/2016
 */
public class Coordinate {
 
    //every Coordinate has a row and a column
    private int row;
    private int col;
 
    /*
     * constructor, creates a coordinate with the given row and column
     * @param row - the row of the coordinate
     * @param col - the column of the coordinate
     */
    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }
 
    /*
     * gets the row of the coordinate
     * @return - the row of the coordinate
     */
    public int getRow() {
        return this.row;
    }
 
    /*
     * gets the column of the coordinate
     * @return - the column of the coordinate
     */
    public int getColumn() {
        return this.col;
    }
 
    /*
     * override equals method,
     * Coordinates are equal if their rows and columns are equal
     * @param o- object to compare to
     * @return true when equal, false when not equal
     */
    @Override public boolean equals(Object o) {
        Coordinate c = (Coordinate) o;
        if(this.row==c.getRow() && this.col==c.getColumn()) {
            return true;
        }
        return false;
    }
 
    /*
     * override toString method,
     * print objects in this format: (row,col)
     * @return string to be printed
     */
    @Override public String toString() {
        return "("+this.row+","+this.col+")";
    }
}