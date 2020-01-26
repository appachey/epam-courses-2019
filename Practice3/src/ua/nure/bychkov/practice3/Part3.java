package ua.nure.bychkov.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
    private static final String F_NAME = "part3.txt";
    public static void main(String[] args) {
        String input = Util.readFile(F_NAME);
        System.out.println(convert(input));
        System.out.println("~~~~~~~~~~~~~~~");
    }

    public static String convert(String input) {
        StringBuilder sb = new StringBuilder(input);
        String regex = "(?Um)\\b(\\w{3,})\\b";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(sb);

        while (match.find()) {
            char start = sb.charAt(match.start(1));
            if (Character.isLowerCase(start)) {
                sb.setCharAt(match.start(1), Character.toUpperCase(start));
            } else {
                sb.setCharAt(match.start(1), Character.toLowerCase(start));
            }
        }
        return sb.toString();
    }
}
