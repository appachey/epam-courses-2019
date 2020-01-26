package ua.nure.bychkov.practice3;

public class Part5 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 101; i++) {
            if (i == 100) {
                sb.append(i).append(" --> ").append(decimal2Roman(i)).append(" --> ").append(roman2Decimal(decimal2Roman(i)));
            } else {
                sb.append(i).append(" --> ").append(decimal2Roman(i)).append(" --> ").append(roman2Decimal(decimal2Roman(i))).append(System.lineSeparator());
            }

        }
        System.out.println(sb.toString());
        System.out.println("~~~~~~~~~~~~~~~");
    }

    public static String decimal2Roman(int x) {
        if (x < 1 || x > 100) {
            throw new IllegalArgumentException();
        }
        StringBuilder result = new StringBuilder();
        while (x == 100) {
            result.append("C");
            x -= 100;
        }

        while (x >= 90) {
            result.append("XC");
            x -= 90;
        }

        while (x >= 50) {
            result.append("L");
            x -= 50;
        }

        while (x >= 40) {
            result.append("XL");
            x -= 40;
        }

        while (x >= 10) {
            result.append("X");
            x -= 10;
        }

        while (x >= 9) {
            result.append("IX");
            x -= 9;
        }

        while (x >= 5) {
            result.append("V");
            x -= 5;
        }

        while (x >= 4) {
            result.append("IV");
            x -= 4;
        }

        while (x >=1) {
            result.append("I");
            x -= 1;
        }
        return result.toString();
    }

    private static int romanTable(char c){
        int num = 0;
        switch(c){
            case 'I':
                num = 1;
                break;
            case 'V':
                num = 5;
                break;
            case 'X':
                num = 10;
                break;
            case 'L':
                num = 50;
                break;
            case 'C':
                num = 100;
                break;
            default:
                num = 0;
                break;
        }
        return num;
    }

    public static int roman2Decimal(String s) {
        String regex = "(?)^(?=[CLXVI])(C?)|(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$";
        if (s == null || s.length() == 0 || !s.matches(regex)) {
            throw new IllegalArgumentException();
        }

        int prev = romanTable(s.charAt(s.length() - 1));
        int curr = 0;
        int result = prev;
        for (int i = s.length() - 2; i >= 0; i --) {
            curr = romanTable(s.charAt(i));
            if (curr < prev) {
                result -= curr;
                prev = 0;
            } else {
                result += curr;
                prev = curr;
            }
        }
        return result;
    }
}
