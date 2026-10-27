package org.howard.edu.lsp.midterm.inheritance;

/**
 * Represents a contractor who receives a 15% premium on all hours worked.
 * 
 * @author Andres Huart
 */
public class Contractor extends Employee {

    /**
     * Constructs a Contractor.
     * 
     * @param hoursWorked hours worked
     * @param hourlyRate  hourly rate
     */
    public Contractor(double hoursWorked, double hourlyRate) {
        super(hoursWorked, hourlyRate);
    }

    /**
     * Calculates total pay with a 15% contractor premium applied.
     * 
     * @return calculated pay
     */
    @Override
    public double calculatePay() {
        return hoursWorked * hourlyRate * 1.15;
    }
}