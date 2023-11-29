package controller.dto.customer;

/**
 * Class that means a customer in the web application
 * @author Francisco José Mellado Ortiz
 */
public class CustomerDTO {
	/**
	 * Customer username
	 */
	private String username_;
	/**
	 * Customer password
	 */
	private String password_;
	/**
	 * Customer rol (Admin/Client/None)
	 */
	private Rol rol_;
	/**
	* Name and surname of the user
	**/
	  private String name_;
	  private String surname_;
	  /**
	   * Customer email
	   */
	  private String email_;
	/**
	 *	Initialize the customer
	 */
	public CustomerDTO() {
		email_ = "";
		username_ = "";
		password_ = "";
		rol_ = Rol.None;
		name_="";
		setSurname("");
	}
	/**
	 *	Initialize the customer
	 */
	public CustomerDTO(String email, String password) {
		email_=email;
		username_ = "";
		password_ = password;
		rol_ = Rol.None;
		name_="";
		setSurname("");
	}
	/**
	 *	Initialize the customer
	 */
	public CustomerDTO(String email, String password,String login, Rol rol, String name, String surname) {
		email_=email;
		username_ = login;
		password_ = password;
		rol_ = rol;
		name_=name;
		surname_=surname;
	}
	/**
	 * Method that returns the customer username
	 * @return String
	 */
	public String getUsername() {
		return username_;
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
	 * Method that returns the customer name
	 * @return String 
	 */
	public String getName() {
		return name_;
	}
	/**
	 * Method that returns the customer name
	 * @return String surname
	 */
	public String getSurname() {
		return surname_;
	}
	/**
	 * Method that returns the customer mail
	 * @return String mail
	 */
	public String getEmail() {
		return email_;
	}
	/**
	 * Method that modify the customer username
	 * @param username New username
	 */
	public void setUsername(String username) {
		username_ = username;
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
	 * @param username New Rol
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
	/**
	 * Method that modify the customer name
	 * @param String New name
	 */
	public void setName(String name) {
		this.name_ = name;
	}
	/**
	 * Method that modify the customer surname
	 * @param String New surname
	 */
	public void setSurname(String surname) {
		this.surname_ = surname;
	}
	/**
	 * @return the mail_
	 */
}
