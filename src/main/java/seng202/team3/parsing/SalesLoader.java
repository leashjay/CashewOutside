package seng202.team3.parsing;


import org.eclipse.persistence.jaxb.*;
import seng202.team3.model.SalesHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class SalesLoader {

    private static final String XML_HEADER = "\n<!DOCTYPE sales [\n" +
            "        <!ENTITY version \"V0.01 (C) Neville Churcher 2019\">\n" +
            "        <!ELEMENT sales (orders*)>\n" +
            "        <!ELEMENT orders (orderId, orderStatus, orderCost, itemsOrdered, flagsChecked)>\n" +
            "        <!ATTLIST orders\n" +
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
            "        ]>\n"; //TODO: Update this DTD to match the output

    private final JAXBContext context;

    private final ObjectGraph salesInfo;

    public SalesLoader() throws Exception {
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

    public SalesHandler loadSalesData(String fileName) throws Exception {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        unmarshaller.setProperty(UnmarshallerProperties.OBJECT_GRAPH, salesInfo);
        InputStream inputStream = new FileInputStream(new File(fileName));
        return (SalesHandler) unmarshaller.unmarshal(inputStream);
    }


    public void exportIngredientsData(String fileName, SalesHandler salesLoad) throws Exception {
        Marshaller marshaller = context.createMarshaller();
        OutputStream outputStream = new FileOutputStream(new File(fileName));
        marshaller.setProperty(MarshallerProperties.OBJECT_GRAPH, salesInfo);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty("com.sun.xml.bind.xmlHeaders", XML_HEADER);
        marshaller.marshal(salesLoad, outputStream);
        outputStream.close();
    }

//    public static void main(String[]args) {
//        SalesLoader loader = new SalesLoader();
//    }
}
