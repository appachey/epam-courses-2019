package ua.nure.bychkov.practice5;

import java.io.InputStream;

public class Part2 {
    private static final InputStream STD_IN = System.in;

    public static void main(String[] args) throws InterruptedException {
        InputStream inStream = new MyInputStream(System.lineSeparator().getBytes());
        System.setIn(inStream);
        Thread t = new Thread(){
            public void run() {Spam.main(null);}
        };
        t.start();
        t.join();
        System.setIn(STD_IN);
    }
}
