package ua.nure.bychkov.practice1;

public class Part3 {
    public static void main(String[] args) {
        StringBuilder outStr = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            if (i != args.length - 1) {
                outStr.append(args[i] + " ");
            } else {
                outStr.append(args[i]);
            }
        }
        System.out.println(outStr);
    }
}
