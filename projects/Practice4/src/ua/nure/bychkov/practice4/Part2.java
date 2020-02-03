package ua.nure.bychkov.practice4;

import java.security.SecureRandom;

public class Part2 {
    public static final int COUNT_RAND = 10;
    public static final int RAND_RANGE = 51;
    public static final String OUTPUT_DATA = "part2.txt";
    public static final String SORTED_OUTPUT_DATA = "part2_sorted.txt";
    public static final String ENCODING = "cp1251";
    public static final String L_SEPARATOR = System.lineSeparator();

    public static void main(String[] args) {
        writeRandomData(OUTPUT_DATA, ENCODING);
        writeSortedData(OUTPUT_DATA, ENCODING);
        System.out.println(consoleOutput());
    }

    private static int[] fillArray(int count) {
        SecureRandom rand = new SecureRandom();
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            int newRandom = rand.nextInt(RAND_RANGE);
            result[i] = newRandom;
        }
        return result;
    }

    private static int[] sortData(int[] data) {
        int[] result = data;
        for (int i = result.length - 1; i > 0; i--){
            for(int j = 0 ; j < i ; j++){
                if( result[j] > result[j+1] ){
                    int tmp = result[j];
                    result[j] = result[j+1];
                    result[j+1] = tmp;
                }
            }
        }
        return result;
    }

    private static String joinArray(int[] data) {
        StringBuilder dataToString = new StringBuilder();
        for(int i = 0; i < data.length; i++) {

            dataToString.append(data[i]).append(" ");
        }
        return dataToString.toString().trim();
    }

    public static void writeRandomData(String fName, String encoding) {
        int[] data = fillArray(COUNT_RAND);
        String joinedData = joinArray(data);
        WriteUtil.writeFile(fName, joinedData, encoding);
    }

    public static void writeSortedData(String fName, String encoding) {
        String str = ReadUtil.readFile(fName, encoding);
        String[] temp = str.split(" ");
        int[] data = new int[temp.length];
        for (int i = 0; i < data.length; i++) {
            data[i] = Integer.parseInt(temp[i]);
        }
        int[] sortedData = sortData(data);
        String joinedSortedData = joinArray(sortedData);
        WriteUtil.writeFile(SORTED_OUTPUT_DATA, joinedSortedData, encoding);
    }

    public static String consoleOutput() {
        StringBuilder sbOutput = new StringBuilder();
        String input = ReadUtil.readFile(OUTPUT_DATA, ENCODING);
        String output = ReadUtil.readFile(SORTED_OUTPUT_DATA, ENCODING);
        sbOutput.append("input ==> ")
                .append(input)
                .append(L_SEPARATOR)
                .append("output ==> ")
                .append(output);
        return sbOutput.toString();
    }
}
