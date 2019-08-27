package seng202.team3.parsing;

import seng202.team3.model.Menu;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class MenuLoader {

    public static void main(String[] args) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Menu.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Menu menuLoad = (Menu) unmarshaller.unmarshal(new File("./resources/data/SampleMenu.xml"));

        System.out.println(menuLoad.getTitle());
        System.out.println(menuLoad.getMenuItem().get(0).getId());

    }

}
