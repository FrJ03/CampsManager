package controller.gestores;

import controller.dto.customer.CustomerDTO;
import controller.dto.customer.Rol;
import model.dao.CustomerDAO;
/**
 * Class that manage customer information
 * @implSpec Singleton Design Pattern
 * @author Enrique de los Reyes Montilla
 */
public class GestorCustomer {
	/**
	 * Singleton private instance
	 */
	private static GestorCustomer instance_ = null;

	/**
	 * Properties path
	 */
	private String dir_;
	/**
	 * Singleton private constructor
	 * @param dir Properties path
	 */
	private GestorCustomer(String dir) {
		dir_=dir;
	};
	/**
	 * Instance access method
	 * @return GestorCustomer Instance
	 */
	public static GestorCustomer getInstance(String dir) {
		if(instance_ == null ) {
			instance_ = new GestorCustomer(dir);
		}
		return instance_;
	}
	/**
	 * Method that add a new customer
	 * @param CustomerDTO Customer to add
	 * @return boolean True if the customer has been added correctly, false otherwise
	 */
	public boolean darAltaCustomer(CustomerDTO customer) {	
		CustomerDAO db = CustomerDAO.getInstance(dir_);
		return db.create(customer);
	}
	/**
	 * Method that read a customer
	 * @param email Customer email
	 * @return CustomerDTO Customer red if exists, null otherwise
	 */
	public CustomerDTO readCustomer(String email) {	
		CustomerDAO db = CustomerDAO.getInstance(dir_);
		return db.read(email);
	}
	/**
	 * Method that modify a customer
	 * @param email Customer email
  	 * @param password Customer password
  	 * @param rol Customer rol
	 * @return boolean True if the customer has added correctly, false otherwise
	 */
	public boolean modificarCustomer(String email, String password,String rol ){
		CustomerDAO db = CustomerDAO.getInstance(dir_);
		if(!rol.equalsIgnoreCase(Rol.Admin.toString()) || !rol.equalsIgnoreCase(Rol.Client.toString()))
			return false;
		return db.update(new CustomerDTO(email, password, Rol.valueOf(rol)));
	}
}
