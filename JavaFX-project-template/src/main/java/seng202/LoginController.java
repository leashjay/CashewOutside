package seng202;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController {
    @FXML
    private TextField usrName;

    @FXML
    private Text signInLabel;

    public LoginController() {
    }

    public void login() {
// Ensure the user has entered some text before preceeding to the next step.
        if (!usrName.getText().isEmpty()) {
// Get that text and put it inside a preformatted message.
            String loginText = String.format("Thanks for logging in, %s!", usrName.getText());
// Set the hidden Text node value to be that string.
            signInLabel.setText(loginText);
// Show the Text node.
            signInLabel.setVisible(true);
        }
    }
}
