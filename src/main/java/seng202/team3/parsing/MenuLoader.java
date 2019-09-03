package seng202.team3.parsing;

import seng202.team3.model.Menu;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class MenuLoader {

    JAXBContext context;

    public MenuLoader() throws JAXBException {
        context = JAXBContext.newInstance(Menu.class);
    }

    public Menu loadMenuData(String fileName) throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Menu menuLoad = (Menu) unmarshaller.unmarshal(new File(fileName));
        return menuLoad;
    }

}
