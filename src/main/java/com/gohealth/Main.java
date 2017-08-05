package com.gohealth;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("You must include the path to a file to be parsed");
        }

        String filePath = args[0];
        try {
            List<String> linesOfText = Files.readAllLines(Paths.get(filePath));
            HistogramParser parser = new HistogramParser();
            Map<String, Integer> histogramMap = parser.parse(linesOfText);
            printHistogram(histogramMap);
        } catch (IOException e) {
            System.out.println("Could not read file");
        }
    }

    private static void printHistogram(Map<String, Integer> histogramMap) {
        for (Map.Entry<String, Integer> entry : histogramMap.entrySet()) {
            String bigram = entry.getKey();
            Integer bigramCount = entry.getValue();
            System.out.println(String.format("%s: %s", bigram, bigramCount));
        }
    }
}
