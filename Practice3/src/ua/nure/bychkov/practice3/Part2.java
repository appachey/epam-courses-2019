package ua.nure.bychkov.practice3;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Part2 {
    private static final String F_NAME = "part2.txt";
    public static void main(String[] args) {
        String input = Util.readFile(F_NAME);
        System.out.println(convert(input));
        System.out.println("~~~~~~~~~~~~~~~");
    }

    public static String convert(String input) {
        String regex = "(?Um)\\b(\\w+?)\\b";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(input);
        StringBuilder minStr = new StringBuilder();
        StringBuilder maxStr = new StringBuilder();
        int min = input.length();
        int max = 0;
        while (match.find()) {
            if (match.group(1).length() < min) {
                min = match.group(1).length();
                if (minStr.length() == 0) {
                    minStr.append(match.group(1));
                } else {
                    minStr.replace(0, minStr.length(), match.group(1));
                }
            } else if (match.group(1).length() == min){
                minStr.append(" ").append(match.group(1));
            }

            if (match.group(1).length() > max) {
                max = match.group(1).length();
                if (maxStr.length() == 0) {
                    maxStr.append(match.group(1));
                } else {
                    maxStr.replace(0, maxStr.length(), match.group(1));
                }
            } else if (match.group(1).length() == max) {
                maxStr.append(" ").append(match.group(1));
            }
        }
        StringBuilder result = new StringBuilder();
        result.append("Min: ")
                .append(Arrays.stream(minStr.toString().split("\\s+"))
                        .distinct()
                        .collect(Collectors.joining(", ")))
                .append(System.lineSeparator())
                .append("Max: ")
                .append(Arrays.stream(maxStr.toString().split("\\s+"))
                        .distinct()
                        .collect(Collectors.joining(", ")));
        return result.toString();
    }
}
