package model;


/**
 * <!-- begin-user-doc -->
 *
 * <!--  end-user-doc  -->
 * @generated
 */

public class Supplier
{

	private int id;


	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String email;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private String phoneNumber;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	

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
	public Supplier(int id, String name, String address, String phoneNumber,  String emailAddress, String url){

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

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public String updateNumber(String parameter) {
		// TODO implement me
		return "";
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public String updateEmail(String parameter) {
		// TODO implement me
		return "";
	}

}

