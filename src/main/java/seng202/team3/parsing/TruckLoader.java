package seng202.team3.parsing;

import org.xml.sax.SAXException;
import seng202.team3.controller.AddXMLController;
import seng202.team3.model.Truck;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

import static seng202.team3.parsing.XMLValidation.validateXMLFile;

public class TruckLoader {
    private static final String XML_HEADER = "\n<!DOCTYPE truck [\n" +
            "        <!ENTITY version \"V0.01 (C) CashewOutside 2019\">\n" +
            "        <!ELEMENT truck (cashAccount)>\n" +
            "        <!ELEMENT cashAccount (#PCDATA)>\n" +
            "        ]>";

    /**
     * Instance of JAXB Context for marshal/unmarshal
     */
    private JAXBContext context;

    private Truck truckLoad;

    public TruckLoader() throws JAXBException {
        context = JAXBContext.newInstance(Truck.class);
    }

    /**
     * Import menu from truck XML file
     *
     * @param fileName path to truck XML file
     * @return instance of truck class
     */
    public Truck loadTruckData(String fileName) throws JAXBException {
        try {
            validateXMLFile(fileName);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            InputStream inputStream = new FileInputStream(new File(fileName));
            truckLoad = (Truck) unmarshaller.unmarshal(inputStream);
        } catch (IOException ioe) {
            AddXMLController.errorMessageList.add("File not found in directory");
        } catch (SAXException spe) {
            AddXMLController.errorMessageList.add("File format incompatible, please choose an XML file");
        } catch (ParserConfigurationException pce) {
            AddXMLController.errorMessageList.add("XML file chosen violates embedded dtd");
        }
        return truckLoad;
    }

    /**
     * Export menu to truck XML file
     *
     * @param fileName path to menu XML file
     */
    public void exportTruckData(String fileName, Truck truckLoad) throws IOException, JAXBException {
        Marshaller marshaller = context.createMarshaller();
        OutputStream outputStream = new FileOutputStream(new File(fileName));
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty("com.sun.xml.bind.xmlHeaders", XML_HEADER);
        marshaller.marshal(truckLoad, outputStream);
        outputStream.close();
    }
}
