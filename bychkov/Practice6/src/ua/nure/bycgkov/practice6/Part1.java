package ua.nure.bycgkov.practice6;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Part1 {
    private static final InputStream STD_IN = System.in;

    private static final String ENCODING = "Cp1251";

    public static void main(String[] args) throws IOException {
        String input = "asd asdf asd asdf asdf^" +
                "43 asdsf 43 43^" +
                "434 stop";
        // set the mock input
        System.setIn(new ByteArrayInputStream(
                input.replace("^", System.lineSeparator()).getBytes(ENCODING)));
        WordContainer.main(args);
        // restore the standard input
        System.setIn(STD_IN);
    }
}
