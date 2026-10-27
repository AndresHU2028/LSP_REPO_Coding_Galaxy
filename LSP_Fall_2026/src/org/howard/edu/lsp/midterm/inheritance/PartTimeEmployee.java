package org.howard.edu.lsp.midterm.inheritance;

/**
 * Represents a part-time employee who receives straight hourly pay for all hours worked.
 * 
 * @author Andres Huart
 */
public class PartTimeEmployee extends Employee {

    /**
     * Constructs a PartTimeEmployee.
     * 
     * @param hoursWorked hours worked
     * @param hourlyRate  hourly rate
     */
    public PartTimeEmployee(double hoursWorked, double hourlyRate) {
        super(hoursWorked, hourlyRate);
    }

    /**
     * Calculates straight hourly pay.
     * 
     * @return calculated pay
     */
    @Override
    public double calculatePay() {
        return hoursWorked * hourlyRate;
    }
}