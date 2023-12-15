package com.day13;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<String> rawInput = FileReaders.readTextDocumentToList();

    private static List<Pattern> patterns = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello world!");
        translateRawInputToPatternList();
        findSymmetryForPatternList();

        System.out.println(patterns);

    }

    private static void translateRawInputToPatternList() {
        List<String> pattern = new ArrayList<>();
        for (String str : rawInput) {
            if (str.length() > 0) {
                pattern.add(str);
            } else {
                patterns.add(new Pattern(pattern));
                pattern = new ArrayList<>();
            }
        }
    }

    private static void findSymmetryForPatternList() {

        for (Pattern p : patterns) {
            p = findVerticalSymmetryForPattern(p);
            p = findHorizontalSymmetryForPattern(p);
        }
    }

    private static Pattern findVerticalSymmetryForPattern(Pattern p) {

        /*
         * so durchiterieren:
         * # #----
         * ## ##--
         * ### ###
         * --## ##
         * ----# #
         */
        return p;
    }

    private static Pattern findHorizontalSymmetryForPattern(Pattern p) {
        return p;
    }
}