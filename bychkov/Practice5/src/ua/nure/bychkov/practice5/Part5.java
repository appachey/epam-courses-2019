package ua.nure.bychkov.practice5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Part5 {
    private static final String OUTPUT = "part5.txt";
    public static void main(String[] args) throws IOException {
        Files.deleteIfExists(Paths.get(OUTPUT));
    }
}
