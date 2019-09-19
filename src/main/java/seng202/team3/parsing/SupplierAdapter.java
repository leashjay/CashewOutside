package seng202.team3.parsing;

import seng202.team3.model.Supplier;
import seng202.team3.wrapper.Suppliers;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Adapter to map Array of suppliers to HashMap and vice versa
 */
public class SupplierAdapter extends XmlAdapter<Suppliers, Map<String, Supplier>> {

    /**
     * Converting array of suppliers to HashMap
     *
     * @param value Array of suppliers from wrapper class
     * @return HashMap of suppliers
     */
    @Override
    public Map<String, Supplier> unmarshal(Suppliers value) throws Exception {
        Map<String, Supplier> map = new HashMap<String, Supplier>();
        for (Supplier item : value.providers)
            map.put(item.getSid(), item);
        return map;
    }

    /**
     * Converting HashMap of suppliers to Array
     * @param map HashMap of suppliers
     * @return Array of suppliers
     */
    @Override
    public Suppliers marshal(Map<String, Supplier> map) throws Exception {
        Suppliers supplierCont = new Suppliers();
        Collection<Supplier> supplierCollection = map.values();
        supplierCont.providers = supplierCollection.toArray(new Supplier[supplierCollection.size()]);
        return supplierCont;
    }


}


