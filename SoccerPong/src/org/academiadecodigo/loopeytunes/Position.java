package org.academiadecodigo.loopeytunes;

public class Position {
    private double col;
    private double row;

    // CONSTRUCTOR
    public Position(double col, double row) {
        this.col = col;
        this.row = row;
    }

    // SET ROW
    public void setRow(double row) {
        this.row += row;
    }

    // SET COL
    public void setCol(double col) {
        this.col += col;
    }

    // GET COL
    public double getCol() {
        return col;
    }

    // GET ROW
    public double getRow() {
        return row;
    }
}
