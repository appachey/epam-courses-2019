package ua.nure.bychkov.practice6.part4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer>{
    private int[] fromTo;

    public Range(int n, int m) {
        if (m <= n) {
            throw new IllegalArgumentException();
        }
        fromTo = new int[m - n + 1];
        for (int i = 0; i < fromTo.length; i++) {
            fromTo[i] = n++;
        }
    }

    public Range(int n, int m, boolean reverse) {
        this(n, m);
        if (reverse) {
            fromTo = new int[m - n + 1];
            for (int i = 0; i < fromTo.length; i++) {
                fromTo[i] = m--;
            }
        }
    }

    public void print() {
        StringBuilder output = new StringBuilder();
        for (int i : fromTo) {
            output.append(i).append(" ");
        }
        System.out.println(output.toString().trim());
    }

    public Iterator<Integer> iterator() {
        return new RangeIterator();
    }

    private class RangeIterator implements Iterator<Integer> {
        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor != Range.this.fromTo.length;
        }

        @Override
        public Integer next() {
            int ind = cursor;
            if (ind >= Range.this.fromTo.length) {
                throw new NoSuchElementException();
            }
            cursor++;
            int[] range = Range.this.fromTo;
            return range[ind];
        }
    }
}
