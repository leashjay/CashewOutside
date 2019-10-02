package seng202.team3.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import seng202.team3.model.Employee;
import seng202.team3.model.EmployeeHandler;
import seng202.team3.util.PasswordUtils;
import seng202.team3.view.BusinessApp;

import java.io.IOException;
import java.util.Optional;

import static seng202.team3.util.PasswordUtils.hashPassword;

public class LoginController {

    @FXML
    Button loginButton;

    @FXML
    Button backButton;

    @FXML
    Text errorText;

    @FXML
    TextField userNameTextField;

    @FXML
    PasswordField passwordPasswordField;

    EmployeeHandler employeeHandler = BusinessApp.getBusiness().getEmployeeHandler();

    /**
     * Method that checks the details the user has entered, shows error if incorrect and allows access to mgmt screen if correct
     * @throws IOException
     */
    public void checkUserDetails() throws IOException {
        String username = userNameTextField.getText();

        if(employeeHandler.existsEmployee(username)){

            Employee employee = employeeHandler.getEmployee(username);
            String password = passwordPasswordField.getText();
            String salt = employee.getSalt();
            String key = employee.getPassword();

            boolean isPasswordCorrect = PasswordUtils.verifyPassword(password, key, salt);
            System.out.println("Password is " + isPasswordCorrect);

            if(isPasswordCorrect) {
                errorText.setVisible(false);
                BusinessApp.loadManagementPage();
            } else {
                errorText.setText("ERROR! Incorrect password!");
                errorText.setVisible(true);
                System.out.println("EXPECTED: " + employee.getPassword());
                Optional<String> actualPassword = hashPassword(password, salt);
                System.out.println("GOT: " + actualPassword);
            }
        } else {
            errorText.setText("ERROR! User does not exist!");
            errorText.setVisible(true);
        }


    }

    public void returnToMainPage() throws IOException {
        BusinessApp.loadMainPage();
    }
}
