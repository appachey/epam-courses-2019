package ua.nure.bychkov.practice6;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part61 {
    private WordContainer words;
    private String text;

    public Part61 (String fName) {
        text = ReadUtil.readFile(fName);
        words = new WordContainer();
    }

    private List<Word> most3Freq() {
        String regex = "(?Umi)\\b(\\w+)\\b";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(text);
        while (match.find()) {
            words.add(new Word(match.group(1)));
        }
        List<Word> wList = words.getWordsList();
        wList.sort((w1, w2) -> - w1.getFrequency() + w2.getFrequency());
        List<Word> result = wList.subList(0, 3);
        result.sort((w1, w2) -> -w1.getContent().compareTo(w2.getContent()));
        return result;
    }

    public void printWords() {
        List<Word> output = most3Freq();
        for (Word w : output) {
            System.out.println(w.getContent() + " ==> " + w.getFrequency());
        }
    }
}
