package ua.nure.bychkov.practice5;

public class Part3 {
    private int counter;
    private int counter2;
    private int count;
    private int ms;
    private final Object lock;
    private Thread[] threads;

    public Part3(int n, int k, int t) {
        counter = 0;
        counter2 = 0;
        threads = new Thread[n];
        count = k;
        ms = t;
        lock = new Object();
    }

    public static void main(String[] args) throws InterruptedException {
        Part3 p = new Part3(3, 5, 100);
        p.test();
        p.reset();
        p.testSync();
    }

    public void reset() {
        counter = 0;
        counter2 = 0;
    }
    public void test() throws InterruptedException {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < count; j++) {
                    System.out.printf("%s %s%n", counter, counter2);
                    counter++;
                    try {
                        Thread.sleep(ms);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    counter2++;
                }
            });
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }

    public void testSync() throws InterruptedException {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                    for (int j = 0; j < count; j++) {
                        synchronized (lock) {
                            System.out.printf("%s %s%n", counter, counter2);
                            counter++;
                            try {
                                Thread.sleep(ms);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            counter2++;
                        }
                    }
                }
            );
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }
}
