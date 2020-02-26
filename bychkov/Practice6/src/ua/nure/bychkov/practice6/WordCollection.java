package ua.nure.bychkov.practice6;

import java.util.ArrayList;
import java.util.List;

public class WordCollection {
    private ArrayList<Word> words;

    public WordCollection() {
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
        return words;
    }
}
