package ua.nure.bychkov.practice6;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part62 {
    private WordContainer words;
    private String text;

    public Part62 (String fName) {
        text = ReadUtil.readFile(fName);
        words = new WordContainer();
    }

    private List<Word> most3Len() {
        String regex = "(?Umi)\\b(\\w+)\\b";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(text);
        while (match.find()) {
            words.add(new Word(match.group(1)));
        }
        List<Word> wList = words.getWordsList();
        wList.sort((w1, w2) -> - w1.getContent().length() + w2.getContent().length());
        return wList.subList(0, 3);
    }

    public void printWords() {
        List<Word> output = most3Len();
        for (Word w : output) {
            System.out.println(w.getContent() + " ==> " + w.getContent().length());
        }
    }

    public static void main(String[] args) {
        Part62 p62 = new Part62("part6.txt");
        p62.printWords();
    }
}
