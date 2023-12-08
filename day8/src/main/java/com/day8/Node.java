package com.day8;

public class Node {
    String name;
    String left;
    String right;

    public Node(String name, String left, String right) {
        this.name = name;
        this.left = left;
        this.right = right;
    }

    public Node(String inputLine) {
        this(inputLine.split(" "));
    }

    public Node(String[] splitLine) {
        this(splitLine[0], splitLine[2].replace("(", "").replace(",", ""), splitLine[3].replace(")", ""));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node [name=" + name + ", left=" + left + ", right=" + right + "]";
    }

}
