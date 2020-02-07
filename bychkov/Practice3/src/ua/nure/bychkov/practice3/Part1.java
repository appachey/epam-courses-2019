package ua.nure.bychkov.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.security.SecureRandom;

public class Part1 {
    private static final String FNAME = "part1.txt";
    private static final String L_SEPARATOR = System.lineSeparator();

    public static void main(String[] args) {
        String input = Util.readFile(FNAME);
        System.out.println(convert1(input));
        System.out.println(convert2(input));
        System.out.println(convert3(input));
        System.out.println(convert4(input));
        System.out.println("~~~~~~~~~~~~~~~");
    }

    public static String convert1(String input) {
        StringBuilder output = new StringBuilder();
        String regex = "(?Um)^(\\w+);\\b(\\w+\\s\\w+)\\b;(.+)$";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(input);
        while (match.find()) {
            output.append(match.group(1))
                    .append(": ")
                    .append(match.group(3))
                    .append(L_SEPARATOR);
        }
        output.delete(output.lastIndexOf(L_SEPARATOR), output.lastIndexOf(L_SEPARATOR) + L_SEPARATOR.length());
        return output.toString();
    }

    public static String convert2(String input) {
        StringBuilder output = new StringBuilder();
        String regex = "(?Um)^(\\w+);\\b(\\w+)\\s(\\w+)\\b;(.+)$";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(input);
        while (match.find()) {
            output.append(match.group(3))
                    .append(" ")
                    .append(match.group(2))
                    .append(" (email: ")
                    .append(match.group(4))
                    .append(")")
                    .append(L_SEPARATOR);
        }
        output.delete(output.lastIndexOf(L_SEPARATOR), output.lastIndexOf(L_SEPARATOR) + L_SEPARATOR.length());
        return output.toString();
    }

    public static String convert3(String input) {
        String regex = "(?m)^(\\S+?);(.+?)@(.+?)$";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(input);
        StringBuilder output = new StringBuilder();
        while (match.find()) {
            StringBuilder temp = new StringBuilder();
            temp.append(match.group(3)).append(" ==> ").append(match.group(1));
            if (output.length() == 0) {
                output.append(temp).append(L_SEPARATOR);
            } else {
                if (output.indexOf(match.group(3)) >= 0) {
                    int start = output.indexOf(match.group(3));
                    int end = output.indexOf(L_SEPARATOR, output.indexOf(match.group(3)));
                    output.replace(start, end, output.substring(start, end) + ", " + match.group(1));
                } else {
                    output.append(temp).append(L_SEPARATOR);
                }
            }
        }
        output.delete(output.lastIndexOf(L_SEPARATOR), output.lastIndexOf(L_SEPARATOR) + L_SEPARATOR.length());
        return output.toString();
    }

    public static String convert4(String input) {
        String regex = "(?Um)(.+?;.+?;(.+$))";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(input);
        StringBuilder output = new StringBuilder();
        while (match.find()) {
            if ("Email".equals(match.group(2))) {
                output.append(match.group()).append(";Password").append(L_SEPARATOR);
            } else {
                output.append(match.group()).append(";").append(passGenerator()).append(L_SEPARATOR);
            }
        }
        return output.toString();
    }

    private static String passGenerator() {
        SecureRandom sRand = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++){
            sb.append(0 + sRand.nextInt(10));
        }
        return sb.toString();
    }
}
