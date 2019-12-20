package ua.epam.lections.lection2.hometask2;

public class Demo {
    public static void main(String[] args) {
        Matrix matr1 = new Matrix(3, 3);
        matr1.printMatr();
        System.out.println();
        matr1.mulMatrByValue(2);
        matr1.printMatr();
    }
}
