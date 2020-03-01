package ua.nure.bychkov.practice6.part1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordContainer {
    private ArrayList<Word> words;

    public WordContainer() {
        words = new ArrayList<>();
    }

    public boolean add(Word w) {
        if (words.contains(w)) {
            Word temp = words.get(words.lastIndexOf(w));
            temp.increaseFreq();
            return true;
        } else {
            return words.add(w);
        }
    }

    public List<Word> getWordsList() {
        return words.subList(0, words.size());
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        WordContainer cont = new WordContainer();
        while(true) {
            String str = scan.next();
            if ("stop".equals(str)) {
                break;
            }

            Word word = new Word(str);
            cont.add(word);
        }
        cont.printWords();
        scan.close();
    }

    public void printWords() {
        words.sort(Word::compareTo);
        for (Word w : words) {
            System.out.println(w);
        }
    }
}
