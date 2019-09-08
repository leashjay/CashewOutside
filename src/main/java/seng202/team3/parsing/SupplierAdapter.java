package seng202.team3.parsing;

import javafx.collections.ObservableList;
import seng202.team3.model.Supplier;
import seng202.team3.wrapper.Suppliers;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SupplierAdapter extends XmlAdapter<Suppliers, Map<String, Supplier>> {

    @Override
    public Map<String, Supplier> unmarshal(Suppliers value) throws Exception {
        Map<String, Supplier> map = new HashMap<String, Supplier>();
        for (Supplier item : value.providers)
            map.put(item.getSid(), item);
        return map;
    }

    @Override
    public Suppliers marshal(Map<String, Supplier> map) throws Exception {
        Suppliers supplierCont = new Suppliers();
        Collection<Supplier> supplierCollection = map.values();
        supplierCont.providers = supplierCollection.toArray(new Supplier[supplierCollection.size()]);
        return supplierCont;
    }


}


