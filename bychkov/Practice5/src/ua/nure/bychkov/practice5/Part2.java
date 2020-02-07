package ua.nure.bychkov.practice5;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Part2 {
    private static final InputStream STD_IN = System.in;

    public static void main(String[] args) throws IOException {
        InputStream inStream = new MyInputStream();
        System.setIn(new ByteArrayInputStream(System.lineSeparator().getBytes()));
    }
}
