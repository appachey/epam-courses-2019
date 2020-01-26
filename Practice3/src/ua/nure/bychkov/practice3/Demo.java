package ua.nure.bychkov.practice3;

import java.security.NoSuchAlgorithmException;

public class Demo {
    public static void main(String[] args) {
        Part1.main(new String[]{});
        Part2.main(new String[]{});
        Part3.main(new String[]{});
        try {
            Part4.main(new String[]{});
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        Part5.main(new String[]{});
        Part6.main(new String[]{});
    }
}
