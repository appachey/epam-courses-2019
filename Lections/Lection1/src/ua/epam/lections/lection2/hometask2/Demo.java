package ua.epam.lections.lection2.hometask2;

public class Demo {
    public static void main(String[] args) {
        Matrix matr1 = new Matrix(3, 3);
        Matrix matr2 = new Matrix(2, 3);
        matr1.printMatr();
        System.out.println();
        matr2.printMatr();
        System.out.println();
        matr1.addMatrix(matr2);
        matr1.printMatr();
        System.out.println();
        matr2.printMatr();
    }
}
