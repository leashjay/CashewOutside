package model;

/**
 * Holds information about the food suppliers for the business
 */
public class Supplier
{
	private int id;
	private String emailAddress;
	private String name;
	private String phoneNumber;
	private String url;

	/**
	 * Constructor for the supplier class with a URL
	 * @param id The suppliers unique ID
	 * @param name The name of the supplier
	 * @param address The street address of the supplier
	 * @param phoneNumber The contact phone number of the supplier
	 * @param emailAddress The email address of the supplier
	 * @param url The suppliers website
	 */
	public Supplier(int id, String name, String address, String phoneNumber, String emailAddress, String url){
		this.id = id;
		this.name = name;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.url = url;
	}

	/**
	 * Constructor for the supplier class without URL
	 * @param id The suppliers unique ID
	 * @param name The name of the supplier
	 * @param address The street address of the supplier
	 * @param phoneNumber The contact phone number of the supplier
	 * @param emailAddress The email address of the supplier
	 */
	public Supplier(int id, String name, String address, String phoneNumber, String emailAddress){
		this.id = id;
		this.name = name;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Updates the suppliers phone number
	 * @param newNumber The new number of the supplier
	 */
	public void updatePhoneNumber(String newNumber) {
		phoneNumber = newNumber;
	}

	/**
	 * Updates the suppliers email address
	 * @param newEmailAddress The new email address of the supplier
	 */
	public void updateEmail(String newEmailAddress) {
		emailAddress = newEmailAddress;
	}

}

