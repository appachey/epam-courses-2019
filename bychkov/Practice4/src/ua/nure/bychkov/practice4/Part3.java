package ua.nure.bychkov.practice4;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
    private static final String F_NAME = "part3.txt";
    private static final String ENCODING = "cp1251";
    public static void main(String[] args) {
        parser(F_NAME, ENCODING);
    }

    private static String finder(String base, String input) {
        Matcher match;
        switch (base) {
            case ("char"):
                match = Pattern.compile("(?Um)\\b(\\p{Alpha})\\b").matcher(input);
                break;
            case ("String"):
                match = Pattern.compile("(?Um)\\b(\\p{Alpha}{2,}+)\\b").matcher(input);
                break;
            case ("int"):
                match = Pattern.compile("(?Um)(?<=\\s|^)(\\d+)(?=\\s|$)").matcher(input);
                break;
            case ("double"):
                match = Pattern.compile("(?Um)(\\.\\d+|\\d+\\.\\d+|\\d+\\.)").matcher(input);
                break;
            default:
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
