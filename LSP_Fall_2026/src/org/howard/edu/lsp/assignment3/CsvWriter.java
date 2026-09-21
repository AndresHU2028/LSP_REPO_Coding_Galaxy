package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {
    private final String filePath;

    public CsvWriter(String filePath) {
        this.filePath = filePath;
    }

    public int writeTransformedEmployees(List<Employee> employees) throws IOException {
        int rowsTransformed = 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("EmployeeID,Name,Department,HoursWorked,HourlyRate,GrossPay,PayLevel,EmploymentStatus");
            writer.newLine();

            for (Employee emp : employees) {
                writer.write(emp.toCsvRow());
                writer.newLine();
                rowsTransformed++;
            }
        }
        return rowsTransformed;
    }
}