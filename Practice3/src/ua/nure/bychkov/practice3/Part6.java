package ua.nure.bychkov.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {
    private static final String F_NAME = "part6.txt";

    public static void main(String[] args) {
        String input = Util.readFile(F_NAME);
        System.out.println(convert(input));
        System.out.println("~~~~~~~~~~~~~~~");
    }

    public static String convert(String input) {
        String regex = "(?Um)\\b(\\w+)\\b(?=[\\s\\S]+\\b\\1\\b[\\s\\S]+\\b\\1\\b)";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(input + " " + input);
        String output = match.replaceAll("_$1");
        output = output.substring(0, output.length() - 1 - p.matcher(input).replaceAll("_$1").length());
        return output;
    }
}
