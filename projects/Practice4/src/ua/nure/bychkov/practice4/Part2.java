package ua.nure.bychkov.practice4;

import java.security.SecureRandom;

public class Part2 {
    public static final int COUNT_RAND = 10;
    public static final int RAND_RANGE = 50;
    public static final String OUTPUT_DATA = "part2.txt";
    public static final String SORTED_OUTPUT_DATA = "part2_sorted.txt";
    public static final String ENCODING = "cp1251";

    public static void main(String[] args) {

    }

    public static int[] fillArray(int count) {
        SecureRandom rand = new SecureRandom();
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            int newRandom = rand.nextInt(50);
            result[i] = newRandom;
        }
        return result;
    }

    public static void writeRandomData(String fName, String encoding) {
        int[] data = fillArray(COUNT_RAND);
        StringBuilder dataToString = new StringBuilder();
        for(int i = 0; i < data.length; i++) {
            if (i == data.length - 1) {
                dataToString.append(data[i]);
            } else {
                dataToString.append(data[i]).append(" ");
            }
        }
        WriteUtil.writeFile(fName, dataToString.toString(), ENCODING);
    }
}
