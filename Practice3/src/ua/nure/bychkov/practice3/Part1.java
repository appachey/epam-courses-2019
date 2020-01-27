package ua.nure.bychkov.practice3;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String regex = "(?m)^(\\p{javaLowerCase}\\S+?);(.+?);(\\S+?)$";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(input);
        while (match.find()) {
            output.append(match.group(1)).append(": ").append(match.group(3)).append(L_SEPARATOR);
        }
        output.delete(output.lastIndexOf(L_SEPARATOR), output.lastIndexOf(L_SEPARATOR) + L_SEPARATOR.length());
        return output.toString();
    }

    public static String convert2(String input) {
        StringBuilder output = new StringBuilder();
        String regex = "(?m)^(\\p{javaLowerCase}\\S+?);(.+?);(\\S+?)$";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(input);
        while (match.find()) {
            output.append(match.group(2)).append(" (email: ").append(match.group(3)).append(")").append(L_SEPARATOR);
        }
        output.delete(output.lastIndexOf(L_SEPARATOR), output.lastIndexOf(L_SEPARATOR) + L_SEPARATOR.length());
        return output.toString();
    }

    public static String convert3(String input) {
        String regex = "(?m)^(\\S+?);(.+?)@(.+?)$";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(input);
        StringBuffer sbuff = new StringBuffer();
        while (match.find()) {
            StringBuffer temp = new StringBuffer();
            temp.append(match.group(3)).append(" ==> ").append(match.group(1));
            if (sbuff.length() == 0) {
                sbuff.append(temp).append(L_SEPARATOR);
            } else {
                if (sbuff.indexOf(match.group(3)) >= 0) {
                    int start = sbuff.indexOf(match.group(3));
                    int end = sbuff.indexOf(L_SEPARATOR, sbuff.indexOf(match.group(3)));
                    sbuff.replace(start, end, sbuff.substring(start, end) + ", " + match.group(1));
                } else {
                    sbuff.append(temp).append(L_SEPARATOR);
                }
            }
        }
        sbuff.delete(sbuff.lastIndexOf(L_SEPARATOR), sbuff.lastIndexOf(L_SEPARATOR) + L_SEPARATOR.length());
        return sbuff.toString();
    }

    public static String convert4(String input) {
        String regex = "(?m)(.+?);(.+?);(.+?)$";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(input);
        StringBuffer sbuff = new StringBuffer();
        while (match.find()) {
            StringBuilder temp = new StringBuilder();
            if ("Email".equals(match.group(3))) {
                temp.append(match.group(0)).append(";Password");
                match.appendReplacement(sbuff, temp.toString());
            } else {
                temp.append(match.group(0)).append(";").append(passGenerator());
                match.appendReplacement(sbuff, temp.toString());
            }
        }
        match.appendTail(sbuff);
        return sbuff.toString();
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
