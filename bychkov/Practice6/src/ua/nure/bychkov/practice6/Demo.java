package ua.nure.bychkov.practice6;

import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws IOException {
        System.out.println("=========================== PART1");
        Part1.main(new String[]{});
        System.out.println("=========================== PART2");
        Part2.main(new String[]{});
        System.out.println("=========================== PART3");
        Part3.main(new String[]{});
        System.out.println("=========================== PART4");
        Part4.main(new String[]{});
        System.out.println("=========================== PART5");
        Part5.main(new String[]{});
        System.out.println("=========================== PART6");
        Part6.main(new String[] {"--input", "part6.txt", "--task", "frequency"});
        Part6.main(new String[] {"--input", "part6.txt", "--task", "length"});
        Part6.main(new String[] {"--input", "part6.txt", "--task", "duplicates"});
    }
}
