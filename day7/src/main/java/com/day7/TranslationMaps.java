package com.day7;

import java.util.HashMap;
import java.util.Map;

public class TranslationMaps {

    private static final TranslationMaps INSTANCE = new TranslationMaps();
    private Map<Character, Long> translationMap = new HashMap<>();

    private TranslationMaps() {
        translationMap.put('A', 14l);
        translationMap.put('K', 13l);
        translationMap.put('Q', 12l);
        translationMap.put('J', 1l);
        translationMap.put('T', 10l);
        translationMap.put('9', 9l);
        translationMap.put('8', 8l);
        translationMap.put('7', 7l);
        translationMap.put('6', 6l);
        translationMap.put('5', 5l);
        translationMap.put('4', 4l);
        translationMap.put('3', 3l);
        translationMap.put('2', 2l);
    }

    public static TranslationMaps getInstance() {
        return INSTANCE;
    }

    public Map<Character, Long> getTranslationMap() {
        return this.translationMap;
    }
}
