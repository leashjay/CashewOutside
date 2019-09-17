package seng202.team3.parsing;


import org.eclipse.persistence.jaxb.JAXBHelper;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.ObjectGraph;
import org.eclipse.persistence.jaxb.Subgraph;
import seng202.team3.model.SalesHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class SalesLoader {
    private JAXBContext context;

    public SalesLoader() throws Exception {
        context = JAXBContext.newInstance(SalesHandler.class);
    }


    public void exportIngredientsData(String fileName, SalesHandler salesLoad) throws Exception {
        ObjectGraph salesInfo = JAXBHelper.getJAXBContext(context).createObjectGraph(SalesHandler.class);
        Subgraph value = salesInfo.addSubgraph("orders");
        value.addAttributeNodes("dateOrdered", "timeOrdered", "isGF", "isVeg", "isVegan");
        value.addSubgraph("orderId");
        value.addSubgraph("orderStatus");
        value.addSubgraph("orderCost");
        value.addSubgraph("flagsChecked");
        Subgraph itemsOrdered = value.addSubgraph("itemsOrdered");
        itemsOrdered.addSubgraph("id");
        itemsOrdered.addSubgraph("name");

        Marshaller marshaller = context.createMarshaller();
        OutputStream outputStream = new FileOutputStream(new File(fileName));
        marshaller.setProperty(MarshallerProperties.OBJECT_GRAPH, salesInfo);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty("com.sun.xml.bind.xmlHeaders", "\n<!DOCTYPE sales [\n" +
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
                "        ]>\n");
        marshaller.marshal(salesLoad, outputStream);
        outputStream.close();
    }

//    public static void main(String[]args) {
//        SalesLoader loader = new SalesLoader();
//    }
}
