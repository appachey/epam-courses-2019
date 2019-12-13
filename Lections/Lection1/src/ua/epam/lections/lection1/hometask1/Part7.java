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
    public static int charToInt (char c){
        return (c - 'A') + 1;
    }
}
