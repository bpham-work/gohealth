package com.gohealth;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class HistogramParserTest {
    private HistogramParser histogramParser = new HistogramParser();

    @Test
    public void shouldParseStringForBigramCount() {
        Map<String, Integer> histogramMap = new HashMap<>();
        String text = "the quick brown fox and the quick blue hare";

        histogramParser.parseString(histogramMap, text);

        assertEquals(2, (int) histogramMap.get("the quick"));
        assertEquals(1, (int) histogramMap.get("quick brown"));
        assertEquals(1, (int) histogramMap.get("brown fox"));
        assertEquals(1, (int) histogramMap.get("fox and"));
        assertEquals(1, (int) histogramMap.get("and the"));
        assertEquals(1, (int) histogramMap.get("quick blue"));
        assertEquals(1, (int) histogramMap.get("blue hare"));
        assertEquals(7, histogramMap.size());
    }

    @Test
    public void parsingStringShouldIgnoreCase() {
        Map<String, Integer> histogramMap = new HashMap<>();
        String text = "THE QUICK BROWN FOX QUICK BROWN";

        histogramParser.parseString(histogramMap, text);

        assertEquals(1, (int) histogramMap.get("the quick"));
        assertEquals(2, (int) histogramMap.get("quick brown"));
        assertEquals(1, (int) histogramMap.get("brown fox"));
        assertEquals(1, (int) histogramMap.get("fox quick"));
        assertEquals(4, histogramMap.size());
    }

    @Test
    public void parsingStringShouldJoinHyphenatedWords() {
        Map<String, Integer> histogramMap = new HashMap<>();
        String text = "the quick-brown fox quick-brown fox";

        histogramParser.parseString(histogramMap, text);

        assertEquals(1, (int) histogramMap.get("the quick-brown"));
        assertEquals(2, (int) histogramMap.get("quick-brown fox"));
        assertEquals(1, (int) histogramMap.get("fox quick-brown"));
        assertEquals(3, histogramMap.size());
    }

    @Test
    public void parsingStringShouldIgnorePunctuation() {
        Map<String, Integer> histogramMap = new HashMap<>();
        String text = "the, quick, brown. fox, quick brown.";

        histogramParser.parseString(histogramMap, text);

        assertEquals(1, (int) histogramMap.get("the quick"));
        assertEquals(2, (int) histogramMap.get("quick brown"));
        assertEquals(1, (int) histogramMap.get("brown fox"));
        assertEquals(1, (int) histogramMap.get("fox quick"));
        assertEquals(4, histogramMap.size());
    }

    @Test
    public void parsingStringShouldIgnoreExtraWhitespace() {
        Map<String, Integer> histogramMap = new HashMap<>();
        String text = "   the     quick   brown    fox        quick       brown      ";

        histogramParser.parseString(histogramMap, text);

        assertEquals(1, (int) histogramMap.get("the quick"));
        assertEquals(2, (int) histogramMap.get("quick brown"));
        assertEquals(1, (int) histogramMap.get("brown fox"));
        assertEquals(1, (int) histogramMap.get("fox quick"));
        assertEquals(4, histogramMap.size());
    }

    @Test
    public void parsingEmptyStringShouldNotHaveAnyHistogram() {
        Map<String, Integer> histogramMap = new HashMap<>();
        String text = "";

        histogramParser.parseString(histogramMap, text);

        assertEquals(0, histogramMap.size());
    }

    @Test
    public void shouldParseListsOfStringsForBigramCount() {
        List<String> linesOfText = Arrays.asList(
            "the quick brown fox and the quick blue hare",
            "the quick brown fox and the quick blue hare",
            "the quick brown fox and the quick blue hare"
        );

        Map<String, Integer> histogramMap = histogramParser.parseForBigramCount(linesOfText);

        assertEquals(6, (int) histogramMap.get("the quick"));
        assertEquals(3, (int) histogramMap.get("quick brown"));
        assertEquals(3, (int) histogramMap.get("brown fox"));
        assertEquals(3, (int) histogramMap.get("fox and"));
        assertEquals(3, (int) histogramMap.get("and the"));
        assertEquals(3, (int) histogramMap.get("quick blue"));
        assertEquals(3, (int) histogramMap.get("blue hare"));
        assertEquals(7, histogramMap.size());
    }
}
