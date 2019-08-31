package seng202.team3.parsing;

import seng202.team3.model.Inventory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class InventoryLoader {
    JAXBContext context;

    public InventoryLoader() throws JAXBException {
        context = JAXBContext.newInstance(Inventory.class);
    }

    public Inventory loadIngredientsData(String fileName) throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Inventory inventoryLoad = (Inventory) unmarshaller.unmarshal(new File(fileName));
        return inventoryLoad;
    }
}
