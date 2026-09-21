package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

public class Employee {
    private final int employeeId;
    private final String name;
    private final String department;
    private final double hoursWorked;
    private final double hourlyRate;

    public Employee(int employeeId, String name, String department, double hoursWorked, double hourlyRate) {
        this.employeeId = employeeId;
        this.name = name.trim().toUpperCase(Locale.ROOT);
        this.department = department.trim();
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    public int getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getHoursWorked() { return hoursWorked; }
    public double getHourlyRate() { return hourlyRate; }

    public double calculateGrossPay() {
        double unroundedPay;
        if (hoursWorked <= 40.00) {
            unroundedPay = hoursWorked * hourlyRate;
        } else {
            double overtimeHours = hoursWorked - 40.00;
            unroundedPay = (40.00 * hourlyRate) + (overtimeHours * hourlyRate * 1.5);
        }

        if ("IT".equals(department)) {
            unroundedPay *= 1.05;
        }

        BigDecimal grossPayBD = new BigDecimal(Double.toString(unroundedPay))
                .setScale(2, RoundingMode.HALF_UP);
        return grossPayBD.doubleValue();
    }

    public String getPayLevel() {
        double grossPay = calculateGrossPay();
        if (grossPay < 500.00) return "Low";
        if (grossPay < 1000.00) return "Standard";
        if (grossPay < 2000.00) return "High";
        return "Executive";
    }

    public String getEmploymentStatus() {
        return (hoursWorked < 30.00) ? "Part-Time" : "Full-Time";
    }

    public String toCsvRow() {
        return String.format(
            Locale.US,
            "%d,%s,%s,%.2f,%.2f,%.2f,%s,%s",
            employeeId,
            name,
            department,
            hoursWorked,
            hourlyRate,
            calculateGrossPay(),
            getPayLevel(),
            getEmploymentStatus()
        );
    }
}