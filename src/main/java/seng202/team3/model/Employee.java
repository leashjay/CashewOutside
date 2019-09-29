package seng202.team3.model;

/**
 * Class holding data about a single employee of the foodtruck
 */
public class Employee {



    /**
     * Gets the username of the employee
     * @return the username of the employee
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the username of the employee
     * @param userName the new username of the employee
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the password of the employee
     * @return the password of the employee
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the employee
     * @param password the new password of the employee
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns if the user has admin rights
     * @return true if the user has admin rights
     */
    public boolean hasAdminRights() {
        return hasAdminRights;
    }

    /**
     * Sets whether a user has admin rights
     * @param hasAdminRights true if the user should have admin rights
     */
    public void setHasAdminRights(boolean hasAdminRights) {
        this.hasAdminRights = hasAdminRights;
    }

    private String userName;
    private String password;
    private boolean hasAdminRights;

    /**
     * Constructor for an instance of an employee
     * @param userName The employees username
     * @param password The employees password
     * @param hasAdminRights a boolean showing whether the employee has administrative rights
     */
    public Employee(String userName, String password, boolean hasAdminRights){
        this.userName = userName;
        this.hasAdminRights = hasAdminRights;
        this.password = password;
    }



}
