package ua.nure.bychkov.practice6.part2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Part2 {
    private static final int N = 7;

    public static void main(String[] args) {
        List<Integer> arrListRm = new ArrayList<>();
        List<Integer> linkListRm = new LinkedList<>();
        List<Integer> arrListIt = new ArrayList<>();
        List<Integer> linkListIt = new LinkedList<>();
        fillList(arrListRm);
        fillList(linkListRm);
        fillList(arrListIt);
        fillList(linkListIt);
        System.out.println("ArrayList#Index: " + removeByIndex(arrListRm, 3) + " ms");
        System.out.println("LinkedList#Index: " + removeByIndex(linkListRm, 3) + " ms");
        System.out.println("ArrayList#Iterator: " + removeByIterator(arrListIt, 3) + " ms");
        System.out.println("LinkedList#Iterator: " + removeByIterator(linkListIt, 3) + " ms");
    }

    public static void fillList(List<Integer> list) {
        for (int i = 0; i < N; i++) {
            list.add(i);
        }
    }

    public static long removeByIndex(List<Integer> list, int k) {
        long start = System.currentTimeMillis();
        int deleteInd = 0;
        while (list.size() != 1) {
            for (int i = 0; i < list.size() && list.size() != 1; i++) {
                deleteInd++;
                if (deleteInd == k) {
                    list.remove(i);
                    deleteInd = 0;
                    i--;
                }
        }
        }
        return System.currentTimeMillis() - start;
    }

    public static long removeByIterator(List<Integer> list, int k) {
        long start = System.currentTimeMillis();
        int deleteInd = 0;
        while (list.size() != 1) {
            Iterator<Integer> it = list.iterator();
            for (int i = 0; i < list.size() && list.size() != 1; i++) {
                deleteInd++;
                it.next();
                if ( deleteInd == k) {
                    it.remove();
                    deleteInd = 0;
                    i--;
                }
            }
        }
        return System.currentTimeMillis() - start;
    }
}
