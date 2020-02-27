package ua.nure.bychkov.practice6;

public class Part6 {
    private static String fName;
    private static String tName;

    public static void main(String[] args) {
        consoleParser(args);
        createTask(tName, fName);
    }

    private static void consoleParser (String[] input) {
        if (input.length != 4) {
            throw new IllegalArgumentException();
        }

        if ("--input".equals(input[0]) || "-i".equals(input[0])) {
            fName = input[1];
        } else if ("--input".equals(input[2]) || "-i".equals(input[2])) {
            fName = input[3];
        } else {
            throw new IllegalArgumentException();
        }

        if ("--task".equals(input[0]) || "-t".equals(input[0])) {
            tName = input[1];
        } else if ("--task".equals(input[2]) || "-t".equals(input[2])) {
            tName = input[3];
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static void createTask (String taskName, String fileName) {
        switch (taskName) {
            case ("frequency") :
                Part61 freq = new Part61(fileName);
                freq.printWords();
                break;
            case ("length") :
                Part62 len = new Part62(fileName);
                len.printWords();
                break;
            case ("duplicates") :
                Part63 dup = new Part63(fileName);
                dup.printWords();
                break;
            default:
                break;
        }
    }
}
