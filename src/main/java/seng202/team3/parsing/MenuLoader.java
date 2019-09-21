package seng202.team3.parsing;

import org.xml.sax.SAXException;
import seng202.team3.model.Menu;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

import static seng202.team3.parsing.XMLValidation.validateXMLFile;

/**
 * JAXB parser for menu xml file
 */
public class MenuLoader {
    /**
     * DTD for XML file
     */
    private static final String XML_HEADER = "\n<!DOCTYPE menu [\n" +
            "        <!ENTITY version \"V0.01 (C) CashewOutside 2019\">\n" +
            "        <!ELEMENT menu (title, description, items)>\n" +
            "        <!ELEMENT items (item*)>\n" +
            "        <!ATTLIST menu\n" +
            "                type CDATA #IMPLIED\n" +
            "                >\n" +
            "        <!ELEMENT title (#PCDATA)>\n" +
            "        <!ELEMENT description (#PCDATA)>\n" +
            "        <!ELEMENT item (id, name, ingredients)>\n" +
            "        <!ATTLIST item\n" +
            "                type (BEVERAGE|COCKTAIL|SNACK|ASIAN|GRILL|MAIN) #IMPLIED\n" +
            "                serves CDATA \"1\"\n" +
            "                >\n" +
            "        <!ELEMENT id (#PCDATA)>\n" +
            "        <!ELEMENT name (#PCDATA)>\n" +
            "        <!ELEMENT ingredients (entry*)>\n" +
            "        <!ELEMENT entry (key, value)>\n" +
            "        <!ELEMENT key (code, quantity)>\n" +
            "        <!ATTLIST key\n" +
            "                unit (ML|GRAM|COUNT) #REQUIRED\n" +
            "                unit (ML|GRAM|COUNT) #REQUIRED\n" +
            "                isVeg (YES|NO|UNKNOWN) \"NO\"\n" +
            "                isVegan (YES|NO|UNKNOWN) \"NO\"\n" +
            "                isgf (YES|NO|UNKNOWN) \"UNKNOWN\"\n" +
            "                cost CDATA \"0\"\n" +
            "                >\n" +
            "        <!ELEMENT code (#PCDATA)>\n" +
            "        <!ELEMENT quantity (#PCDATA)>\n" +
            "        <!ELEMENT value (#PCDATA)>\n" +
            "        ]>";

    /**
     * Instance of JAXB Context for marshal/unmarshal
     */
    private JAXBContext context;

    /**
     * Menu to be mapped
     */
    private Menu menuLoad;

    /**
     * Constructor for MenuLoader
     */
    public MenuLoader() throws JAXBException {
        context = JAXBContext.newInstance(Menu.class);
    }

    /**
     * Import menu from menu XML file
     *
     * @param fileName path to menu XML file
     * @return instance of Menu class
     */
    public Menu loadMenuData(String fileName) throws IOException, SAXException, JAXBException {
        try {
            validateXMLFile(fileName);
        } catch (ParserConfigurationException pce) {
            String errorMessage = pce.getMessage();
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        InputStream inputStream = new FileInputStream(new File(fileName));
        menuLoad = (Menu) unmarshaller.unmarshal(inputStream);
        return menuLoad;
    }

    /**
     * Export menu to menu XML file
     *
     * @param fileName path to menu XML file
     */
    public void exportMenuData(String fileName) throws IOException, JAXBException {
        Marshaller marshaller = context.createMarshaller();
        OutputStream outputStream = new FileOutputStream(new File(fileName));
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty("com.sun.xml.bind.xmlHeaders", XML_HEADER);
        marshaller.marshal(menuLoad, outputStream);
        outputStream.close();
    }

}
