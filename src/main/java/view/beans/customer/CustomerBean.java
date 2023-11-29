package view.beans.customer;

import java.io.Serializable;

/**
 * Class that means a customer in the web application
 * @author Francisco José Mellado Ortiz
 */
public class CustomerBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Customer emailUser
	 */
	private String emailUser_;
	/**
	 * Customer password
	 */
	private String password_;
	/**
	 * Customer String (Admin/Client/None)
	 */
	private String rol_;
	
	/**
	 *	Initialize the customer
	 */
	public CustomerBean() {
		emailUser_ = "";
		password_ = "";
		rol_ = "";
	}
	/**
	 *	Initialize the customer
	 */
	public CustomerBean(String login, String password) {
		emailUser_ = login;
		password_ = password;
		rol_ = "";
	}
	/**
	 *	Initialize the customer
	 */
	public CustomerBean(String login, String password, String String) {
		emailUser_ = login;
		password_ = password;
		rol_ = "";
	}
	/**
	 * Method that returns the customer emailUser
	 * @return String
	 */
	public String getEmailUser() {
		return emailUser_;
	}
	/**
	 * Method that returns the customer password
	 * @return String
	 */
	public String getPassword() {
		return password_;
	}
	/**
	 * Method that returns the customer String
	 * @return String
	 */
	public String getRol() {
		return rol_;
	}
	/**
	 * Method that modify the customer emailUser
	 * @param emailUser New emailUser
	 */
	public void setEmailUser(String emailUser) {
		emailUser_ = emailUser;
	}
	/**
	 * Method that modify the customer password
	 * @param password New password
	 */
	public void setPassword(String password) {
		password_ = password;
	}
	/**
	 * Method that modify the customer String
	 * @param emailUser New String
	 */
	public void setRol(String String) {
		rol_ = String;
	}

}
