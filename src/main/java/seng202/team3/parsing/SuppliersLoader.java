package seng202.team3.parsing;

import seng202.team3.model.SupplierHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;


public class SuppliersLoader {
    private JAXBContext context;
    private SupplierHandler suppliersLoad;


    public SuppliersLoader() throws JAXBException {
        context = JAXBContext.newInstance(SupplierHandler.class);
    }

    public SupplierHandler loadSuppliersData(String fileName) throws Exception {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        InputStream inputStream = new FileInputStream(new File(fileName));
        suppliersLoad = (SupplierHandler) unmarshaller.unmarshal(inputStream);
        return suppliersLoad;
    }

    public void exportSupplierData(String fileName) throws Exception {
        Marshaller marshaller = context.createMarshaller();
        OutputStream outputStream = new FileOutputStream(new File(fileName));
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(suppliersLoad, outputStream);
        outputStream.close();
    }
}


//Supplier supplier = new Supplier("S123", "Jacks", "This place is hell", "75509", "jack@", "www.jack.s");
//    JAXBContext context = JAXBContext.newInstance(Suppliers.class);
//Marshaller mar = context.createMarshaller();
//mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//mar.marshal(supplier, new File("./resources/data/TESTsupplier.xml"));

