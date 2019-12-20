package ua.epam.lections.lection2.hometask2;

public class Matrix {
    private double [][] matrix;
    private int rows;

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    private int cols;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new double[this.rows][this.cols];
    }

    public static boolean isEqual (Matrix mat1, Matrix mat2) {
        if (mat1.getRows() == mat2.getRows() && mat1.getCols() == mat2.getCols()) {
            return true;
        }
        return false;
    }
}
