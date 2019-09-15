package jUnitTests;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import seng202.team3.model.Supplier;
import seng202.team3.model.SupplierHandler;
import seng202.team3.parsing.SuppliersLoader;
import seng202.team3.util.PhoneType;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SupplierLoaderTest {
    private SuppliersLoader testLoader;
    private HashMap<String, Supplier> suppsLoaded;
    private SupplierHandler suppHandler;

    /**
     * Make sure each test starts with a clean copy of the loaded data - in case
     * previous tests have side-effects.
     */
    @Before
    public void testLoadSupplierFile() throws Exception {
        String fName = "./resources/data/Suppliers.xml";
        int numExpected = 4;
        String pathName = "";

        try {
            pathName = (new File(fName)).toURI().toURL().toString();
        } catch (IOException ioe) {
            System.err.println("Problem reading file: <" + fName + ">  Check for typos");
            System.err.println(ioe);
            System.exit(666);// a bit brutal!
        }

        testLoader = new SuppliersLoader();
        suppHandler = testLoader.loadSuppliersData(fName);
        suppsLoaded = suppHandler.getSuppliers();

        TestCase.assertEquals("All XML record should be added", numExpected, suppsLoaded.size());
    }

    @Test
    public void testSomeKnownValues() {
        Supplier s = suppsLoaded.get("s1");
        assertEquals("First supplier name check", "Countup", s.getName());
    }

    @Test
    public void testOptionalFields() {
        Supplier s = suppsLoaded.get("s2");
        assertEquals("Second supplier has no URL", Supplier.UNKNOWN_URL, s.getUrl());
    }

    @Test
    public void testPhoneTypeAttribute() {
        Supplier s = suppsLoaded.get("s2");
        assertEquals("Loaded attribute", PhoneType.MOBILE, s.getPhoneType());
    }

    @Test
    public void testExportDBtoXML() throws Exception {
        Supplier westfield = new Supplier();
        westfield.setAddress("Somewhere riccarton road");
        westfield.setName("Westfield");
        suppsLoaded.put("Westfield", westfield);
        testLoader.exportSupplierData("./resources/data/testdata/testSupplier.xml");


        suppsLoaded.clear();
        assertEquals("Reset list of suppliers", 0, suppsLoaded.size());

        suppHandler = testLoader.loadSuppliersData("./resources/data/testdata/testSupplier.xml");
        suppsLoaded = suppHandler.getSuppliers();
        assertEquals("All XML ingredients record should be added", 5, suppsLoaded.size());
    }
}

