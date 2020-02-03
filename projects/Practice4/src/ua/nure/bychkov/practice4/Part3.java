package ua.nure.bychkov.practice4;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
    public static void main(String[] args) {
        parser("part3.txt", "cp1251");
    }

    private static String finder(String base, String input) {
        Matcher match;
        if ("char".equals(base)) {
            match = Pattern.compile("(?Um)\\b(\\p{Alpha})\\b").matcher(input);
        } else if("String".equals(base)) {
            match = Pattern.compile("(?Um)\\b(\\p{Alpha}{2,}+)\\b").matcher(input);
        } else if("int".equals(base)) {
            match = Pattern.compile("(?Um)(?<=\\s|^)(\\d+)(?=\\s|$)").matcher(input);
        } else if("double".equals(base)) {
            match = Pattern.compile("(?Um)(\\.\\d+|\\d+\\.\\d+|\\d+\\.)").matcher(input);
        } else {
            return "Incorrect input";
        }
        StringBuilder output = new StringBuilder();
        if (!match.find()) {
            return "No such values";
        } else {
            match.reset();
            while(match.find()) {
                output.append(match.group()).append(" ");
            }
        }
        return output.toString().trim();
    }

    public static void parser(String fileName, String encoding) {
        Scanner scan = new Scanner(System.in);
        String input = ReadUtil.readFile(fileName, encoding);
        String consoleInput;
        while (!"stop".equals(consoleInput = scan.nextLine())) {
            System.out.println(finder(consoleInput, input));
        }
    }
}
