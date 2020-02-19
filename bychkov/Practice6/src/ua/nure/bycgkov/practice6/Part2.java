package ua.nure.bycgkov.practice6;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Part2 {
    private static final int N = 10000;

    public static void main(String[] args) {
        List<Integer> arrList = new ArrayList<>();
        List<Integer> linkList = new LinkedList<>();
        fillList(arrList);
        fillList(linkList);
        System.out.println("ArrayList#Index: " + removeByIndex(arrList, 4) + " ms");
        System.out.println("LinkedList#Index: " + removeByIndex(linkList, 4) + " ms");
    }

    public static void fillList(List<Integer> list) {
        for (int i = 0; i < N; i++) {
            list.add(i);
        }
    }

    public static long removeByIndex(List<Integer> list, int k) {
        long start = System.currentTimeMillis();
        while (list.size() != 1) {
            for (int i = 0; i < list.size(); i++) {
                if (i % k == 0) {
                    list.remove(i);
                }
            }
        }

        return System.currentTimeMillis() - start;
    }
}
