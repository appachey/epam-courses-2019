package ua.epam.lections.lection1.hometask1;

public class Part7 {
    public static int chars2digits(String number){
        int sum = 0;
        char[] strToChar = number.toUpperCase().toCharArray();
        for (int i = 0; i < strToChar.length; i++){
            sum += (charToInt(strToChar[i]) * Math.pow(26, strToChar.length - i - 1));
        }
        return sum;
    }

    public static String digits2chars(int number){
        String output = "";
        while (number > 0){
            if (number % 26 == 0){
                output = intToChar((number % 26) + 26) + output;
                number = (number / 26) - 1;
            } else {
                output = intToChar(number % 26) + output;
                number /= 26;
            }
        }
        return output;
    }

    public static String rightColumn(String number){
        return digits2chars(chars2digits(number) + 1);
    }

    public static int charToInt (char c){
        return (c - 'A') + 1;
    }
    public static char intToChar (int num){
        return  (char) ((num + 'A') - 1);
    }
}
