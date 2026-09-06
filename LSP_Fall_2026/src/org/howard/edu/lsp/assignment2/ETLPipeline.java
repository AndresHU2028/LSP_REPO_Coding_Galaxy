package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ETLPipeline {

    public static void main(String[] args) {
        String inputFilePath = "data/employees.csv";
        String outputFilePath = "data/transformed_employees.csv";

        // Check if input file exists before attempting to read
        File inputFile = new File(inputFilePath);
        System.out.println("Looking for input file at: " + inputFile.getAbsolutePath());

        if (!inputFile.exists()) {
            System.err.println("ERROR: File not found at " + inputFilePath);
            System.err.println("Current Java Working Directory is: " + System.getProperty("user.dir"));
            return;
        }

        int rowsRead = 0;
        int rowsTransformed = 0;
        int rowsSkipped = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String headerLine = reader.readLine();
            if (headerLine == null) {
                System.out.println("Input file is empty.");
                return;
            }

            // Write output CSV header
            writer.write("EmployeeID,Name,Department,HoursWorked,HourlyRate,GrossPay,PayLevel,EmploymentStatus");
            writer.newLine();

            String line;
            while ((line = reader.readLine()) != null) {
                rowsRead++;

                // Skip blank lines
                if (line.trim().isEmpty()) {
                    rowsSkipped++;
                    continue;
                }

                // Split line into tokens
                String[] tokens = line.split(",");

                if (tokens.length != 5) {
                    rowsSkipped++;
                    continue;
                }

                // Step 1: Normalize fields
                String rawId = tokens[0].trim();
                String name = tokens[1].trim().toUpperCase();
                String department = tokens[2].trim();
                String rawHours = tokens[3].trim();
                String rawRate = tokens[4].trim();

                // Step 2: Validate numeric values
                int employeeId;
                double hoursWorked;
                double hourlyRate;

                try {
                    employeeId = Integer.parseInt(rawId);
                    hoursWorked = Double.parseDouble(rawHours);
                    hourlyRate = Double.parseDouble(rawRate);
                } catch (NumberFormatException e) {
                    rowsSkipped++;
                    continue;
                }

                if (hoursWorked < 0 || hourlyRate < 0) {
                    rowsSkipped++;
                    continue;
                }

                // Step 3: Calculate pay
                double grossPay;
                if (hoursWorked <= 40.0) {
                    grossPay = hoursWorked * hourlyRate;
                } else {
                    double overtimeHours = hoursWorked - 40.0;
                    grossPay = (40.0 * hourlyRate) + (overtimeHours * hourlyRate * 1.5);
                }

                // Step 4: Apply IT bonus
                if (department.equals("IT")) {
                    grossPay = grossPay * 1.05;
                }

                // Step 5: Round GrossPay
                grossPay = Math.round(grossPay * 100.0) / 100.0;

                // Step 6: Determine PayLevel
                String payLevel;
                if (grossPay < 500.00) {
                    payLevel = "Low";
                } else if (grossPay < 1000.00) {
                    payLevel = "Standard";
                } else if (grossPay < 2000.00) {
                    payLevel = "High";
                } else {
                    payLevel = "Executive";
                }

                // Step 7: Determine EmploymentStatus
                String employmentStatus = (hoursWorked < 30.0) ? "Part-Time" : "Full-Time";

                // Format outputs
                String formattedHours = String.format("%.2f", hoursWorked);
                String formattedRate = String.format("%.2f", hourlyRate);
                String formattedGross = String.format("%.2f", grossPay);

                writer.write(employeeId + "," + name + "," + department + "," + 
                             formattedHours + "," + formattedRate + "," + formattedGross + "," + 
                             payLevel + "," + employmentStatus);
                writer.newLine();

                rowsTransformed++;
            }

        } catch (IOException e) {
            System.err.println("Error reading or writing file: " + e.getMessage());
            return;
        }

        // Print Run Summary
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output file: " + outputFilePath);
    }
}
