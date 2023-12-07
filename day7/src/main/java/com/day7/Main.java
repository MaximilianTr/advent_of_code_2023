package com.day7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        List<Hand> handList = readTextDocumentToList().stream().map((text) -> {
            String[] splitString = text.split(" ");
            return new Hand.HandBuilder().withCardsAndAutomaticHandType(splitString[0])
                    .withBid(Integer.parseInt(splitString[1])).build();
        }).toList().stream().sorted().toList();

        for (Hand h : handList) {
            System.out.println(h);
        }

        long sum = 0l;

        for (int i = 1; i <= handList.size(); i++) {
            sum += handList.get(i - 1).getBid() * i;
            // System.out.println(handList.get(i - 1).getBid() + " * " + i);
        }

        System.out.println(sum);

    }

    private static List<String> readTextDocumentToList() {
        File textFile = new File(System.getProperty("user.dir") + "/src/main/resources/input.txt");
        List<String> lineList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(textFile))) {
            lineList = reader.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineList;
    }

}

/*
 * Objekt: Hand String Cards, int bid, HandType type
 * 
 * Builder pattern: withHandAndAutomaticHandType --> detects type automatically
 * 
 * comparator: *
 * types value + 10^12
 * first card value + 10^10
 * second card value + 10^8
 * third card value + 10^6
 * fourth card value + 10^4
 * fifth card value
 * 
 * alles addieren, dann voneinander abziehen
 * 
 * sonst nach types
 * 
 * 
 * map von Karten nach punkten
 * 
 * 
 */