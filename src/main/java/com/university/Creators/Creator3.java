package com.university.Creators;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Creator3 {

    private String filename;
    private List<String[]> data = new ArrayList<>();
    private List<String[]> evaluations = new ArrayList<>();

    public Creator3(String filename) {
        this.filename = filename;
    }

    public void create() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    data.add(parts);
                    evaluations.add(new String[]{parts[0], parts[1], parts[2]});
                } else {
                    System.out.println("Formato de línea inválido: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> getData() {
        return data;
    }

    public List<String[]> getEvaluations() {
        return evaluations;
    }
}
