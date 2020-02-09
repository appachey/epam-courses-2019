package ua.nure.bychkov.practice5;

import static java.lang.Thread.sleep;

public class Part1 {
    private static final int MS = 333;

    public static void main(String[] args) throws InterruptedException {
        ExtendableThread t = new ExtendableThread();
        Thread t2 = new Thread(new RunnableThread());
        t.start();
        t.join();
        t2.start();
        t2.join();
        Runnable r = Part1::runnableM;
        Thread t3 = new Thread(r);
        t3.start();
        t3.join();
    }

    public static void runnableM() {
        Thread t = Thread.currentThread();
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + 1000) {
            System.out.println(t.getName());

            try{
                sleep(MS);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
