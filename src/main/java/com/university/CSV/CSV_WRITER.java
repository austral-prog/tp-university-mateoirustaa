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
        try {
            int rowsNum = data.size();
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileOut));
            bw.write(String.join(delimiter, headers));
            bw.newLine();
            for (int i = 0; i < rowsNum; i++) {
                String[] row = data.get(i);
                bw.write(String.join(delimiter, row));
                if (i < rowsNum - 1) {
                    bw.newLine();
                }
            }
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
