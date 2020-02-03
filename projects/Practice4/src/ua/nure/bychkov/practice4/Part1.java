package ua.nure.bychkov.practice4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    private static final String ENCODING = "cp1251";
    private static final String F_NAME = "part1.txt";

    public static void main(String[] args) {
        String input = ReadUtil.readFile(F_NAME, ENCODING);
        System.out.println(invert(input));
    }

    public static String invert(String input) {
        StringBuilder sb = new StringBuilder(input);
        String regex = "(?Um)\\b(\\w{4,})\\b";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(sb);

        while (match.find()) {
            int count = match.group(1).length();
            int position = match.start(1);
            for (int i = 0; i < count; i++, position++) {
                char c = match.group(1).charAt(i);
                if (Character.isLowerCase(c)) {
                    sb.setCharAt(position, Character.toUpperCase(c));
                } else {
                    sb.setCharAt(position, Character.toLowerCase(c));
                }
            }
        }
        String output = sb.toString().replaceAll("\n|\n\r", System.lineSeparator());
        return output;
    }
}
