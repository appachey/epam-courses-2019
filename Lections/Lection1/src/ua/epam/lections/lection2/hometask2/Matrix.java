package ua.epam.lections.lection2.hometask2;

public class Matrix {
    private double [][] matrix;
    private int rows;
    private int cols;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new double[this.rows][this.cols];
    }
}
