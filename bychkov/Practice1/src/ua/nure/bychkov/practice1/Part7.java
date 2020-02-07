package ua.nure.bychkov.practice1;

import java.util.Locale;

public class Part7 {
    public static final int BASE = 26;
    public static void main(String[] args) {
        String[] inputData = {"A", "B", "Z", "AA", "AZ", "BA", "ZZ", "AAA"};
        for (String param : inputData){
            System.out.println(param + " ==> " + str2int(param) + " ==> " + int2str(str2int(param)));
        }
    }
    public static int str2int(String number){
        int sum = 0;
        String inputStr = number.toUpperCase(Locale.ENGLISH);
        char[] strToChar = inputStr.toCharArray();
        for (int i = 0; i < strToChar.length; i++){
            sum += (charToInt(strToChar[i]) * Math.pow(BASE, (double) strToChar.length - i - 1));
        }
        return sum;
    }

    public static String int2str(int number){
        StringBuilder output = new StringBuilder();
        while (number > 0){
            if (number % BASE == 0){
                output.append(intToChar((number % BASE) + BASE));
                number = (number / BASE) - 1;
            } else {
                output.append(intToChar(number % BASE));
                number /= BASE;
            }
        }
        return output.reverse().toString();
    }

    public static String rightColumn(String number){
        return int2str(str2int(number) + 1);
    }

    public static int charToInt (char c){
        return (c - 'A') + 1;
    }
    public static char intToChar (int num){
        return  (char) ((num + 'A') - 1);
    }
}