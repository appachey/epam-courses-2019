package ua.nure.bychkov.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    private static final String F_NAME = "part2.txt";
    public static void main(String[] args) {
        String input = Util.readFile(F_NAME);
        System.out.println(convert(input));
    }

    public static String convert(String input) {
        String regex = "(?Um)\\b(\\w+?)\\b";
        String regex2 = "(?Um)\\b(\\w+?)\\b(?=.+\\b\\1\\b)";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(input);
        StringBuilder minStr = new StringBuilder();
        StringBuilder maxStr = new StringBuilder();
        int min = input.length();
        int max = 0;
        while (match.find()) {
            //System.out.println(match.group(1));
            if (match.group(1).length() < min) {
                min = match.group(1).length();
                if (minStr.length() == 0) {
                    minStr.append(match.group(1));
                } else {
                    minStr.replace(0, minStr.length(), match.group(1));
                }
            } else if (match.group(1).length() == min){
                minStr.append(", ").append(match.group(1));
            }

            if (match.group(1).length() > max) {
                max = match.group(1).length();
                if (maxStr.length() == 0) {
                    maxStr.append(match.group(1));
                } else {
                    maxStr.replace(0, maxStr.length(), match.group(1));
                }
            } else if (match.group(1).length() == max) {
                maxStr.append(", ").append(match.group(1));
            }
        }
        String result = maxStr.reverse().toString();
        result = result.replaceAll(regex2, "");
        result = new StringBuilder(result).reverse().toString();

        return result;
    }
}
