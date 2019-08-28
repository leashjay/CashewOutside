package seng202.team3.parsing;

import seng202.team3.model.Suppliers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;


public class SuppliersLoader {

    public static void main(String[] args) throws JAXBException {

        //Supplier supplier = new Supplier("S123", "Jacks", "This place is hell", "75509", "jack@", "www.jack.s");
        JAXBContext context = JAXBContext.newInstance(Suppliers.class);
        //Marshaller mar = context.createMarshaller();
        //mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //mar.marshal(supplier, new File("./resources/data/TESTsupplier.xml"));
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Suppliers suppliersRead = (Suppliers) unmarshaller.unmarshal(new File("./resources/data/Suppliers.xml"));
        System.out.println(suppliersRead.getDesc());
        System.out.println(suppliersRead.getSuppliers().get(0).getName());
        System.out.println(suppliersRead.getSuppliers().get(0).getSid());
        System.out.println(suppliersRead.getSuppliers().get(0).getType());
    }
}

