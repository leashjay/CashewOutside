package seng202.team3.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class BusinessApp extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage pStage) throws IOException {
        primaryStage = pStage;
        loadMainPage();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void loadMainPage() throws IOException {
        Parent root = FXMLLoader.load(BusinessApp.class.getResource("/view/main.fxml"));
        primaryStage.setTitle("Main Business Landing Page");
        primaryStage.setScene(new Scene(root, 800, 300));
    }

    public static void loadSalesPage() throws IOException {
        Parent root = FXMLLoader.load(BusinessApp.class.getResource("/view/sales.fxml"));
        primaryStage.setTitle("Sales Page");
        primaryStage.setScene(new Scene(root, 1000, 800));
    }

}

