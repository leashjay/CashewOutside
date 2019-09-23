package seng202.team3.parsing;

import org.xml.sax.SAXException;
import seng202.team3.controller.AddXMLController;
import seng202.team3.model.Inventory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

import static seng202.team3.parsing.XMLValidation.validateXMLFile;

/**
 * JAXB parser for inventory XML file
 */

public class InventoryLoader {
    /**
     * DTD for inventory XML file
     */
    private static final String XML_HEADER = "\n<!DOCTYPE inventory [\n" +
            "        <!ENTITY version \"V0.01 (C) CashewOutside 2019\">\n" +
            "        <!ELEMENT inventory (description, ingredients)>\n" +
            "        <!ELEMENT ingredients ((ingredient*))>\n" +
            "        <!ELEMENT description (#PCDATA)>\n" +
            "        <!ELEMENT ingredient (code, name, quantity)>\n" +
            "        <!ATTLIST ingredient\n" +
            "                unit (ML|GRAM|COUNT) #REQUIRED\n" +
            "                isVeg (YES|NO|UNKNOWN) \"UNKNOWN\"\n" +
            "                isVegan (YES|NO|UNKNOWN) \"UNKNOWN\"\n" +
            "                isgf (YES|NO|UNKNOWN) \"UNKNOWN\"\n" +
            "                cost CDATA #REQUIRED\n" +
            "                >\n" +
            "        <!ELEMENT code (#PCDATA)>\n" +
            "        <!ELEMENT name (#PCDATA)>\n" +
            "        <!ELEMENT quantity (#PCDATA)>\n" +
            "        ]>\n";

    /**
     * Instance of JAXB context to execute marshal/unmarshal function
     */
    private JAXBContext context;

    /** Inventory to be mapped */
    private Inventory inventoryLoad;


    /**
     * Constructor for InventoryLoader
     */
    public InventoryLoader() throws JAXBException {
        context = JAXBContext.newInstance(Inventory.class);
    }

    /**
     * Import ingredients from ingredient XML file
     * @param fileName path to ingredient XML file
     * @return instance of Inventory class
     */
    public Inventory loadIngredientsData(String fileName) throws JAXBException{
        try {
            validateXMLFile(fileName);
        } catch (ParserConfigurationException pce) {
            AddXMLController.errorMessageList.add(pce.getMessage());
        } catch (SAXException spe) {
            AddXMLController.errorMessageList.add(spe.getMessage());
        } catch (IOException ioe) {
            AddXMLController.errorMessageList.add(ioe.getMessage());
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();

        try {
            InputStream inputStream = new FileInputStream(new File(fileName));
            inventoryLoad = (Inventory) unmarshaller.unmarshal(inputStream);
        } catch (FileNotFoundException fnfe) {
            AddXMLController.errorMessageList.add(fnfe.getMessage());
        }
        return inventoryLoad;
    }

    /**
     * Export ingredients to ingredient XML file
     * @param fileName path to ingredient XML filese.printStackTrace();
     */
    public void exportIngredientsData(String fileName) throws IOException, JAXBException {
        Marshaller marshaller = context.createMarshaller();
        OutputStream outputStream = new FileOutputStream(new File(fileName));
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty("com.sun.xml.bind.xmlHeaders", XML_HEADER);
        marshaller.marshal(inventoryLoad, outputStream);
        outputStream.close();
    }

}
