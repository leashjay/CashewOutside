package seng202.team3.parsing;

import seng202.team3.model.Menu;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class MenuLoader {
    private JAXBContext context;
    private Menu menuLoad;

    public MenuLoader() throws JAXBException {
        context = JAXBContext.newInstance(Menu.class);
    }

    public Menu loadMenuData(String fileName) throws Exception {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        InputStream inputStream = new FileInputStream(new File(fileName));
        menuLoad = (Menu) unmarshaller.unmarshal(inputStream);
        return menuLoad;
    }

    public void exportMenuData(String fileName) throws Exception {
        Marshaller marshaller = context.createMarshaller();
        OutputStream outputStream = new FileOutputStream(new File(fileName));
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty("com.sun.xml.bind.xmlHeaders", "\n<!DOCTYPE menu [\n" +
                "        <!ENTITY version \"V0.01 (C) Neville Churcher 2019\">\n" +
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
                "        ]>");
        marshaller.marshal(menuLoad, outputStream);
        outputStream.close();
    }

}
