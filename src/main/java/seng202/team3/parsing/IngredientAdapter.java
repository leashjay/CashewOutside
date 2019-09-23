package seng202.team3.parsing;

import seng202.team3.model.Ingredient;
import seng202.team3.wrapper.Ingredients;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Adapter to map Array in Ingredients class to a HashMap and vice versa
 **/

public class IngredientAdapter extends XmlAdapter<Ingredients, Map<String, Ingredient>> {

    /**
     * Converting array of ingredients into HashMap when JAXB unmarshal is called
     *
     * @param value array of ingredients from wrapper class
     * @return HashMap of ingredients
     */
    @Override
    public Map<String, Ingredient> unmarshal(Ingredients value) throws Exception {
        Map<String, Ingredient> map = new HashMap<String, Ingredient>();
        for (Ingredient ing : value.ingredients)
            map.put(ing.getCode(), ing);
        return map;
    }

    /**
     * Converting HashMap of ingredients into HashMap when JAXB marshal is called
     * @param map HashMap of ingredients
     * @return Array of ingredients
     */
    @Override
    public Ingredients marshal(Map<String, Ingredient> map) throws Exception {
        Ingredients ingCont = new Ingredients();
        Collection<Ingredient> ings = map.values();
        ingCont.ingredients = ings.toArray(new Ingredient[ings.size()]);
        return ingCont;
    }
}




