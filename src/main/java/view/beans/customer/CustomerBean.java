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
	 * Customer rol (Admin/Client/None)
	 */
	private Rol rol_;
	
	/**
	 *	Initialize the customer
	 */
	public CustomerBean() {
		emailUser_ = "";
		password_ = "";
		rol_ = Rol.None;
	}
	/**
	 *	Initialize the customer
	 */
	public CustomerBean(String login, String password) {
		emailUser_ = login;
		password_ = password;
		rol_ = Rol.None;
	}
	/**
	 *	Initialize the customer
	 */
	public CustomerBean(String login, String password, Rol rol) {
		emailUser_ = login;
		password_ = password;
		rol_ = rol;
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
	 * Method that returns the customer rol
	 * @return Rol
	 */
	public Rol getRol() {
		return rol_;
	}
	/**
	 * Method that returns the customer rol
	 * @return String ("Admin"/"Client"/"None")
	 */
	public String getRolName() {
		if(rol_ == Rol.Admin)
			return "Admin";
		else if(rol_ == Rol.Client)
			return "Client";
		else
			return "None";
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
	 * Method that modify the customer rol
	 * @param emailUser New Rol
	 */
	public void setRol(Rol rol) {
		rol_ = rol;
	}
	/**
	 * Method that modify the customer rol
	 * @param rol New Rol (Must be "Admin", "Client" or "None")
	 * @return boolean True if the rol value has been modified, false otherwise
	 */
	public boolean setRol(String rol) {
		if(rol.equalsIgnoreCase("admin")) {
			rol_ = Rol.Admin;
			return true;
		}
		else if(rol.equalsIgnoreCase("client")) {
			rol_ = Rol.Client;
			return true;
		}
		else if(rol.equalsIgnoreCase("none")) {
			rol_ = Rol.None;
			return true;
		}
		else
			return false;
	}
}