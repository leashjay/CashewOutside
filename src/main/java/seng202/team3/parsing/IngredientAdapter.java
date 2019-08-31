package seng202.team3.parsing;

import seng202.team3.model.Ingredient;
import seng202.team3.wrapper.Ingredients;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Adapter to map Array in Ingredients class to a HashMap
 **/

public class IngredientAdapter extends XmlAdapter<Ingredients, Map<String, Ingredient>> {

    @Override
    public Map<String, Ingredient> unmarshal(Ingredients value) throws Exception {
        Map<String, Ingredient> map = new HashMap<String, Ingredient>();
        for (Ingredient ing : value.ingredients)
            map.put(ing.getCode(), ing);
        return map;
    }

    @Override
    public Ingredients marshal(Map<String, Ingredient> map) throws Exception {
        Ingredients ingCont = new Ingredients();
        Collection<Ingredient> ings = map.values();
        ingCont.ingredients = ings.toArray(new Ingredient[ings.size()]);
        return ingCont;
    }
}




