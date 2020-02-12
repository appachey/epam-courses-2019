package ua.nure.bychkov.practice5;

public class Part6 {
    private static final Object M = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread() {
            public void run() {
                synchronized (M) {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            M.notifyAll();
                            M.wait();
                        } catch (InterruptedException ex) {
                            interrupt();
                        }
                    }
                }
            }
        };
        t.start();
        synchronized (M) {
            M.wait(100);
            M.notifyAll();
            System.out.println(t.getState());
            M.wait();
            System.out.println(t.getState());
            t.interrupt();
            M.wait(100);
            System.out.println(t.getState());
        }
        t.join();
    }
}
