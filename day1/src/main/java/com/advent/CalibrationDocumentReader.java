package com.advent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CalibrationDocumentReader {
    Map<String, Integer> wordsToNumbers = Map.of("one", 1, "two", 2, "three", 3, "four", 4, "five", 5, "six", 6, "seven", 7, "eight", 8, "nine", 9);
    
    public void run() {
        System.out.println(calculateSum(convertToIntegerEachLine(mapCalibrationLineToNumbers(readTextDocumentToList()))));
    }

    private Integer calculateSum(List<Integer> intList) {
        return intList.stream().reduce(0, (a, b) -> a+b);
    }

    private List<Integer> convertToIntegerEachLine(List<String> mappedLineList) {
        List<Integer> intList = new ArrayList<>();
        intList = mappedLineList.stream().map((e)->{return Integer.parseInt(e);}).toList();
        return intList;
    }

    private List<String> mapCalibrationLineToNumbers(List<String> lineList) {
        List<String> mappedLineList = new ArrayList<>();
        mappedLineList = lineList.stream().map((e)->{
            System.out.println(e);
            //return reduceStringToFirstAndLastDigit(e);
            return reduceStringToFirstAndLastDigitIncludingWords(e);
        }).toList();
        return mappedLineList;
    }

    private String reduceStringToFirstAndLastDigit(String line) {
        char[] lineArray = line.toCharArray();
        String result = "";
        for (char c:lineArray) {
            if (c>=48 && c <=57) {
                result += c;
            }
        }

        if (result.length() > 2) {
            result = String.valueOf(result.charAt(0)) + String.valueOf(result.charAt(result.length()-1));
        } else if (result.length() == 1) {
            result += result;
        }
        System.out.println(result);
        return result;
    }

    private String reduceStringToFirstAndLastDigitIncludingWords(String line) {
        Set<String> numberWords = wordsToNumbers.keySet();
        char[] lineArray = line.toCharArray();
        String result = "";

        for (int i = 0; i< line.length(); i++) {
            if (line.charAt(i)>=48 && line.charAt(i) <=57) {
                result += line.charAt(i);
            } else {
                for (String word: numberWords) {
                    if (line.substring(i).startsWith(word)) {
                        result += wordsToNumbers.get(word);
                    }
                }
            }
        }

        if (result.length() > 2) {
            result = String.valueOf(result.charAt(0)) + String.valueOf(result.charAt(result.length()-1));
        } else if (result.length() == 1) {
            result += result;
        }
        System.out.println(result);
        return result;
    }

    private List<String> readTextDocumentToList() {
        File textFile = new File(System.getProperty("user.dir")+ "/src/main/resources/input.txt");
        List<String> lineList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(textFile))) {
            lineList = reader.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineList;        
    }
}