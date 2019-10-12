package seng202.team3.parsing;

import org.xml.sax.SAXException;
import seng202.team3.controller.AddXMLController;
import seng202.team3.model.EmployeeHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

import static seng202.team3.parsing.XMLValidation.validateXMLFile;

public class EmployeeLoader {

    /**
     * DTD for inventory XML file
     */
    private static final String XML_HEADER = "\n<!DOCTYPE employeehandler [\n" +
            "             <!ENTITY version \"V0.01 (C) CashewOutside 2019\">\n" +
            "             <!ELEMENT employeehandler (employees)>\n" +
            "             <!ELEMENT employees (employee*)>\n" +
            "             <!ELEMENT employee (username, password, salt)>\n" +
            "             <!ELEMENT username (#PCDATA)>\n" +
            "             <!ELEMENT password (#PCDATA)>\n" +
            "             <!ELEMENT salt (#PCDATA)>\n" +
            "             <!ATTLIST employee\n" +
            "                     hasAdminRights (true|false) #REQUIRED\n" +
            "                     >\n" +
            "             ]>";

    /**
     * Instance of JAXB context to execute marshal/unmarshal function
     */
    private JAXBContext context;

    /**
     * Inventory to be mapped
     */
    private EmployeeHandler employeeHandler;


    /**
     * Constructor for InventoryLoader
     */
    public EmployeeLoader() throws JAXBException {
        context = JAXBContext.newInstance(EmployeeHandler.class);
    }

    /**
     * Import ingredients from ingredient XML file
     *
     * @param fileName path to ingredient XML file
     * @return instance of Inventory class
     */
    public EmployeeHandler loadEmployeeData(String fileName) throws JAXBException {
        try {
            validateXMLFile(fileName);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            InputStream inputStream = new FileInputStream(new File(fileName));
            employeeHandler = (EmployeeHandler) unmarshaller.unmarshal(inputStream);
        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getMessage());
            AddXMLController.errorMessageList.add(pce.getMessage());
        } catch (SAXException spe) {
            System.out.println(spe.getMessage());
            AddXMLController.errorMessageList.add(spe.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            AddXMLController.errorMessageList.add(ioe.getMessage());
        }
        return employeeHandler;
    }

    /**
     * Export ingredients to ingredient XML file
     *
     * @param fileName path to ingredient XML filese.printStackTrace();
     */
    public void exportEmployeeData(String fileName, EmployeeHandler employeeHandler) throws IOException, JAXBException {
        Marshaller marshaller = context.createMarshaller();
        OutputStream outputStream = new FileOutputStream(new File(fileName));
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty("com.sun.xml.bind.xmlHeaders", XML_HEADER);
        marshaller.marshal(employeeHandler, outputStream);
        outputStream.close();
    }

}
