package ua.nure.bychkov.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    private static final String F_NAME = "part2.txt";
    public static void main(String[] args) {
        String input = Util.readFile("part2_1.txt");
        convert(input);
    }

    public static void convert(String input) {
        String regex = "(?m)(^|\\W+?\\b+?)(\\S+?)(\\b+?)";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(input);
        while (match.find()) {
            System.out.println(match.group(2));
        }
    }
}
