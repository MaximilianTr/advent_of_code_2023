package com.day10;

import java.util.HashSet;
import java.util.Set;

public class Pipe {

    private char letter;
    private Coordinates coordinates;
    private Set<Direction> connectionDirections;

    private boolean isConnectedToS;
    private int distanceToS = 0;

    public Coordinates getNextCoordinatesInDirection(Direction direction) {
        switch (direction) {
            case NORTH -> {
                return new Coordinates(this.coordinates.getX(), this.coordinates.getY() - 1);
            }
            case EAST -> {
                return new Coordinates(this.coordinates.getX() + 1, this.coordinates.getY());
            }
            case SOUTH -> {
                return new Coordinates(this.coordinates.getX(), this.coordinates.getY() + 1);
            }
            case WEST -> {
                return new Coordinates(this.coordinates.getX() - 1, this.coordinates.getY());
            }
            default -> throw new IllegalArgumentException("Unexpected value: " + direction);

        }
    }

    public Pipe(char letter, Coordinates coordinates, Set<Direction> connectionDirections, boolean isConnectedToS,
            int distanceToS) {
        this.letter = letter;
        this.coordinates = coordinates;
        this.connectionDirections = connectionDirections;
        this.isConnectedToS = isConnectedToS;
        this.distanceToS = distanceToS;
    }

    public void addConnectionDirection(Direction direction) {
        this.connectionDirections.add(direction);
    }

    public Set<Direction> getConnectionDirections() {
        return connectionDirections;
    }

    public void setConnectionDirections(Set<Direction> connectionDirections) {
        this.connectionDirections = connectionDirections;
    }

    public boolean isConnectedToS() {
        return isConnectedToS;
    }

    public void setConnectedToS(boolean isConnectedToS) {
        this.isConnectedToS = isConnectedToS;
    }

    public int getDistanceToS() {
        return distanceToS;
    }

    public void setDistanceToS(int distanceToS) {
        this.distanceToS = distanceToS;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    @Override
    public String toString() {
        return "Pipe [letter=" + letter + ", coordinates=" + coordinates + ", connectionDirections="
                + connectionDirections + ", isConnectedToS=" + isConnectedToS + ", distanceToS=" + distanceToS + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + letter;
        result = prime * result + ((coordinates == null) ? 0 : coordinates.hashCode());
        result = prime * result + ((connectionDirections == null) ? 0 : connectionDirections.hashCode());
        result = prime * result + (isConnectedToS ? 1231 : 1237);
        result = prime * result + distanceToS;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pipe other = (Pipe) obj;
        if (letter != other.letter)
            return false;
        if (coordinates == null) {
            if (other.coordinates != null)
                return false;
        } else if (!coordinates.equals(other.coordinates))
            return false;
        if (connectionDirections == null) {
            if (other.connectionDirections != null)
                return false;
        } else if (!connectionDirections.equals(other.connectionDirections))
            return false;
        if (isConnectedToS != other.isConnectedToS)
            return false;
        if (distanceToS != other.distanceToS)
            return false;
        return true;
    }

    public static class PipeBuilder {
        private char letter;
        private Coordinates coordinates;
        private Set<Direction> connectionDirections = new HashSet<>();

        private boolean isConnectedToS = false;
        private int distanceToS = 0;

        public Pipe build() {
            return new Pipe(this.letter, this.coordinates, this.connectionDirections, this.isConnectedToS,
                    this.distanceToS);
        }

        public PipeBuilder withCoordinates(int x, int y) {
            this.coordinates = new Coordinates(x, y);
            return this;
        }

        public PipeBuilder withLetterAndConversionToConnections(char letter) {
            this.letter = letter;
            switch (letter) {
                case '|' -> {
                    connectionDirections.add(Direction.NORTH);
                    connectionDirections.add(Direction.SOUTH);
                }
                case '-' -> {
                    connectionDirections.add(Direction.EAST);
                    connectionDirections.add(Direction.WEST);
                }
                case 'L' -> {
                    connectionDirections.add(Direction.NORTH);
                    connectionDirections.add(Direction.EAST);
                }
                case 'J' -> {
                    connectionDirections.add(Direction.NORTH);
                    connectionDirections.add(Direction.WEST);
                }
                case '7' -> {
                    connectionDirections.add(Direction.WEST);
                    connectionDirections.add(Direction.SOUTH);
                }
                case 'F' -> {
                    connectionDirections.add(Direction.EAST);
                    connectionDirections.add(Direction.SOUTH);
                }
            }
            return this;
        }

    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

}
