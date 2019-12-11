package ua.epam.lections.lection1.hometask1;

public class Demo {
    public static void main(String[] args) {
        //Part6.main(new String[]{"1000"});
        //System.out.println(Math.pow(26, 0));
        String str = "abc";
        int sum = 0;
        char[] strToChar = str.toUpperCase().toCharArray();
        for (int i = 0; i < strToChar.length; i++){
            sum += (charToInt(strToChar[i]) * Math.pow(26, strToChar.length - i - 1));
        }
        System.out.println(sum);
    }
    public static int charToInt (char c){
        return (c - 'A') + 1;
    }
}
