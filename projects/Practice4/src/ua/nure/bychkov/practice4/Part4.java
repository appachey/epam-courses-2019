package ua.nure.bychkov.practice4;

import java.util.Iterator;

public class Part4 implements Iterable<String>{
    public static void main(String[] args) {

    }

    @Override
    public Iterator<String> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<String> {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public String next() {
            return null;
        }

        @Override
        public void remove() {

        }
    }
}

