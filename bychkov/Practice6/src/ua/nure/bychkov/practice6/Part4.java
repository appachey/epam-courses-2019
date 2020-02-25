package ua.nure.bychkov.practice6;

public class Part4 {
    public static void main(String[] args) {
        Range r1 = new Range (3, 10);
        r1.print();
        Range r2 = new Range (3, 10, true);
        for (Integer x : r2) {
            System.out.printf("%d ", x);
        }
        System.out.println();
    }
}
