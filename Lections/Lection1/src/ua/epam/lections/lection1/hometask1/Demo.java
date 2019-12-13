package ua.epam.lections.lection1.hometask1;

public class Demo {
    public static void main(String[] args) {
        /*Part6.main(new String[]{"1000"});
        System.out.println(Math.pow(26, 0));
        String str = "abc";
        int sum = 0;
        char[] strToChar = str.toUpperCase().toCharArray();
        for (int i = 0; i < strToChar.length; i++){
            sum += (charToInt(strToChar[i]) * Math.pow(26, strToChar.length - i - 1));
        }
        System.out.println(sum);
        int sum = 701;
        int num = 53;
        String output = "";
        while (num > 0){
            if (num % 26 == 0){
                output = intToChar((num % 26) + 26) + output;
                num = (num / 26) - 1;
            } else {
                output = intToChar(num % 26) + output;
                num /= 26;
            }
        }
        System.out.println(output);

        System.out.println((703 / 26));*/
        System.out.println(Part7.chars2digits("aaa"));
        System.out.println(Part7.digits2chars(703));
        System.out.println(Part7.rightColumn("ZZ"));
    }
}
