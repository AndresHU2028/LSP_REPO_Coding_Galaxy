package org.howard.edu.lsp.assignment3;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ETLPipeline {

    public static void main(String[] args) {
        String inputPath = "data/employees.csv";
        String outputPath = "data/transformed_employees.csv";

        File inputDir = new File("data");
        if (!inputDir.exists()) {
            inputDir.mkdirs();
        }

        CsvReader reader = new CsvReader(inputPath);
        CsvWriter writer = new CsvWriter(outputPath);

        try {
            List<Employee> validEmployees = reader.readAndExtractEmployees();
            int rowsTransformed = writer.writeTransformedEmployees(validEmployees);

            System.out.println("Rows read: " + reader.getRowsReadCount());
            System.out.println("Rows transformed: " + rowsTransformed);
            System.out.println("Rows skipped: " + reader.getRowsSkippedCount());
            System.out.println("Output file: " + outputPath);

        } catch (IOException e) {
            System.err.println("Pipeline failed: " + e.getMessage());
        }
    }
}