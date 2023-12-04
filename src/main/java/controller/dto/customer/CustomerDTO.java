package controller.dto.customer;

/**
 * Class that means a customer in the web application
 * @author Francisco José Mellado Ortiz
 */
public class CustomerDTO {
	/**
	 * Customer password
	 */
	private String password_;
	/**
	 * Customer rol (Admin/Client/None)
	 */
	private Rol rol_;
	  /**
	   * Customer email
	   */
	  private String email_;
	/**
	 *	Initialize the customer
	 */
	public CustomerDTO() {
		email_ = "";
		password_ = "";
		rol_ = Rol.None;
	}
	/**
	 *	Initialize the customer
	 */
	public CustomerDTO(String email, String password) {
		email_=email;
		password_ = password;
		rol_ = Rol.None;
	}
	/**
	 *	Initialize the customer
	 */
	public CustomerDTO(String email, String password, Rol rol) {
		email_=email;
		password_ = password;
		rol_ = rol;
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
	 * Method that returns the customer mail
	 * @return String mail
	 */
	public String getEmail() {
		return email_;
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
}
