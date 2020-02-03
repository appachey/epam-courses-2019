package ua.nure.bychkov.practice4;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Part5 {
    private static final String RES = "resources";
    public static void main(String[] args) {
        localizator();
    }

    public static void localizator() {
        Scanner scan = new Scanner(System.in);
        String input;
        while (!"stop".equals(input = scan.nextLine())) {
            String[] properties = input.split(" ");
            if (properties.length <= 1) {
                ResourceBundle rb = ResourceBundle.getBundle(RES);
                try {
                    System.out.println(rb.getString(properties[0]));
                } catch (MissingResourceException ex) {
                    System.out.println("No such value");
                }
            } else {
                ResourceBundle rb = ResourceBundle.getBundle(RES, new Locale(properties[1]));
                try {
                    System.out.println(rb.getString(properties[0]));
                } catch (MissingResourceException ex) {
                    System.out.println("No such value");
                }
            }
        }
    }
}
