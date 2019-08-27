/**
 * Unit tests for Loading Supplier Data using SAX.
 */

package seng202.team3.io;

import org.junit.Before;
import org.junit.Test;
import seng202.team3.model.Ingredient;
import seng202.team3.parsing.IngredientsLoader;
import seng202.team3.util.ThreeValueLogic;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Unit tests for example DOM parsing code. Individual tests aren't usually
 * documented extensively since their namesare supposed to be informative and
 * their content is supposed to be small enough to be understood (e.g. aiming to
 * have one assertion per test).
 */
public class TestLoadIngredientsData {
    Map<String, Ingredient> ingredients;

    /**
     * Make sure each test starts with a clean copy of the loaded data - in case
     * previous tests have side-effects.
     */
    @Before
    public void testLoadMenuFile() throws MalformedURLException {
        String fName = "resources/data/Ingredients.xml";
        String pathName = (new File(fName)).toURI().toURL().toString();
        IngredientsLoader aDOMdemo = new IngredientsLoader(pathName, true, System.out);
        aDOMdemo.parseInput();
        ingredients = aDOMdemo.getIngredients();

        assertEquals("All XML ingredients records should be added", 30, ingredients.size());
    }

    @Test
    public void testSomeKnownValues() {
        String aCode = "BBun";
        assertTrue("Key in keylist", ingredients.keySet().contains(aCode));
        Ingredient i = ingredients.get(aCode);
        assertNotNull("Corresponding object in collection", i);
        assertEquals("It's a burger bun, isn't it?", "Hamburger bun", i.name());
    }

    @Test
    public void testOptionalFields() {
        String aCode = "Beetroot";
        assertTrue("Key in keylist", ingredients.keySet().contains(aCode));
        Ingredient i = ingredients.get(aCode);
        assertNotNull("Corresponding object in collection", i);
        assertEquals("It's a beetroot slice, isn't it?", "Beetroot slice", i.name());
        assertEquals("Beetroot is gluten free", ThreeValueLogic.YES, i.isGF());
    }

    @Test
    public void testDefaultAttribute() {
        String aCode = "Mayo";
        assertTrue("Key in keylist", ingredients.keySet().contains(aCode));
        Ingredient i = ingredients.get(aCode);
        assertNotNull("Corresponding object in collection", i);
        assertEquals("It's Eater plain Mayonnaise, isn't it?", "Eater plain Mayonnaise", i.name());
        assertEquals("No idea whether mayo is gluten free", ThreeValueLogic.UNKNOWN, i.isGF());
    }

}