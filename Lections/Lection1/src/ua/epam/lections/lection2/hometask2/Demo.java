package ua.epam.lections.lection2.hometask2;

public class Demo {
    public static void main(String[] args) {
        Matrix matr1 = new Matrix(3, 3);
        matr1.initRandom();
        matr1.printMatr();
        System.out.println();
        Matrix matrTransp = matr1.transpondMatr();
        matrTransp.printMatr();
    }
}
