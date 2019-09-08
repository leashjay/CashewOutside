package seng202.team3.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Management extends Application {
    private Stage window;
    private Parent root;


    @Override
    public void start(Stage managementStage) throws IOException {
        window = managementStage;
        root = FXMLLoader.load(getClass().getResource("management.fxml"));
        window.setTitle("Management");
        window.setScene(new Scene(root, 800, 300));
        window.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
