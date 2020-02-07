package ua.nure.bychkov.practice4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadUtil {
    private static final String ENCODING = "cp1251";
    private static final String F_NAME = "part1.txt";
    private static final String L_SEP = System.lineSeparator();

    public static void main(String[] args) {
        String input = ReadUtil.readFile(F_NAME, ENCODING);
        System.out.println(input);
    }
    public static String readFile(String fName, String encoding) {
        StringBuilder output = new StringBuilder();
        try (BufferedReader bReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(fName), encoding))) {
            String str;
            while ((str = bReader.readLine()) != null) {
                output.append(str).append(L_SEP);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        output.delete(output.lastIndexOf(L_SEP), output.lastIndexOf(L_SEP) + L_SEP.length());
        return output.toString();
    }
}
