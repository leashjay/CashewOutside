package seng202.team3.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seng202.team3.model.Business;

import java.io.IOException;


public class BusinessApp extends Application {

    /**
     * Source ingredients XML file to load data from
     */
    public static final String ingredientsXML = "./resources/data/Ingredients.xml";
    /** Source menu XML file to load data from */
    public static final String menuXML = "./resources/data/SampleMenu.xml";
    /** Source supplier XML file to load data from */
    public static final String suppliersXML = "./resources/data/Suppliers.xml";
    /**
     * Primary stage for CashewOutside application
     */
    private static Stage primaryStage;
    /**
     * Business instance to hold all model classes data in memory
     */
    private static Business business;

    /**
     * Creating a business instance when application is launched
     */
    static {
        try {
            business = new Business(ingredientsXML, menuXML, suppliersXML);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Main function to launch application
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Load main business landing page
     */
    public static void loadMainPage() throws IOException {
        Parent root = FXMLLoader.load(BusinessApp.class.getResource("/view/main.fxml"));
        primaryStage.setTitle("Cashew Outside");
        primaryStage.setScene(new Scene(root, 800, 300));
    }

    /**
     * Load sales page
     */
    public static void loadSalesPage() throws IOException {
        Parent root = FXMLLoader.load(BusinessApp.class.getResource("/view/sales.fxml"));
        primaryStage.setTitle("Sales");
        primaryStage.setScene(new Scene(root, 1000, 800));
    }

    /**
     * Load management page
     */
    public static void loadManagementPage() throws IOException {
        Parent root = FXMLLoader.load(BusinessApp.class.getResource("/view/management.fxml"));
        primaryStage.setTitle("Management");
        primaryStage.setScene(new Scene(root, 1000, 800));
    }

    /**
     * Load kitchen page
     */
    public static void loadKitchenPage() throws IOException {
        Parent root = FXMLLoader.load(BusinessApp.class.getResource("/view/kitchen.fxml"));
        primaryStage.setTitle("Kitchen");
        primaryStage.setScene(new Scene(root, 1000, 800));
    }

    /**
     * Create primary stage and set up export data feature on close request
     */
    @Override
    public void start(Stage pStage) throws IOException {
        primaryStage = pStage;
        primaryStage.setOnCloseRequest(ev -> {
            try {
                business.exportOrdersAsXML("./resources/data/testdata/exportOrders.xml");
                business.exportMenuAsXML("./resources/data/testdata/exportSampleMenu.xml");
                business.exportInventoryAsXML("./resources/data/testdata/exportIngredients.xml");
                business.exportSupplierAsXML("./resources/data/testdata/exportSuppliers.xml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        loadMainPage();
        primaryStage.show();
    }

    /**
     * Gets the singular business
     * @return the singular business which can be used to access handlers.
     */
    public static Business getBusiness(){
        return business;
    }



}

