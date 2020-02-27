package ua.nure.bychkov.practice6;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Part62 {
    private List<String> words;
    private String text;

    public Part62 (String fName) {
        text = ReadUtil.readFile(fName);
        words = new ArrayList<>();
    }

    private void most3Len() {
        String regex = "(?Umi)\\b(\\w+)\\b";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(text);
        while (match.find()) {
            words.add(match.group(1));
        }
    }

    public void printWords() {
        most3Len();
        words.stream().sorted((w1, w2) -> w2.length() - w1.length())
                .limit(3)
                .collect(Collectors.toList())
                .forEach(w -> System.out.println(w + " ==> " + w.length()));

    }
}
