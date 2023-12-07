package com.day7;

public enum HandTypes {
    FIVE_OF_A_KIND(6),
    FOUR_OF_A_KIND(5),
    FULL_HOUSE(4),
    THREE_OF_A_KIND(3),
    TWO_PAIR(2),
    ONE_PAIR(1),
    HIGH_CARD(0);

    private int value;

    private HandTypes(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
