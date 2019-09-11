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
        marshaller.marshal(menuLoad, outputStream);
        outputStream.close();
    }

}
