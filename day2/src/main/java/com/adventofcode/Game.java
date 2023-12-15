package com.adventofcode;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private int id;

    private List<Reveal> revealList;

    public Game() {
    }

    public Game(int id, List<Reveal> revealList) {
        this.id = id;
        this.revealList = revealList;
    }

    public boolean isEligibleGame(Reveal maximumCubes) {
        if (maximumCubes.getBlue() < this.revealList.stream().map(e -> e.getBlue()).max((a, b) -> a - b).get()) {
            return false;
        }
        if (maximumCubes.getGreen() < this.revealList.stream().map(e -> e.getGreen()).max((a, b) -> a - b).get()) {
            return false;
        }
        if (maximumCubes.getRed() < this.revealList.stream().map(e -> e.getRed()).max((a, b) -> a - b).get()) {
            return false;
        }
        return true;
    }

    public long calculateCubeSetPower() {
        long factorGreen = this.revealList.stream().map(e -> e.getGreen()).max((a, b) -> a - b).get();
        long factorRed = this.revealList.stream().map(e -> e.getRed()).max((a, b) -> a - b).get();
        long factorBlue = this.revealList.stream().map(e -> e.getBlue()).max((a, b) -> a - b).get();
        return factorBlue * factorGreen * factorRed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Reveal> getRevealList() {
        return revealList;
    }

    public void setRevealList(List<Reveal> revealList) {
        this.revealList = revealList;
    }

    public static class GameBuilder {
        private int id;

        private List<Reveal> revealList = new ArrayList<>();

        public Game build() {
            return new Game(id, revealList);
        }

        public GameBuilder getGameFromInputLine(String inputLine) {

            String[] firstSplit = inputLine.split(":");
            String[] gameNumber = firstSplit[0].split(" ");

            this.id = Integer.parseInt(gameNumber[1]);

            String[] reveals = firstSplit[1].split(";");
            for (String reveal : reveals) {
                this.revealList.add(new Reveal(reveal));
            }

            return this;
        }
    }

    @Override
    public String toString() {
        return "Game [id=" + id + ", revealList=" + revealList + "]";
    }

}
