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

    public void addMatrix(Matrix matrix) {
        if (isEqual(matrix)){
            double[][] matrVolumes = matrix.getMatrix();
            for (int i = 0; i < this.ROWS; i++) {
                for (int j = 0; j < this.COLS; j++){
                    this.matrix[i][j] += matrVolumes[i][j];
                }
            }
        } else {
            System.out.println("Different dimmentions of matrix");
        }

    }

    public void mulMatrByValue(int value){
        for (int i = 0; i < this.ROWS; i++) {
            for (int j = 0; j < this.COLS; j++) {
                this.matrix[i][j] *= value;
            }
        }
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

    private boolean isEqual (Matrix matrix) {
        if (this.ROWS == matrix.getRows() && this.COLS == matrix.getCols()) {
            return true;
        }
        return false;
    }
}
