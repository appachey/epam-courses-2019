package ua.nure.bychkov.practice5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class Part4 {
    private static int result = 0;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, InterruptedException {
//        SecureRandom rand = new SecureRandom();
//        StringBuilder sb = new StringBuilder();
//        Formatter f = new Formatter("part4.txt", "cp1251");
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 100; j++) {
//                int x = rand.nextInt(1000);
//                if (j == 99) {
//                    f.format("%d", x);
//                }else {
//                    f.format("%-4d", x);
//                }
//            }
//            f.format("%s", System.lineSeparator());
//        }
//        f.flush();
        Scanner scan = new Scanner(new File("part4.txt"), "cp1251");
        List<String> lines = new ArrayList<>();
        while (scan.hasNextLine()) {
            lines.add(scan.nextLine());
        }
        int cols = lines.get(0).split("\\s+").length;
        int rows = lines.size();
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                String[] str = lines.get(i).split("\\s+");
                matrix[i][j] = Integer.parseInt(str[j]);
            }
        }
        maxVol(matrix);
        advancedMaxVol(matrix);
    }
    public static void maxVol(int[][] matrix) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int result = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result = Math.max(matrix[i][j], result);
                Thread.sleep(1);
            }
        }
        System.out.println(result);
        System.out.println(System.currentTimeMillis() - startTime);
    }

    public static void advancedMaxVol(int[][] matrix) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread[] threads = new Worker[matrix.length];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Worker(matrix[i]);
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println(result);
        System.out.println(System.currentTimeMillis() - startTime);
    }
    static class Worker extends Thread{
        private int[] matrixRow;

        public Worker(int[] matrixRow) {
            this.matrixRow = matrixRow;
        }

        public void run() {
            for (int j = 0; j < matrixRow.length; j++) {
                result = Math.max(matrixRow[j], result);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
