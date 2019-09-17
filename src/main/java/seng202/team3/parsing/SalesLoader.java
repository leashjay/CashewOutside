package seng202.team3.parsing;


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
        Marshaller marshaller = context.createMarshaller();
        OutputStream outputStream = new FileOutputStream(new File(fileName));
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty("com.sun.xml.bind.xmlHeaders", "\n<!DOCTYPE sales [\n" +
                "        <!ENTITY version \"V0.01 (C) Neville Churcher 2019\">\n" +
                "        <!ELEMENT sales (orders)>\n" +
                "        <!ELEMENT orders (entry*)>\n" +
                "        <!ELEMENT entry (key, value)>\n" +
                "        <!ELEMENT key (#PCDATA)>\n" +
                "        <!ELEMENT value (orderId, orderStatus, orderCost, itemsOrdered, flagsChecked)>\n" +
                "        <!ELEMENT orderId (#PCDATA)>\n" +
                "        <!ELEMENT orderStatus (#PCDATA)>\n" +
                "        <!ELEMENT orderCost (#PCDATA)>\n" +
                "        <!ELEMENT itemsOrdered (id, name)>\n" +
                "        <!ELEMENT id (#PCDATA)>\n" +
                "        <!ELEMENT name (#PCDATA)>\n" +
                "        <!ELEMENT flagsChecked (#PCDATA)>\n" +
                "        <!ATTRIBUTE value\n" +
                "            dateOrdered (#PCDATA)\n" +
                "            timeOrdered (#PCDATA)\n" +
                "            isGFFlag (YES|NO|UNKNOWN) \"NO\"\n" +
                "            isVeganFlag (YES|NO|UNKNOWN) \"NO\"\n" +
                "            isVegFlag (YES|NO|UNKNOWN) \"UNKNOWN\"\n" +
                "        >\n" +
                "        ]>");
        marshaller.marshal(salesLoad, outputStream);
        outputStream.close();
    }

//    public static void main(String[]args) {
//        SalesLoader loader = new SalesLoader();
//    }
}
