package ua.nure.bychkov.practice6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Part63 {
    private List<String> words;
    private String text;

    public Part63 (String fName) {
        text = ReadUtil.readFile(fName);
        words = new ArrayList<>();
    }

    private List<String> first3Dup() {
        String regex = "(?Umi)\\b(\\w+)\\b";
        Pattern p = Pattern.compile(regex);
        Matcher match = p.matcher(text);
        while (match.find()) {
            words.add(match.group(1));
        }
        return words.stream().filter(i -> Collections.frequency(words, i) > 1).distinct().limit(3)
                .collect(Collectors.toList());
    }

    public void printWords() {
        List<String> output = first3Dup();

        for (String s : output) {
            StringBuilder sb = new StringBuilder(s);
            System.out.println(sb.reverse().toString().toUpperCase());
        }
    }
}
