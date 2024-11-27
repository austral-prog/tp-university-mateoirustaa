package com.university.CSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    private String file;
    private List<String[]> allRows = new ArrayList<>();
    private boolean printData; // Nuevo flag para controlar la impresión

    public CsvReader(String file) {
        this.file = file;
        this.printData = false; // Por defecto, no imprimir
    }

    // Permite activar o desactivar la impresión de los datos leídos
    public void setPrintData(boolean printData) {
        this.printData = printData;
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

                // Si printData es true, se imprime la línea leída
                if (printData) {
                    System.out.println(String.join(", ", row));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo: " + file, e);
        }
    }

    public List<String[]> getData() {
        return allRows;
    }
}
