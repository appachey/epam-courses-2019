package ua.epam.lections.lection2.hometask2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Matrix {
    private double[][] matrix;
    private int rows;
    private int cols;

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public double[][] getMatrix() {
        return this.matrix;
    }

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new double[this.rows][this.cols];
        this.initMatr();
    }

    private void initMatr() {
        Random rand = new Random();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.matrix[i][j] = valGenerator();
            }
        }
    }

    public void addMatrix(Matrix matrix) {
        if (isEqual(matrix)) {
            double[][] matrVolumes = matrix.getMatrix();
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {
                    this.matrix[i][j] += matrVolumes[i][j];
                }
            }
        } else {
            System.out.println("Different dimmentions of matrix");
        }

    }

    public void mulMatrByValue(int value) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.matrix[i][j] *= value;
            }
        }
    }

    public void mulMatrix(Matrix matrix) {
        double[][] result = new double[this.rows][matrix.getCols()];
        if (this.cols == matrix.getRows()) {
            double[][] matrVolumes = matrix.getMatrix();
            int matRows = matrix.getRows();
            int matCols = matrix.getCols();
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < matCols; j++) {
                    for (int k = 0; k < matRows; k++) {
                        result[i][j] += this.matrix[i][k] * matrVolumes[k][j];
                    }
                }
            }
        } else {
            System.out.println("Count of columns of first matrix is not equal count of rows of second matrix");
        }
        this.matrix = result;
        this.rows = this.matrix.length;
        this.cols = this.matrix[0].length;

    }

    public void printMatr() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.printf("%10.2f", this.matrix[i][j]);
            }
            System.out.println();
        }
    }

    private double valGenerator() {
        Random rand = new Random();
        BigDecimal bd = new BigDecimal(Double.toString(rand.nextDouble() * 100));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private boolean isEqual(Matrix matrix) {
        if (this.rows == matrix.getRows() && this.cols == matrix.getCols()) {
            return true;
        }
        return false;
    }
}
