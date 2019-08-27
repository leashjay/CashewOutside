package seng202.team3.parsing;

import seng202.team3.model.Inventory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class InventoryLoader {
    public static void main(String[] args) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(Inventory.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Inventory inventoryLoad = (Inventory) unmarshaller.unmarshal(new File("./resources/data/Ingredients.xml"));

        System.out.println(inventoryLoad.getDesc());

    }
}
