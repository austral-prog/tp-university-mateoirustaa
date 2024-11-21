package com.university.CSV;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSV_WRITER {

    String[] headers;
    String fileOut;
    List<String[]> data;

    public CSV_WRITER(String[] headers, String fileOut, List<String[]> data) {
        this.headers = headers;
        this.fileOut = fileOut;
        this.data = data;
    }

    public void write(String delimiter) {
        if (data == null) return;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileOut))) {
            if (headers != null && headers.length > 0) {
                bw.write(String.join(delimiter, headers));
                bw.newLine();
            }

            for (int i = 0; i < data.size(); i++) {
                String[] row = data.get(i);

                if (row == null) continue;

                bw.write(String.join(delimiter, row));
                if (i < data.size() - 1) {
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
