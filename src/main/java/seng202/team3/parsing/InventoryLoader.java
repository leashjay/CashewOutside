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
        marshaller.marshal(inventoryLoad, outputStream);
        outputStream.close();
    }

}
