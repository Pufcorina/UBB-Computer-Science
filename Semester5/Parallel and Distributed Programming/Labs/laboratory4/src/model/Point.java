package model;

public class Point {
    private int matrixIndex;
    private int row;
    private int col;

    public Point(int matrixIndex, int row, int col) {
        this.matrixIndex = matrixIndex;
        this.row = row;
        this.col = col;
    }

    public int getMatrixIndex() {
        return matrixIndex;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        StringBuilder ss = new StringBuilder();

        ss.append("(i: ")
                .append(matrixIndex)
                .append(", r: ")
                .append(row)
                .append(", c: ")
                .append(col)
                .append(")");

        return ss.toString();
    }

    @Override
    public boolean equals(Object object) {
        Point other = (Point) object;
        if (this.matrixIndex != (other.matrixIndex))
            return false;

        if (this.col != other.col)
            return false;

        if (this.row != other.row)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}