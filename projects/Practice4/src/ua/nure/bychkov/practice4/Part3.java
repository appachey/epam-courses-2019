package ua.nure.bychkov.practice4;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
    public static void main(String[] args) {
        parser("part3.txt", "cp1251");

    }
    public static void parser(String fileName, String encoding) {
        Scanner scan = new Scanner(System.in);
        String input = ReadUtil.readFile(fileName, encoding);
        Matcher charMatch = Pattern.compile("(?Um)\\b(\\p{Alpha})\\b").matcher(input);
        Matcher strMatch = Pattern.compile("(?Um)\\b(\\p{Alpha}{2,}+)\\b").matcher(input);
        Matcher intMatch = Pattern.compile("(?Um)(?<=\\s|^)(\\d+)(?=\\s|$)").matcher(input);
        Matcher doubleMatch = Pattern.compile("(?Um)(\\.\\d+|\\d+\\.\\d+|\\d+\\.)").matcher(input);
        String consoleInput = "";
        while (!"stop".equals(consoleInput)) {
            consoleInput = scan.nextLine();
            if ("char".equals(consoleInput)) {
                StringBuilder charOutput = new StringBuilder();
                while(charMatch.find()) {
                    charOutput.append(charMatch.group(1)).append(" ");
                }
                charMatch.reset();
                System.out.println(charOutput.toString().trim());
            } else if("String".equals(consoleInput)) {
                StringBuilder strOutput = new StringBuilder();
                while (strMatch.find()) {
                    strOutput.append(strMatch.group(1)).append(" ");
                }
                strMatch.reset();
                System.out.println(strOutput.toString().trim());
            } else if("int".equals(consoleInput)) {
                StringBuilder intOutput = new StringBuilder();
                while (intMatch.find()) {
                    intOutput.append(intMatch.group(1)).append(" ");
                }
                intMatch.reset();
                System.out.println(intOutput.toString().trim());
            } else if("double".equals(consoleInput)) {
                StringBuilder doubleOutput = new StringBuilder();
                while (doubleMatch.find()) {
                    doubleOutput.append(doubleMatch.group(1)).append(" ");
                }
                doubleMatch.reset();
                System.out.println(doubleOutput.toString().trim());
            } else {
                if(!"stop".equals(consoleInput)) {
                    System.out.println("No such values");
                }
            }
        }
    }
}
