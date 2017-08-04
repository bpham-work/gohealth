package com.gohealth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistogramParser {
    public Map<String, Integer> parse(List<String> linesOfText) {
        Map<String, Integer> histogramMap = new HashMap<>();
        for (String text : linesOfText) {
            parseLine(histogramMap, text);
        }
        return histogramMap;
    }

    public void parseLine(Map<String, Integer> histogramMap, String text) {
        text = removePunctuation(text);
        text = removeExtraWhitespace(text);
        String[] words = text.toLowerCase().split(" ");
        for (int k = 0; k < words.length - 1; k++) {
            String bigram = words[k] + " " + words[k+1];
            Integer bigramCount = histogramMap.getOrDefault(bigram, 0);
            histogramMap.put(bigram, bigramCount + 1);
        }
    }

    private String removePunctuation(String text) {
        return text.replaceAll("[^a-zA-Z\\s-]", "");
    }

    private String removeExtraWhitespace(String text) {
        text = text.trim();
        return text.replaceAll("\\s+", " ");
    }
}
