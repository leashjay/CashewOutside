package seng202.team3.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seng202.team3.model.Business;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


public class BusinessApp extends Application {

    /**
     * Source ingredients XML file to load data from
     */
    public static String ingredientsXML = "./src/main/resources/data/realdata/Ingredients.xml";

    /** Source menu XML file to load data from */
    public static String menuXML = "./src/main/resources/data/realdata/SampleMenu.xml";

    /** Source supplier XML file to load data from */
    public static String suppliersXML = "./src/main/resources/data/realdata/Suppliers.xml";

    /** Source sales XML file to load data from */
    public static String salesXML = "./src/main/resources/data/realdata/Sales.xml";

    /** Source employee XML file to load data from */
    public static String employeeXML = "./src/main/resources/data/realdata/Employees.xml";

    /**
     * Source truck XML file to load data from
     */
    public static String truckXML = "./src/main/resources/data/realdata/Truck.xml";

    /** Primary stage for CashewOutside application */
    private static Stage primaryStage;

    /** Business instance to hold all model classes data in memory */
    private static Business business;

    /**
     * Date of business operation (usually today)
     */
    private static LocalDate dateOperation;

    /**
     * XML path prefix
     */
    private static String pathPrefix = "./src/main/resources/data/realdata/";

    private static ArrayList<File> fileArrayList;

    /**
     * Creating a business instance when application is launched
     */
    static {
        try {
            checkBusinessDay();
            business = new Business(ingredientsXML, menuXML, suppliersXML, salesXML, employeeXML, truckXML);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if application is able to load today's XML file
     * if not application will load yesterday's XML file
     * Backup plan to load default XML file
     */
    public static void checkBusinessDay() {
        dateOperation = LocalDate.now();
        alterXMLPath();
        File ingredientsFile = new File(ingredientsXML);
        File menuFile = new File(menuXML);
        File salesFile = new File(salesXML);
        File suppliersFile = new File(suppliersXML);
        File employeeFile = new File(employeeXML);
        File truckFile = new File(truckXML);
        if (!ingredientsFile.exists() || !menuFile.exists() || !salesFile.exists() || !suppliersFile.exists() || !employeeFile.exists() || !truckFile.exists()) {
            dateOperation = LocalDate.now().minusDays(1);
            alterXMLPath();
            ingredientsFile = new File(ingredientsXML);
            menuFile = new File(menuXML);
            salesFile = new File(salesXML);
            suppliersFile = new File(suppliersXML);
            employeeFile = new File(employeeXML);
            truckFile = new File(truckXML);
            if (!ingredientsFile.exists() || !menuFile.exists() || !salesFile.exists() || !suppliersFile.exists() || !employeeFile.exists() || !truckFile.exists()) {
                alterXMLPathToOriginal();
            }
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
        primaryStage.setScene(new Scene(root, 1000, 800));
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

    public static void loadLoginPage() throws IOException {
        Parent root = FXMLLoader.load(BusinessApp.class.getResource("/view/login.fxml"));
        primaryStage.setTitle("Login");
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
     * alter XML path to XML filename with the current date of operation
     */
    public static void alterXMLPath() {
        ingredientsXML = pathPrefix + "Ingredients" + dateOperation.toString() + ".xml";
        menuXML = pathPrefix + "SampleMenu" + dateOperation.toString() + ".xml";
        suppliersXML = pathPrefix + "Suppliers" + dateOperation.toString() + ".xml";
        salesXML = pathPrefix + "Sales" + dateOperation.toString() + ".xml";
        employeeXML = pathPrefix + "Employee" + dateOperation.toString() + ".xml";
        truckXML = pathPrefix + "Truck" + dateOperation.toString() + ".xml";
    }

    /**
     * alter XML path to default XML filename
     */
    public static void alterXMLPathToOriginal() {
        ingredientsXML = pathPrefix + "Ingredients" + ".xml";
        menuXML = pathPrefix + "SampleMenu" + ".xml";
        suppliersXML = pathPrefix + "Suppliers" + ".xml";
        salesXML = pathPrefix + "Sales" + ".xml";
        employeeXML = pathPrefix + "Employees" + ".xml";
        truckXML = pathPrefix + "Truck" + ".xml";
    }

    /**
     * Create primary stage and set up export data feature on close request
     */
    @Override
    public void start(Stage pStage) throws IOException {
        primaryStage = pStage;
        primaryStage.setOnCloseRequest(ev -> {
            dateOperation = LocalDate.now();
            alterXMLPath();
            try {
                business.exportOrdersAsXML(salesXML);
                business.exportMenuAsXML(menuXML);
                business.exportInventoryAsXML(ingredientsXML);
                business.exportSupplierAsXML(suppliersXML);
                business.exportEmployeeAsXML(employeeXML);
                business.exportTruckAsXML(truckXML);
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

