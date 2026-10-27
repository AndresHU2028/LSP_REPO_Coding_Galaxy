package org.howard.edu.lsp.midterm.inheritance;

/**
 * Represents a full-time employee who receives overtime pay at 1.5 times the hourly rate
 * for hours worked beyond 40 per week.
 * 
 * @author Andres Huart
 */
public class FullTimeEmployee extends Employee {

    /**
     * Constructs a FullTimeEmployee.
     * 
     * @param hoursWorked hours worked
     * @param hourlyRate  hourly rate
     */
    public FullTimeEmployee(double hoursWorked, double hourlyRate) {
        super(hoursWorked, hourlyRate);
    }

    /**
     * Calculates pay with standard rate up to 40 hours and 1.5x overtime for hours over 40.
     * 
     * @return calculated pay
     */
    @Override
    public double calculatePay() {
        if (hoursWorked <= 40) {
            return hoursWorked * hourlyRate;
        } else {
            double regularPay = 40 * hourlyRate;
            double overtimeHours = hoursWorked - 40;
            double overtimePay = overtimeHours * (hourlyRate * 1.5);
            return regularPay + overtimePay;
        }
    }
}