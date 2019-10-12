package seng202.team3.model;

import seng202.team3.parsing.EmployeeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.HashMap;

/**
 * Class to hold information about all the employees in the food truck
 */
@XmlRootElement(name = "employeehandler")
@XmlAccessorType(XmlAccessType.NONE)
public class EmployeeHandler {

    @XmlElement(name = "employees")
    @XmlJavaTypeAdapter(EmployeeAdapter.class)
    private HashMap<String, Employee> employees = new HashMap<String, Employee>();

    public EmployeeHandler() {
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

}
