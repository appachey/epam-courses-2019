package ua.nure.bycgkov.practice6;

import java.util.Objects;

public class Word implements Comparable<Word>{
    private String content;
    private int frequency;

    public Word (String content) {
        this.content = content;
        this.frequency = 1;
    }

    public void increaseFreq() {
        frequency++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Word word = (Word) o;
        return Objects.equals(content, word.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public String toString() {
        return content + " : " + frequency;
    }

    @Override
    public int compareTo(Word w) {
        return frequency - w.frequency;
    }
}
