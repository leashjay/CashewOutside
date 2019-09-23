package seng202.team3.parsing;


import org.eclipse.persistence.jaxb.*;
import org.xml.sax.SAXException;
import seng202.team3.controller.AddXMLController;
import seng202.team3.model.SalesHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

import static seng202.team3.parsing.XMLValidation.validateXMLFile;

/**
 * JAXB parser for sales record
 */
public class SalesLoader {

    /**
     * DTD for sales XML file
     */
    private static final String XML_HEADER = "\n<!DOCTYPE sales [\n" +
            "        <!ENTITY version \"V0.01 (C) CashewOutside\">\n" +
            "        <!ELEMENT sales (orders)>\n" +
            "        <!ELEMENT orders (order*)>\n" +
            "        <!ELEMENT order (orderId, orderStatus, orderCost, itemsOrdered, flagsChecked)>\n" +
            "        <!ATTLIST order\n" +
            "                dateOrdered CDATA #REQUIRED\n" +
            "                timeOrdered CDATA #REQUIRED\n" +
            "                isGF (YES|NO|UNKNOWN) \"NO\"\n" +
            "                isVegan (YES|NO|UNKNOWN) \"NO\"\n" +
            "                isVeg (YES|NO|UNKNOWN) \"UNKNOWN\"\n" +
            "                >\n" +
            "        <!ELEMENT orderId (#PCDATA)>\n" +
            "        <!ELEMENT orderStatus (#PCDATA)>\n" +
            "        <!ELEMENT orderCost (#PCDATA)>\n" +
            "        <!ELEMENT itemsOrdered (id, name)>\n" +
            "        <!ELEMENT flagsChecked (#PCDATA)>\n" +
            "        <!ELEMENT id (#PCDATA)>\n" +
            "        <!ELEMENT name (#PCDATA)>\n" +
            "        ]>";

    /** Instance of JAXB context */
    private final JAXBContext context;

    /** Set of elements to be added to XML file */
    private final ObjectGraph salesInfo;

    /**
     * Instance of sales handler to export/import data to
     */
    private SalesHandler salesHandler;

    /**
     * Constructor for SalesLoader
     */
    public SalesLoader() throws JAXBException {
        context = JAXBContext.newInstance(SalesHandler.class);

        salesInfo = JAXBHelper.getJAXBContext(context).createObjectGraph(SalesHandler.class);
        Subgraph orders = salesInfo.addSubgraph("orders");
        Subgraph order = orders.addSubgraph("order");
        order.addAttributeNodes("dateOrdered", "timeOrdered", "isGF", "isVeg", "isVegan");
        order.addSubgraph("orderId");
        order.addSubgraph("orderStatus");
        order.addSubgraph("orderCost");
        order.addSubgraph("flagsChecked");
        Subgraph itemsOrdered = order.addSubgraph("itemsOrdered");
        itemsOrdered.addSubgraph("id");
        itemsOrdered.addSubgraph("name");
    }

    /**
     * Import sales data from sales XML file
     * @param fileName path to sales XML file
     * @return Instance of SalesHandler
     */
    public SalesHandler loadSalesData(String fileName) throws JAXBException{
        try {
            validateXMLFile(fileName);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            unmarshaller.setProperty(UnmarshallerProperties.OBJECT_GRAPH, salesInfo);
            InputStream inputStream = new FileInputStream(new File(fileName));
            salesHandler = (SalesHandler) unmarshaller.unmarshal(inputStream);
        } catch (ParserConfigurationException pce) {
            AddXMLController.errorMessageList.add(pce.getMessage());
        } catch (SAXException spe) {
            AddXMLController.errorMessageList.add(spe.getMessage());
        } catch (IOException ioe) {
            AddXMLController.errorMessageList.add(ioe.getMessage());
        }
        return salesHandler;
    }


    /**
     * Export sales data to sales XML file
     *
     * @param fileName  path to sales XML file
     * @param salesLoad instance of SalesHandler to be loaded
     */
    public void exportSalesData(String fileName, SalesHandler salesLoad) throws JAXBException, IOException {
        Marshaller marshaller = context.createMarshaller();
        OutputStream outputStream = new FileOutputStream(new File(fileName));
        marshaller.setProperty(MarshallerProperties.OBJECT_GRAPH, salesInfo);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty("com.sun.xml.bind.xmlHeaders", XML_HEADER);
        marshaller.marshal(salesLoad, outputStream);
        outputStream.close();
    }
}
