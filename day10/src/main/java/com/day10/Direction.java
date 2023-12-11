package com.day10;

public enum Direction {
    NORTH(),
    SOUTH(),
    WEST(),
    EAST();

    public Direction getOppositeDirection() {
        switch (this) {
            case NORTH -> {
                return Direction.SOUTH;
            }
            case EAST -> {
                return Direction.WEST;
            }
            case SOUTH -> {
                return Direction.NORTH;
            }
            case WEST -> {
                return Direction.EAST;
            }
            default -> throw new IllegalArgumentException("Unexpected value: " + this);
        }
    }
}
