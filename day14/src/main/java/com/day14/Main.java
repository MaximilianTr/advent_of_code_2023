package com.day14;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        List<String> fieldHorizontalLines = FileReaders.readTextDocumentToList();
        List<String> fieldVerticalLines = convertToVerticalList(fieldHorizontalLines);

        fieldVerticalLines = tiltFieldToNorth(fieldVerticalLines);
        System.out.println(calculateWeight(fieldVerticalLines));

    }

    private static int calculateWeight(List<String> fieldVerticalLines) {

        int weight = 0;

        for (String line : fieldVerticalLines) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == 'O') {
                    weight += (line.length() - i);
                }
            }
        }

        return weight;
    }

    private static List<String> tiltFieldToNorth(List<String> fieldVerticalLines) {
        for (String line : fieldVerticalLines) {
            int index = fieldVerticalLines.indexOf(line);
            line = sortLine(line);
            fieldVerticalLines.set(index, line);
        }
        return fieldVerticalLines;
    }

    private static String sortLine(String line) {
        boolean isFirstArray = true;
        String extLine = "-" + line + "-";
        String[] splitLine = extLine.split("#", 0);
        StringBuilder sb = new StringBuilder();
        if (line.startsWith("#")) {
            sb.append("#");
        }
        for (String fragment : splitLine) {
            if (fragment.startsWith("-")) {
                if (fragment.length() > 1) {
                    fragment = fragment.substring(1);
                } else {
                    continue;
                }
            }
            if (fragment.endsWith("-")) {
                if (fragment.length() > 1) {
                    fragment = fragment.substring(0, fragment.length() - 1);
                } else {
                    continue;
                }
            }
            if (!isFirstArray) {
                sb.append("#");
            }
            isFirstArray = false;
            long oCount = fragment.chars().filter(e -> e == 'O').count();
            if (fragment.equals("#")) {
                sb.append("#");
            }
            for (int i = 1; i <= oCount; i++) {
                sb.append("O");
            }
            for (int i = 1; i <= fragment.length() - oCount; i++) {
                sb.append(".");
            }
        }
        if (line.endsWith("#")) {
            sb.append("#");
        }

        System.err.println(line.length() == sb.toString().length());
        if (line.length() != sb.toString().length()) {
            System.out.println(line);
            System.out.println(sb.toString());
        }
        System.out.println("original: " + line.length() + "new: " + sb.toString().length());

        return sb.toString();
    }

    private static List<String> convertToVerticalList(List<String> fieldHorizontalLines) {

        List<String> verticalList = new ArrayList<>();
        for (int i = 0; i < fieldHorizontalLines.get(0).length(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int k = 0; k < fieldHorizontalLines.size(); k++) {
                sb.append(fieldHorizontalLines.get(k).charAt(i));
            }
            verticalList.add(sb.toString());
        }
        return verticalList;
    }
}