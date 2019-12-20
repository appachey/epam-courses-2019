package ua.epam.lections.lection2.hometask2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Matrix {
    private double [][] matrix;
    private final int ROWS;
    private final int COLS;

    public int getRows() {
        return ROWS;
    }

    public int getCols() {
        return COLS;
    }

    public double[][] getMatrix() {
        return this.matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix(int rows, int cols) {
        this.ROWS = rows;
        this.COLS = cols;
        this.matrix = new double[this.ROWS][this.COLS];
        this.initMatr();
    }

    private void initMatr() {
        Random rand = new Random();
        for (int i = 0; i < this.ROWS; i++) {
            for (int j = 0; j < this.COLS; j++) {
                this.matrix[i][j] = valGenerator();
            }
        }
    }

    public static Matrix addMatrix(Matrix mat1, Matrix mat2) {
        double[][] matrix1 = mat1.getMatrix();
        double[][] matrix2 = mat2.getMatrix();
        double[][] result = new double[mat1.getRows()][mat1.getCols()];
        Matrix resultMatr = new Matrix(mat1.getRows(), mat1.getCols());
        for (int i = 0; i < mat1.getRows(); i++) {
            for (int j = 0; j < mat1.getCols(); j++){
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        resultMatr.setMatrix(result);
        return resultMatr;
    }

    public void printMatr() {
        for (int i = 0; i < this.ROWS; i++) {
            for (int j = 0; j < this.COLS; j++){
                System.out.printf("%7.2f", this.matrix[i][j]);
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

    private static boolean isEqual (Matrix mat1, Matrix mat2) {
        if (mat1.getRows() == mat2.getRows() && mat1.getCols() == mat2.getCols()) {
            return true;
        }
        return false;
    }
}
