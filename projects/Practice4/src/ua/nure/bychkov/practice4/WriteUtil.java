package ua.nure.bychkov.practice4;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class WriteUtil {
    public static final String F_NAME = "part2.txt";
    public static final String ENCODING = "cp1251";

    public static void main(String[] args) {
        String input = "Лягає день. Він віддає свої надії ночі.\nС барабаном ходит ёжик. Бум-бум-бум.";
        writeFile(F_NAME, input, ENCODING);
    }

    public static void writeFile(String fName, String data, String encoding) {
        try {
            BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fName, false), encoding));
            bWriter.write(data);
            bWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
