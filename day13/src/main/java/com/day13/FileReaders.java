package com.day13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaders {

    private FileReaders() {

    }

    public static List<String> readTextDocumentToList() {
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
