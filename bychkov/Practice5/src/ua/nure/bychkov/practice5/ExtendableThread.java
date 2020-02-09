package ua.nure.bychkov.practice5;


public class ExtendableThread extends Thread {
    private static final int MS = 333;

    public void run() {
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
