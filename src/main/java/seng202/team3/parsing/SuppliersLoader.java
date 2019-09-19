package seng202.team3.parsing;

import seng202.team3.model.SupplierHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;

import static seng202.team3.parsing.XMLValidation.validateXmlFile;

/**
 * JAXB parser for supplierHandler
 */
public class SuppliersLoader {
    /**
     * DTD for supplier XML file
     */
    private static final String XML_HEADER = "\n<!DOCTYPE suppliers [\n" +
            "        <!ENTITY version \"V0.01 (C) CashewOutside 2019\">\n" +
            "        <!ELEMENT suppliers (description, providers)>\n" +
            "        <!ELEMENT providers ((supplier*))>\n" +
            "        <!ELEMENT description (#PCDATA)>\n" +
            "        <!ELEMENT supplier (sid, name, address, phone, email?, url?)>\n" +
            "        <!ELEMENT sid (#PCDATA)>\n" +
            "        <!ELEMENT name (#PCDATA)>\n" +
            "        <!ELEMENT address (#PCDATA)>\n" +
            "        <!ELEMENT phone (#PCDATA)>\n" +
            "        <!ATTLIST supplier type (MOBILE|WORK|HOME|UNKNOWN) \"UNKNOWN\">\n" +
            "        <!ELEMENT email (#PCDATA)>\n" +
            "        <!ELEMENT url (#PCDATA)>\n" +
            "        ]>";

    /**
     * Instance of JAXB context
     */
    private JAXBContext context;

    /** SupplierHandler to be mapped */
    private SupplierHandler suppliersLoad;

    /**
     * errorMessage to be printed in output
     */
    public static ArrayList<String> errorMessage;


    /**
     * Constructor of SuppliersLoader
     */
    public SuppliersLoader() throws Exception {
        context = JAXBContext.newInstance(SupplierHandler.class);
        errorMessage = new ArrayList<>();
    }

    /**
     * Import suppliers data from suppliers XML file
     * @param fileName path to suppliers XML file
     * @return instance of SupplierHandler
     */
    public SupplierHandler loadSuppliersData(String fileName) throws JAXBException {
        errorMessage.clear();
        try {
            validateXmlFile(fileName);
        } catch (ParserConfigurationException pce) {
            errorMessage.add(pce.getMessage());
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();

        try {
            InputStream inputStream = new FileInputStream(new File(fileName));
            suppliersLoad = (SupplierHandler) unmarshaller.unmarshal(inputStream);
        } catch (FileNotFoundException fnfe) {
            errorMessage.add(fnfe.getMessage());
        }
        return suppliersLoad;
    }


    /**
     * Export suppliers data to suppliers XML file
     * @param fileName path to suppliers XML file
     */
    public void exportSupplierData(String fileName) throws Exception {
        Marshaller marshaller = context.createMarshaller();
        OutputStream outputStream = new FileOutputStream(new File(fileName));
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty("com.sun.xml.bind.xmlHeaders", XML_HEADER);
        marshaller.marshal(suppliersLoad, outputStream);
        outputStream.close();
    }

}


