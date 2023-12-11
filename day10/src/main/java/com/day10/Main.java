package com.day10;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class Main {

    private static List<String> gameboard = new ArrayList<>();
    private static List<Pipe> flatListGameboard = new ArrayList<>();

    public static void main(String[] args) {
        gameboard = FileReaders.readTextDocumentToList();
        fillFlatListGameboard();
        findSConnections();
        resolveDistancesToS();

        System.out.println(flatListGameboard.stream().max((e, e2) -> e.getDistanceToS() - e2.getDistanceToS()));

    }

    private static void findSConnections() {
        Pipe start = findStartingPipe();
        int startIndex = flatListGameboard.indexOf(start);
        Pipe north = findPipeAtCoordinates(start.getNextCoordinatesInDirection(Direction.NORTH));
        Pipe south = findPipeAtCoordinates(start.getNextCoordinatesInDirection(Direction.SOUTH));
        Pipe west = findPipeAtCoordinates(start.getNextCoordinatesInDirection(Direction.WEST));
        Pipe east = findPipeAtCoordinates(start.getNextCoordinatesInDirection(Direction.EAST));

        if (north != null) {
            if (north.getConnectionDirections().contains(Direction.SOUTH)) {
                start.addConnectionDirection(Direction.NORTH);
            }
        }

        if (south != null) {
            if (south.getConnectionDirections().contains(Direction.NORTH)) {
                start.addConnectionDirection(Direction.SOUTH);
            }
        }

        if (west != null) {
            if (west.getConnectionDirections().contains(Direction.EAST)) {
                start.addConnectionDirection(Direction.WEST);
            }
        }

        if (east != null) {
            if (east.getConnectionDirections().contains(Direction.WEST)) {
                start.addConnectionDirection(Direction.EAST);
            }
        }

        flatListGameboard.set(startIndex, start);

    }

    private static void fillFlatListGameboard() {
        for (int i = 0; i < gameboard.size(); i++) {
            for (int k = 0; k < gameboard.get(i).length(); k++) {
                flatListGameboard.add(new Pipe.PipeBuilder().withCoordinates(k, i)
                        .withLetterAndConversionToConnections(gameboard.get(i).charAt(k)).build());
            }
        }
    }

    private static void resolveDistancesToS() {
        Pipe currentPipe = findStartingPipe();
        Set<Direction> startingDirections = new HashSet<>();
        for (Direction d : currentPipe.getConnectionDirections()) {
            startingDirections.add(d);
        }

        for (Direction startingDirection : startingDirections) {
            currentPipe = findStartingPipe();
            boolean isFollowingPath = true;
            int distanceTraveled = 1;
            Direction lastDirection = startingDirection;
            currentPipe = findPipeAtCoordinates(currentPipe.getNextCoordinatesInDirection(startingDirection));
            int currentPipeIndex = flatListGameboard.indexOf(currentPipe);
            currentPipe.setConnectedToS(true);
            currentPipe.setDistanceToS(distanceTraveled);
            flatListGameboard.set(currentPipeIndex, currentPipe);

            while (isFollowingPath) {
                try {
                    Direction newDirection = getNextDirection(currentPipe, lastDirection);
                    lastDirection = newDirection;
                    currentPipe = findPipeAtCoordinates(
                            currentPipe.getNextCoordinatesInDirection(newDirection));
                    currentPipeIndex = flatListGameboard.indexOf(currentPipe);
                    distanceTraveled += 1;
                    if (currentPipe.getLetter() != 'S') {

                        currentPipe.setConnectedToS(true);
                        if (currentPipe.getDistanceToS() == 0 || currentPipe.getDistanceToS() > distanceTraveled) {
                            currentPipe.setDistanceToS(distanceTraveled);
                        }
                        flatListGameboard.set(currentPipeIndex, currentPipe);
                    } else {
                        isFollowingPath = false;
                    }
                } catch (Exception e) {
                    e.getMessage();
                    isFollowingPath = false;
                }
            }

        }

    }

    private static Direction getNextDirection(Pipe currentPipe, Direction lastDirection) {
        return currentPipe.getConnectionDirections().stream()
                .filter(e -> !e.equals(lastDirection.getOppositeDirection())).toList().getFirst();
    }

    private static Pipe findPipeAtCoordinates(Coordinates nextCoordinatesInDirection) {
        try {
            Pipe result = flatListGameboard.stream().filter(e -> e.getCoordinates().equals(nextCoordinatesInDirection))
                    .toList()
                    .getFirst();
            System.out.println(result);
            return result;
        } catch (NoSuchElementException e) {
            System.out.println(nextCoordinatesInDirection);
            e.printStackTrace();
            return null;
        }
    }

    private static Pipe findStartingPipe() {
        return flatListGameboard.stream().filter(e -> e.getLetter() == 'S').toList().getFirst();
    }
}