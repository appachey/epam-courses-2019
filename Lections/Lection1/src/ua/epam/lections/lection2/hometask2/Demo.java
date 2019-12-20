package ua.epam.lections.lection2.hometask2;

public class Demo {
    public static void main(String[] args) {
        Matrix matr1 = new Matrix(3, 3);
        Matrix matr2 = new Matrix(3, 3);
        matr1.printMatr();
        System.out.println();
        matr2.printMatr();
        System.out.println();
        Matrix addRes = Matrix.addMatrix(matr1, matr2);
        addRes.printMatr();
    }
}
