package ua.nure.bychkov.practice5;

import java.io.InputStream;

public class MyInputStream extends InputStream {
    private byte [] arr;
    private int pos;
    private int length;

    public MyInputStream(byte[] arr) {
        this.arr = arr;
        pos = 0;
        length = arr.length;
    }
    @Override
    public int read() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return (pos < length) ? (arr[pos++] & 0xff) : -1;
    }
}
