package com.day8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Node> nodeList = new ArrayList<>();

    public static void main(String[] args) {
        List<String> inputList = readTextDocumentToList();
        String lrInstructions = inputList.get(0);
        List<String> nodeStringList = inputList.stream().filter(e -> e.contains("=")).toList();
        nodeList = nodeStringList.stream().map(e -> new Node(e)).toList();

        List<Node> currentNodes = nodeList.stream().filter(e -> e.getName().endsWith("A")).toList();
        List<Node> tempNodes = new ArrayList<>();
        Node tempNode;
        boolean repeat = true;
        int steps = 0;
        int i = 0;

        while (repeat) {
            steps += 1;
            for (Node currentNode : currentNodes) {
                tempNode = getNextNode(lrInstructions.charAt(i), currentNode);
                if (tempNode.getName().endsWith("Z")) {
                    System.out.println(tempNode + " nach " + steps);
                } else {
                }
                tempNodes.add(getNextNode(lrInstructions.charAt(i), currentNode));
            }
            currentNodes = tempNodes;
            tempNodes = new ArrayList<>();
            if (currentNodes.stream().filter(e -> e.getName().endsWith("Z")).count() == currentNodes.size()) {
                System.out.println(steps + " Steps were needed");
                repeat = false;
            }
            i++;
            if (i >= lrInstructions.length()) {
                i = 0;
            }
        }
    }

    private static Node getNextNode(char lrInstruction, Node currentNode) {
        switch (lrInstruction) {
            case 'L' -> {
                return nodeList.stream().filter(e -> e.getName().equals(currentNode.getLeft()))
                        .toList().getFirst();
            }
            case 'R' -> {
                return nodeList.stream().filter(e -> e.getName().equals(currentNode.getRight()))
                        .toList().getFirst();
            }
            default -> {
                return null;
            }
        }
    }

    private static List<String> readTextDocumentToList() {
        File textFile = new File(System.getProperty("user.dir") + "/src/main/resources/input.txt");
        List<String> lineList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(textFile))) {
            lineList = reader.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineList;
    }

}