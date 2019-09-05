package seng202.team3.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class BusinessApp extends Application {

    private Stage window;
    private Parent root;

    @FXML
    private Button salesBtn;

    @Override
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;
        root = FXMLLoader.load(getClass().getResource("main.fxml"));
        window.setTitle("Main Business Landing Page");
        window.setScene(new Scene(root, 800, 300));
        window.show();

    }


//    public void salesButtonPressed() throws IOException {
//        BusinessApp.primaryStage.setStage. ot = FXMLLoader.load(getClass().getResource("sales.fxml"));
//        window.setTitle("Sales Hub");
//        window.setScene(new Scene(root, 1000, 1000));
//        window.show();
//    }
//
//    salesbtn.setOnAction(new EventHandler<ActionEvent>() {
//
//        @Override
//        public void handle(ActionEvent event) {
//            salesButtonPressed();
//        }
//    }

    public static void main(String[] args) {
        launch(args);
    }

}

