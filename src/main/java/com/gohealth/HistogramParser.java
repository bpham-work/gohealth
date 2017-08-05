package com.gohealth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The HistogramParser class parses Strings to build a map of bigram counts.
 * A bigram is any two adjacent words in the String disregarding case
 * */
public class HistogramParser {

    /**
     * Parses lines of text for bigrams counts. Returns a map containing number of occurrences for each bigram
     *
     * @param strings - List of strings to parse
     *
     * @return Map containing bigram counts. Keys are bigrams. Values are counts for each bigram
     * */
    public Map<String, Integer> parseForBigramCount(List<String> strings) {
        Map<String, Integer> histogramMap = new HashMap<>();
        for (String string : strings) {
            parseString(histogramMap, string);
        }
        return histogramMap;
    }

    /**
     * Parses string for bigram counts
     *
     * @param histogramMap - Map tracking bigram counts
     * @param string - String to parse for bigrams
     * */
    public void parseString(Map<String, Integer> histogramMap, String string) {
        string = removePunctuation(string);
        string = removeExtraWhitespace(string);
        String[] words = string.toLowerCase().split(" ");
        for (int k = 0; k < words.length - 1; k++) {
            String bigram = words[k] + " " + words[k+1];
            Integer bigramCount = histogramMap.getOrDefault(bigram, 0);
            histogramMap.put(bigram, bigramCount + 1);
        }
    }

    private String removePunctuation(String string) {
        return string.replaceAll("[^a-zA-Z\\s-]", "");
    }

    private String removeExtraWhitespace(String string) {
        string = string.trim();
        return string.replaceAll("\\s+", " ");
    }
}
