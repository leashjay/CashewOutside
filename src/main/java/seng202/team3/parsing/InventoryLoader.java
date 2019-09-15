package seng202.team3.parsing;

import seng202.team3.model.Inventory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class InventoryLoader {
    private JAXBContext context;
    private Inventory inventoryLoad;

    public InventoryLoader() throws Exception {
        context = JAXBContext.newInstance(Inventory.class);
    }

    public Inventory loadIngredientsData(String fileName) throws Exception {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        InputStream inputStream = new FileInputStream(new File(fileName));
        inventoryLoad = (Inventory) unmarshaller.unmarshal(inputStream);
        return inventoryLoad;
    }

    public void exportIngredientsData(String fileName) throws Exception {
        Marshaller marshaller = context.createMarshaller();
        OutputStream outputStream = new FileOutputStream(new File(fileName));
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty("com.sun.xml.bind.xmlHeaders", "\n<!DOCTYPE inventory [\n" +
                "        <!ENTITY version \"V0.01 (C) Neville Churcher 2019\">\n" +
                "        <!ELEMENT inventory (description, ingredients)>\n" +
                "        <!ELEMENT ingredients ((ingredient*))>\n" +
                "        <!ELEMENT description (#PCDATA)>\n" +
                "        <!ELEMENT ingredient (code, name, quantity)>\n" +
                "        <!ATTLIST ingredient\n" +
                "                unit (ML|GRAM|COUNT) #REQUIRED\n" +
                "                isVeg (YES|NO|UNKNOWN) \"NO\"\n" +
                "                isVegan (YES|NO|UNKNOWN) \"NO\"\n" +
                "                isgf (YES|NO|UNKNOWN) \"UNKNOWN\"\n" +
                "                cost CDATA #REQUIRED\n" +
                "                >\n" +
                "        <!ELEMENT code (#PCDATA)>\n" +
                "        <!ELEMENT name (#PCDATA)>\n" +
                "        <!ELEMENT quantity (#PCDATA)>\n" +
                "        ]>\n");
        marshaller.marshal(inventoryLoad, outputStream);
        outputStream.close();
    }

}
