package seng202.team3.parsing;

import org.xml.sax.SAXException;
import seng202.team3.model.Inventory;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;

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

    private MyErrorHandler errorHandler;

    /**
     * Constructor for InventoryLoader
     */
    public InventoryLoader() throws Exception {
        context = JAXBContext.newInstance(Inventory.class);
        errorHandler = new MyErrorHandler();
    }

    public static void main(String arg[]) throws Exception {
        InventoryLoader inventoryload = new InventoryLoader();
        inventoryload.loadIngredientsData("./resources/data/testdata/Ingredients.xml");
    }

    /**
     * Import ingredients from ingredient XML file
     * @param fileName path to ingredient XML file
     * @return instance of Inventory class
     */
    public Inventory loadIngredientsData(String fileName) {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.XML_DTD_NS_URI);
            Schema schema = sf.newSchema(new File("./resources/data/dtd/ingredients.dtd"));
            unmarshaller.setSchema(schema);
            unmarshaller.setEventHandler(new ValidationEventHandler() {
                @Override
                public boolean handleEvent(ValidationEvent ve) {
                    System.out.println("Unmarshaller event handler says: " + ve.getMessage() + " (Exception: " + ve.getLinkedException() + ")");
                    return false;
                }
            });
            InputStream inputStream = new FileInputStream(new File(fileName));
            inventoryLoad = (Inventory) unmarshaller.unmarshal(inputStream);
            inputStream.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (JAXBException jaxbe) {
            jaxbe.printStackTrace();
        } catch (IOException ioe) {
            System.err.println("Problem reading file: <" + fileName + ">  Check for typos");
            System.err.println(ioe);
            System.exit(666);// a bit brutal!
        } catch (SAXException se) {
            se.printStackTrace();
        }
        return inventoryLoad;
    }

    /**
     * Export ingredients to ingredient XML file
     * @param fileName path to ingredient XML file
     */
    public void exportIngredientsData(String fileName) throws Exception {
        Marshaller marshaller = context.createMarshaller();
        errorHandler.marHandleJAXBException(marshaller);
        OutputStream outputStream = new FileOutputStream(new File(fileName));
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty("com.sun.xml.bind.xmlHeaders", XML_HEADER);
        marshaller.marshal(inventoryLoad, outputStream);
        outputStream.close();
    }

}
