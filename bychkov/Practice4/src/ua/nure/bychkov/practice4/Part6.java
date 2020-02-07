package ua.nure.bychkov.practice4;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {
    private static final String F_NAME = "part6.txt";
    private static final String ENCODING = "cp1251";

    public static void main(String[] args) {
        finder(F_NAME, ENCODING);
    }

    private static String find(String loc, String input) {
        Matcher match;
        StringBuilder output = new StringBuilder();
        if ("Latn".equals(loc) || "latn".equals(loc)) {
            match = Pattern.compile("(?m)\\b(\\w+)\\b").matcher(input);
            output.append(loc).append(": ");
        } else if("Cyrl".equals(loc) || "cyrl".equals(loc)) {
            match = Pattern.compile("(?m)\\b([\\u0400-\\u04FF]+)\\b").matcher(input);
            output.append(loc).append(": ");
        } else {
            return loc + ": Incorrect input";
        }
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

    public static void finder(String fName, String encoding) {
        Scanner scan = new Scanner(System.in);
        String input = ReadUtil.readFile(fName, encoding);
        String consoleInput = "";
        while (true) {
            consoleInput = scan.nextLine();
            if ("stop".equals(consoleInput) || "Stop".equals(consoleInput)) {
                break;
            }
            System.out.println(find(consoleInput, input));
        }
    }
}
