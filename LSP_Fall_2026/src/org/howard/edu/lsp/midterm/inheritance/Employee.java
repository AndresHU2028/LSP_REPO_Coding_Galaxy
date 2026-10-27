package org.howard.edu.lsp.midterm.inheritance;

/**
 * Abstract base class representing a generic employee, establishing shared fields
 * and the abstract method signature for calculating pay.
 * 
 * @author Andres Huart
 */
public abstract class Employee {
    protected double hoursWorked;
    protected double hourlyRate;

    /**
     * Constructs an Employee with specified hours worked and hourly rate.
     * 
     * @param hoursWorked hours worked by the employee
     * @param hourlyRate  hourly pay rate
     */
    public Employee(double hoursWorked, double hourlyRate) {
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    /**
     * Calculates and returns the total pay for the employee.
     * 
     * @return total computed pay
     */
    public abstract double calculatePay();
}