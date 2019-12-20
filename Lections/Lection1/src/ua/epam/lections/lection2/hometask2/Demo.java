package ua.epam.lections.lection2.hometask2;

public class Demo {
    public static void main(String[] args) {
        Matrix matr1 = new Matrix(3, 2);
        Matrix matr2 = new Matrix(3, 3);
        System.out.println(Matrix.isEqual(matr1, matr2));
    }
}
