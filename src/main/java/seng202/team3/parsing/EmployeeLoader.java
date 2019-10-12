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

/**
 * Import and export employee details
 */
public class EmployeeLoader {

    /**
     * DTD for employee XML file
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
     * EmployeeHandler to be mapped
     */
    private EmployeeHandler employeeHandler;


    /**
     * Constructor for EmployeeLoader
     */
    public EmployeeLoader() throws JAXBException {
        context = JAXBContext.newInstance(EmployeeHandler.class);
    }

    /**
     * Import employees from employee XML file
     *
     * @param fileName path to employee XML file
     * @return instance of EmployeeHandler class
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
     * Export employeeHandler to employee XML file
     *
     * @param fileName path to employee XML file
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
