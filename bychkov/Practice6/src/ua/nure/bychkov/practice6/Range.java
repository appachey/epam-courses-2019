package ua.nure.bychkov.practice6;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer>{
    private int[] range;

    public Range(int n, int m) {
        if (m <= n) {
            throw new IllegalArgumentException();
        }
        range = new int[m - n + 1];
        for (int i = 0; i < range.length; i++) {
            range[i] = n++;
        }
    }

    public Range(int n, int m, boolean reverse) {
        this(n, m);
        if (reverse) {
            range = new int[m - n + 1];
            for (int i = 0; i < range.length; i++) {
                range[i] = m--;
            }
        }
    }

    public void print() {
        for (int i : range) {
            System.out.printf("%d ", i);
        }
    }

    public Iterator<Integer> iterator() {
        return new RangeIterator();
    }

    private class RangeIterator implements Iterator<Integer> {
        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor != Range.this.range.length;
        }

        @Override
        public Integer next() {
            int ind = cursor;
            if (ind >= Range.this.range.length) {
                throw new NoSuchElementException();
            }
            cursor++;
            int[] range = Range.this.range;
            return range[ind];
        }
    }
}
