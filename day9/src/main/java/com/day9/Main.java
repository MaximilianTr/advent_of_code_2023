package com.day9;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> lines = FileReaders.readTextDocumentToList();
        List<List<Integer>> measurementSeries = new ArrayList<>();
        for (String line : lines) {
            String[] split = line.split(" ");
            List<Integer> measurements = new ArrayList<>();
            for (String value : split) {
                measurements.add(Integer.parseInt(value));
            }
            measurementSeries.add(measurements);
        }
        extrapolateAll(measurementSeries, true);
        int sum = 0;
        for (List<Integer> series : measurementSeries) {
            sum += series.getLast();
        }
        System.out.println(sum);

        extrapolateAll(measurementSeries, false);
        int firstElementSum = 0;
        for (List<Integer> series : measurementSeries) {
            firstElementSum += series.getFirst();
        }
        System.out.println(firstElementSum);

    }

    public static List<List<Integer>> extrapolateAll(List<List<Integer>> allSeries, boolean rightSide) {
        List<List<Integer>> newAllSeries = new ArrayList<>();

        for (int i = 0; i < allSeries.size(); i++) {
            System.out.println("Nächste: (" + rightSide + ") ");
            System.out.println(allSeries.get(i));
            newAllSeries.add(getExtrapolatedList(allSeries.get(i), rightSide));
            System.out.println(allSeries.get(i));
        }

        return newAllSeries;

    }

    public static List<Integer> getExtrapolatedList(List<Integer> series, boolean rightSide) {
        if (rightSide) {
            series.add(getNextElement(series, rightSide));
        } else {
            series.addFirst(getNextElement(series, rightSide));
        }
        return series;
    }

    public static Integer getNextElement(List<Integer> series, boolean rightSide) {
        if (series.size() == series.stream().filter(e -> e == 0).toList().size()) {
            return 0;
        } else {
            List<Integer> differences = buildDifferenceList(series);
            if (rightSide) {
                return series.getLast() + getExtrapolatedList(differences, rightSide).getLast();
            } else {
                return series.getFirst() - getExtrapolatedList(differences, rightSide).getFirst();
            }
        }
    }

    public static List<Integer> buildDifferenceList(List<Integer> series) {
        List<Integer> differences = new ArrayList<>();
        for (int i = 0; i < series.size() - 1; i++) {
            differences.add(series.get(i + 1) - series.get(i));
        }
        System.out.println(differences);
        return differences;
    }

}