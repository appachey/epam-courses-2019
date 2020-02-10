package ua.nure.bychkov.practice5;

import java.security.SecureRandom;

public class Part4 {
    public static void main(String[] args) {
        SecureRandom rand = new SecureRandom();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 100; j++) {
                int x = rand.nextInt(1000);
                System.out.printf("%4d", x);
            }
            System.out.println();
        }
    }
}
