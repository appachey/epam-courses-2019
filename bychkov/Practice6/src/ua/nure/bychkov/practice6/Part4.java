package ua.nure.bychkov.practice6;

import java.util.Iterator;

public class Part4 {
    public static void main(String[] args) {
        Range r = new Range (3, 10);
        for (Integer x : r) {
            System.out.printf("%d ", x);
        }
    }
}
