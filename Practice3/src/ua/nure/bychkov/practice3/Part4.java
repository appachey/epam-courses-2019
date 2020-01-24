package ua.nure.bychkov.practice3;

public class Part4 {
    public static void main(String[] args) {
        byte[] bytes = {-29, 5, -2, 33, -40};
        StringBuilder sb = new StringBuilder();
        for(byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        System.out.println(sb.toString());
    }
}
