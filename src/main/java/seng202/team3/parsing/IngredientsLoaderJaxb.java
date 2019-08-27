package seng202.team3.parsing;

import seng202.team3.model.Ingredient;
import seng202.team3.model.Ingredients;
import seng202.team3.util.ThreeValueLogic;
import seng202.team3.util.UnitType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class IngredientsLoaderJaxb {

    public static void main(String[] args) throws JAXBException {

        Ingredient ingredient = new Ingredient("ABC", "ABC Food", UnitType.ML, ThreeValueLogic.UNKNOWN, ThreeValueLogic.UNKNOWN, ThreeValueLogic.UNKNOWN);

        JAXBContext context = JAXBContext.newInstance(Ingredients.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        mar.marshal(ingredient, new File("./ingredient.xml"));

        Unmarshaller unmarshaller = context.createUnmarshaller();

        Ingredients ingredientsRead = (Ingredients) unmarshaller.unmarshal(new File("./resources/data/Ingredients.xml"));

        System.out.println(ingredientsRead.desc);
        System.out.println(ingredientsRead.getIngredients().get(0).name());
        System.out.println(ingredientsRead.getIngredients().get(0).isGF());

    }
}
