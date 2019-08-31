package seng202.team3.parsing;

import seng202.team3.model.Suppliers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;


public class SuppliersLoader {
    JAXBContext context;

    public SuppliersLoader() throws JAXBException {
        context = JAXBContext.newInstance(Suppliers.class);
    }

    public Suppliers loadSuppliersData(String fileName) throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Suppliers suppliersLoad = (Suppliers) unmarshaller.unmarshal(new File(fileName));
        return suppliersLoad;
    }
}


//Supplier supplier = new Supplier("S123", "Jacks", "This place is hell", "75509", "jack@", "www.jack.s");
//    JAXBContext context = JAXBContext.newInstance(Suppliers.class);
//Marshaller mar = context.createMarshaller();
//mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//mar.marshal(supplier, new File("./resources/data/TESTsupplier.xml"));

