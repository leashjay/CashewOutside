package seng202.team3.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seng202.team3.model.Business;

import java.io.IOException;


public class BusinessApp extends Application {

    private static Stage primaryStage;
    private static Business business;

    public static final String ingredientsXML = "./resources/data/Ingredients.xml";
    public static final String menuXML = "./resources/data/SampleMenu.xml";
    public static final String suppliersXML = "./resources/data/Suppliers.xml";

    static {
        try {
            business = new Business(ingredientsXML, menuXML, suppliersXML);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    public static void loadManagementPage() throws IOException {
        Parent root = FXMLLoader.load(BusinessApp.class.getResource("/view/management.fxml"));
        primaryStage.setTitle("ManagementController Page");
        primaryStage.setScene(new Scene(root, 1000, 800));
    }

    public static void loadKitchenPage() throws IOException {
        Parent root = FXMLLoader.load(BusinessApp.class.getResource("/view/kitchen.fxml"));
        primaryStage.setTitle("Kitchen Page");
        primaryStage.setScene(new Scene(root, 1000, 800));
    }

    /**
     * Gets the singular business
     * @return the singular business which can be used to access handlers.
     */
    public static Business getBusiness(){
        return business;
    }



}

