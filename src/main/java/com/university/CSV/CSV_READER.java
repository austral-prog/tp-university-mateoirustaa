package com.university.CSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CSV_READER {

    private String file;
    private List<String[]> allRows = new ArrayList<>();

    public CSV_READER(String file) {
        this.file = file;
    }

    public void read(String delimiter) {
        File csvFile = new File(file);
        if (!csvFile.exists()) {
            throw new RuntimeException("El archivo no se encontró: " + file);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(delimiter);
                allRows.add(row);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo: " + file, e);
        }
    }

    public List<String[]> getData() {
        return allRows;
    }
}
