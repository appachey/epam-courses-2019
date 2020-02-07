package ua.nure.bychkov.practice5;

import java.io.IOException;
import java.io.InputStream;

public class MyInputStream extends InputStream {
    @Override
    public int read() throws IOException {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
}
