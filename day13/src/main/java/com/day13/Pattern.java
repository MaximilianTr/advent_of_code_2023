package com.day13;

import java.util.List;

public class Pattern {

    private List<String> pattern;
    private int horizontalSymmetricalAt;
    private int verticalSymmetricalAt;

    public Pattern() {

    }

    public Pattern(List<String> pattern) {
        this.pattern = pattern;
    }

    public List<String> getPattern() {
        return pattern;
    }

    public void setPattern(List<String> pattern) {
        this.pattern = pattern;
    }

    public int getHorizontalSymmetricalAt() {
        return horizontalSymmetricalAt;
    }

    public void setHorizontalSymmetricalAt(int horizontalSymmetricalAt) {
        this.horizontalSymmetricalAt = horizontalSymmetricalAt;
    }

    public int getVerticalSymmetricalAt() {
        return verticalSymmetricalAt;
    }

    public void setVerticalSymmetricalAt(int verticalSymmetricalAt) {
        this.verticalSymmetricalAt = verticalSymmetricalAt;
    }

    @Override
    public String toString() {
        return "Pattern [pattern=" + pattern + ", symmetricalAt=" + horizontalSymmetricalAt + "]";
    }

}
