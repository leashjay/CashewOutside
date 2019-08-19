package model;

/**
 * Holds information about the ingredients
 */
public class Ingredient
{

	private String name;
	private int quantity;
	private boolean isGluetenFree;
	private boolean isVegan;
	private boolean isVegetarian;

	/**
	 * A constructor for the Ingredient class
	 * @param name The name of the ingredient
	 * @param quantity The quantity of the ingredient
	 * @param isGluetenFree If the ingredient is gluetenfree or not
	 * @param isVegan If the ingredient is vegan or not
	 * @param isVegetarian If the ingredient is vegetarian or not
	 */
	public Ingredient(String name, int quantity, boolean isGluetenFree, boolean isVegan, boolean isVegetarian){
		this.name = name;
		this.quantity = quantity;
		this.isGluetenFree = isGluetenFree;
		this.isVegan = isVegan;
		this.isVegetarian = isVegetarian;
	}

	/**
	 * Changes the quantity of the ingredient
	 * @param amount The amount of the ingredient that is being added/subtracted
	 */
	public void changeQuantity(int amount) {
		quantity -= amount;
	}

}

