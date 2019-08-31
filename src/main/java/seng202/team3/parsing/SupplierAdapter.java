package seng202.team3.parsing;

import seng202.team3.model.Supplier;
import seng202.team3.wrapper.Providers;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SupplierAdapter extends XmlAdapter<Providers, Map<String, Supplier>> {

    @Override
    public Map<String, Supplier> unmarshal(Providers value) throws Exception {
        Map<String, Supplier> map = new HashMap<String, Supplier>();
        for (Supplier item : value.suppliers)
            map.put(item.getSid(), item);
        return map;
    }

    @Override
    public Providers marshal(Map<String, Supplier> map) throws Exception {
        Providers supplierCont = new Providers();
        Collection<Supplier> supplierCollection = map.values();
        supplierCont.suppliers = supplierCollection.toArray(new Supplier[supplierCollection.size()]);
        return supplierCont;
    }
}


