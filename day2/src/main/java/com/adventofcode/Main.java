package com.adventofcode;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<String> inputList = FileReaders.readTextDocumentToList();

    public static void main(String[] args) {

        List<Game> gameList = new ArrayList<>();
        for (String input : inputList) {
            gameList.add(new Game.GameBuilder().getGameFromInputLine(input).build());
        }

        System.out.println(gameList);

        Reveal availableSet = new Reveal(12, 13, 14);

        System.out.println(gameList.stream().filter(e -> e.isEligibleGame(availableSet)).map(e -> e.getId()).reduce(0,
                (a, b) -> a + b));

        System.out.println(gameList.stream().map(e -> e.calculateCubeSetPower()).reduce(0L,
                (a, b) -> a + b));

    }

}