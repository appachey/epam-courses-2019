package ua.nure.bychkov.practice8.db;

public class MyException extends Exception {
    public MyException(String message, Throwable primary) {
        super(message, primary);
    }
}
