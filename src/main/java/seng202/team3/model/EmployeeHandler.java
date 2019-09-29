package seng202.team3.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class to hold information about all the employees in the food truck
 */
public class EmployeeHandler {
    private HashMap<String, Employee> employees = new HashMap<String, Employee>();

    public EmployeeHandler(){
        createTestEmployee();
    }

    /**
     * Adds an employee to the list of employees
     * @param employee the employee to be added
     */
    public void addEmployee(Employee employee){
        employees.put(employee.getUserName(), employee);
    }

    /**
     * Removes an employee from the list of employees
     * @param employee the username of the employee to be removed.
     */
    public void removeEmployee(Employee employee){
        employees.remove(employee.getUserName());
    }

    /**
     * Returns an employee given their username
     * @param username the username of the employee you want to return
     * @return the employee that has that username
     */
    public Employee getEmployee(String username){
        return employees.get(username);
    }

    /**
     * Checks if an employee with the given username exists
     * @param username the username you are checking the existence of
     * @return true if the employee exists
     */
    public boolean existsEmployee(String username){
        return employees.containsKey(username);
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