package ua.nure.bychkov.practice1;

public class Part4 {
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        System.out.println(gcd(x, y));
    }
    public static int gcd(int x, int y){
        if (y == 0){
            return Math.abs(x);
        } else {
            return gcd (y, x % y);
        }
    }
}
