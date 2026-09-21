package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvReader {
    private final String filePath;
    private int rowsReadCount = 0;
    private int rowsSkippedCount = 0;

    public CsvReader(String filePath) {
        this.filePath = filePath;
    }

    public int getRowsReadCount() { return rowsReadCount; }
    public int getRowsSkippedCount() { return rowsSkippedCount; }

    public java.util.List<Employee> readAndExtractEmployees() throws IOException {
        java.util.List<Employee> employees = new java.util.ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String header = reader.readLine(); // Skip CSV header row

            String line;
            while ((line = reader.readLine()) != null) {
                rowsReadCount++;

                if (line.trim().isEmpty()) {
                    rowsSkippedCount++;
                    continue;
                }

                String[] rawTokens = line.split(",", -1);
                if (rawTokens.length != 5) {
                    rowsSkippedCount++;
                    continue;
                }

                try {
                    int id = Integer.parseInt(rawTokens[0].trim());
                    String name = rawTokens[1];
                    String dept = rawTokens[2];
                    double hours = Double.parseDouble(rawTokens[3].trim());
                    double rate = Double.parseDouble(rawTokens[4].trim());

                    if (hours < 0 || rate < 0) {
                        rowsSkippedCount++;
                        continue;
                    }

                    employees.add(new Employee(id, name, dept, hours, rate));
                } catch (NumberFormatException e) {
                    rowsSkippedCount++;
                }
            }
        }
        return employees;
    }
}