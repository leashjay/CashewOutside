package seng202.team3.model;

import java.util.ArrayList;

/**
 * Class to hold information about all the employees in the food truck
 */
public class EmployeeHandler {
    private ArrayList<Employee> employees;

    public EmployeeHandler(){
        createTestEmployee();
    }

    /**
     * Adds an employee to the list of employees
     * @param employee the new employee to be added
     */
    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    /**
     * Removes an employee from the list of employees
     * @param employee the employee to be removed.
     */
    public void removeEmployee(Employee employee){
        employees.remove(employee);
    }

    /**
     * Method to create a test employee to check the login system before
     * we use XML parsing for that.
     */
    public void createTestEmployee(){
        String userName = "Manager";
        String password = "password";
        boolean hasAdminAccess = true;
        Employee newEmployee = new Employee(userName, password, hasAdminAccess);
        addEmployee(newEmployee);
    }

}
