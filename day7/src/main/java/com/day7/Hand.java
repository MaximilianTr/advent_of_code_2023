package com.day7;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Hand implements Comparable<Hand> {

    private String cards;
    private HandTypes type;
    private int bid;
    private long value;

    public Hand(String cards, HandTypes type, int bid, long value) {
        this.cards = cards;
        this.type = type;
        this.bid = bid;
        this.value = value;
    }

    @Override
    public int compareTo(Hand otherHand) {
        long thisvalue = this.getValue();
        long othervalue = otherHand.getValue();
        if (thisvalue > othervalue) {
            return 1;
        } else if (thisvalue < othervalue) {
            return -1;
        } else {
            return 0;
        }
    }

    // public long calculateHandValue() {
    // long value = 0l;
    // value += this.type.getValue() * 10 ^ 12;
    // Map<Character, Long> translationMap =
    // TranslationMaps.getInstance().getTranslationMap();
    // value += translationMap.get(this.cards.charAt(0)) * (10 ^ (10));
    // value += translationMap.get(this.cards.charAt(0)) * (10 ^ 8);
    // value += translationMap.get(this.cards.charAt(0)) * (10 ^ 6);
    // value += translationMap.get(this.cards.charAt(0)) * (10 ^ 4);
    // value += translationMap.get(this.cards.charAt(0)) * (10 ^ 2);
    // return value;
    // }

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    public HandTypes getType() {
        return type;
    }

    public void setType(HandTypes type) {
        this.type = type;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cards == null) ? 0 : cards.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + bid;
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
        Hand other = (Hand) obj;
        if (cards == null) {
            if (other.cards != null)
                return false;
        } else if (!cards.equals(other.cards))
            return false;
        if (type != other.type)
            return false;
        if (bid != other.bid)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Hand [cards=" + cards + ", type=" + type + ", bid=" + bid + ", value=" + value + "]";
    }

    public static class HandBuilder {

        private String cards;
        private HandTypes type;
        private int bid;

        public HandBuilder() {
            this.cards = "22222";
            this.type = HandTypes.HIGH_CARD;
            this.bid = 1;
        }

        public HandBuilder withCardsAndAutomaticHandType(String cards) {
            this.cards = cards;
            List<Character> cardsList = new ArrayList<>();
            for (char ch : cards.toCharArray()) {
                cardsList.add(ch);
            }

            Map<Character, Long> translationMap = TranslationMaps.getInstance().getTranslationMap();
            Set<Character> allCardsSet = translationMap.keySet();
            List<Long> countList = new ArrayList<>();

            for (Character c : allCardsSet) {
                countList.add(cardsList.stream().filter((e) -> e.equals(c)).count());
            }

            countList = countList.stream().sorted((a, b) -> {
                if (a > b) {
                    return -1;
                } else if (a < b) {
                    return 1;
                } else {
                    return 0;
                }
            }).toList();

            System.out.println(countList);

            if (countList.get(0) == 5) {
                this.type = HandTypes.FIVE_OF_A_KIND;
            } else if (countList.get(0) == 4) {
                this.type = HandTypes.FOUR_OF_A_KIND;
            } else if (countList.get(0) == 3 && countList.get(1) == 2) {
                this.type = HandTypes.FULL_HOUSE;
            } else if (countList.get(0) == 3) {
                this.type = HandTypes.THREE_OF_A_KIND;
            } else if (countList.get(0) == 2 && countList.get(1) == 2) {
                this.type = HandTypes.TWO_PAIR;
            } else if (countList.get(0) == 2) {
                this.type = HandTypes.ONE_PAIR;
            } else {
                this.type = HandTypes.HIGH_CARD;
            }

            return this;
        }

        public HandBuilder withBid(int bid) {
            this.bid = bid;
            return this;
        }

        public Hand build() {
            long value = 0l;
            value += this.type.getValue() * 1000000000000l;
            Map<Character, Long> translationMap = TranslationMaps.getInstance().getTranslationMap();
            value += translationMap.get(this.cards.charAt(0)) * (10000000000l);
            value += translationMap.get(this.cards.charAt(1)) * (100000000l);
            value += translationMap.get(this.cards.charAt(2)) * (1000000l);
            value += translationMap.get(this.cards.charAt(3)) * (10000l);
            value += translationMap.get(this.cards.charAt(4)) * (100l);

            System.out.println("" + (10 ^ 2));
            return new Hand(this.cards, this.type, this.bid, value);
        }

    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

}
