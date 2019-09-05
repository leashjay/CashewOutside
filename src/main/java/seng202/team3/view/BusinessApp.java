package seng202.team3.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BusinessApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Main Business Landing Page");
        primaryStage.setScene(new Scene(root, 800, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

