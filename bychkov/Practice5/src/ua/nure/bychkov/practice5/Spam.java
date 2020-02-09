package ua.nure.bychkov.practice5;

import java.util.Scanner;

public class Spam {
    private Thread[] threads;
    public Spam(String[] messages, int[] times){
        Thread[] temp = new Thread[messages.length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = new Worker(messages[i], times[i]);
        }
        threads = temp;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] messages = {"@@@", "$$$$$$"};
        int[] times = {333, 222};
        Spam spam = new Spam(messages, times);
        spam.start();
        String input = scan.nextLine();
        spam.stop();
    }

    public void start() {
        for (Thread t : threads){
            t.start();
        }
    }

    public void stop() {
        for (Thread t : threads) {
            t.interrupt();
        }
    }

    private static class Worker extends Thread {
        private String message;
        private int ms;

        Worker(String message, int ms){
            this.message = message;
            this.ms = ms;
        }

        public void run(){
            while(!isInterrupted()) {
                System.out.println(message);
                try {
                    sleep(ms);
                } catch (InterruptedException ex) {
                    interrupt();
                }
            }
        }
    }
}
