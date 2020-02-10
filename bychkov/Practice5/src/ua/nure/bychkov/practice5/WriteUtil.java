package ua.nure.bychkov.practice5;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WriteUtil {
    public static void writeFile(String fName, String data, String encoding) {
        try (BufferedWriter bWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream("part4.txt", false), "cp1251"))) {
            bWriter.write(data);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
