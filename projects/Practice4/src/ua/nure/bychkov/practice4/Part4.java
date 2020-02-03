package ua.nure.bychkov.practice4;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 implements Iterable<String>{
    private String text;
    private int textLength;
    private Matcher match;

    public Part4(String fileName, String encoding) {
        text = ReadUtil.readFile(fileName, encoding).trim();
        textLength = text.length();
        match = Pattern.compile("(?Us)\\S.+?[\\.\\?!]+").matcher(text);
    }

    public static void main(String[] args) {
        Part4 part4 = new Part4("part4.txt", "cp1251");
        Iterator it = part4.iterator();
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
    }

    @Override
    public Iterator<String> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<String> {
        private int cursor;
        private int lastReturned;

        IteratorImpl() {
            cursor = 0;
            lastReturned = -1;
        }
        @Override
        public boolean hasNext() {
            return cursor != Part4.this.textLength;
        }

        @Override
        public String next() {
            int start = cursor;
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Part4.this.match.find(start);
            cursor = Part4.this.match.end();
            return Part4.this.match.group();
        }

        @Override
        public void remove() {
                throw new UnsupportedOperationException();
        }
    }
}

