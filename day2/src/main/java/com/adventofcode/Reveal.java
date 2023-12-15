package com.adventofcode;

public class Reveal {

    private int red;
    private int green;
    private int blue;

    public Reveal() {
    }

    public Reveal(String reveal) {
        String[] cubeCounts = reveal.split(",");
        for (String cubeCount : cubeCounts) {
            String[] countAndColor = cubeCount.split(" ");
            if (countAndColor[2].equals("green")) {
                this.green = Integer.parseInt(countAndColor[1]);
            }
            if (countAndColor[2].equals("blue")) {
                this.blue = Integer.parseInt(countAndColor[1]);
            }
            if (countAndColor[2].equals("red")) {
                this.red = Integer.parseInt(countAndColor[1]);
            }
        }

    }

    public Reveal(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    @Override
    public String toString() {
        return "Reveal [red=" + red + ", green=" + green + ", blue=" + blue + "]";
    }

}
