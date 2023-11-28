package view.beans.customer;

import java.io.Serializable;
/**
 * Class that means a customer in the web application
 * @author Enrique de los Reyes Montilla
 */
public class CustomerBean implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * The customer email
	 */
	private String emailUser = "";
	/**
	 * Method that returns the customerBean mail
	 * @return String mail
	 */
	public String getEmailUser() {
		return emailUser;
	}
	/**
	 * Method that set the customerBean mail
	 * @param String mail
	 */
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}
	
}