package ua.nure.bychkov.practice5;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Part5 {
    private static final String OUTPUT = "part5.txt";
    private static final int K = 10;
    private static final Object monitor = new Object();
    public static void main(String[] args) throws IOException, InterruptedException {
        writeLn();
        String str = new String(Files.readAllBytes(Paths.get(OUTPUT)));
        System.out.println(str);
    }

    public static void writeLn () throws IOException, InterruptedException {
        Files.deleteIfExists(Paths.get(OUTPUT));
        RandomAccessFile raf = new RandomAccessFile(OUTPUT, "rw");
        Thread[] threads = new Worker[K];
        for (int i = 0; i < K; i++) {
            threads[i] = new Worker(raf, i);
        }
        for (Thread t : threads) {
            t.join();
        }
        raf.close();
    }

    private static class Worker extends Thread {
        private int number;
        private long offset;
        private static final int COUNT = 20;
        private static final String L_SEP = System.lineSeparator();
        private RandomAccessFile raf;

        public Worker (RandomAccessFile raf, int number) {
            this.number = number;
            this.raf = raf;
            offset = number * (COUNT + L_SEP.length());
            this.start();
        }

        public void run() {
            try {
                writeLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

        public void writeLine() throws IOException {
            synchronized (monitor) {
                raf.seek(offset);
                for (int i = 0; i < COUNT; i++) {
                    raf.writeByte('0' + number);
                    try {
                        sleep(1);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                raf.writeBytes(L_SEP);
            }
        }
    }
}
