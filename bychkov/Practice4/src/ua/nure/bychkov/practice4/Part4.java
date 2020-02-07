package ua.nure.bychkov.practice4;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 implements Iterable<String>{
    private static final String F_NAME = "part4.txt";
    private static final String ENCODING = "cp1251";
    private String text;
    private int textLength;
    private Matcher match;

    public Part4(String fileName, String encoding) {
        String temp = ReadUtil.readFile(fileName, encoding).trim();
        text = temp.replaceAll("\\s+", " ");
        textLength = text.length();
        match = Pattern.compile("(?Us)\\p{javaUpperCase}.+?\\.+").matcher(text);
    }

    public static void main(String[] args) {
        Part4 part4 = new Part4(F_NAME, ENCODING);
        Iterator it = part4.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<String> {
        private int cursor;

        IteratorImpl() {
            cursor = 0;
        }
        @Override
        public boolean hasNext() {
            return cursor != Part4.this.textLength;
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int start = cursor;
            Part4.this.match.find(start);
            cursor = Part4.this.match.end();
            String output = Part4.this.match.group();
            output = output.replaceAll("\n|\n\r", "");
            return output;
        }

        @Override
        public void remove() {
                throw new UnsupportedOperationException();
        }
    }
}

