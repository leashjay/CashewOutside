package model;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Holds information about the business's suppliers.
 */
public class SupplierHandler
{
	
	public ArrayList<Supplier> suppliers;


	/**
	 * Constructor for supplier handler
	 */
	public SupplierHandler(){
		super();
	}

	/**
	 * Adds a supplier to the list of suppliers
	 * @param supplierToAdd The supplier to be added to the list
	 */
	public void addSupplier(Supplier supplierToAdd) {
		suppliers.add(supplierToAdd);
	}

	/**
	 * Removes a supplier from the list of suppliers
	 * @param supplierToRemove The supplier to be removed from the list
	 */
	public void removeSupplier(Supplier supplierToRemove) {
		suppliers.remove(supplierToRemove);
	}

	public void makeOrder(Supplier parameter, ArrayList<Ingredient> parameter2) {
		// TODO implement me
	}

}

