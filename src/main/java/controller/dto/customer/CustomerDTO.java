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
	 *	Empty constructor
	 */
	public CustomerDTO() {
		email_ = "";
		password_ = "";
		rol_ = Rol.None;
	}
	/**
	 *	Constructor
	 */
	public CustomerDTO(String email, String password) {
		email_=email;
		password_ = password;
		rol_ = Rol.None;
	}
	/**
	 *	Constructor
	 */
	public CustomerDTO(String email, String password, Rol rol) {
		email_=email;
		password_ = password;
		rol_ = rol;
	}
	/**
	 * Password get method
	 * @return String Customer password
	 */
	public String getPassword() {
		return password_;
	}
	/**
	 * Rol get method
	 * @return Rol Customer rol
	 */
	public Rol getRol() {
		return rol_;
	}
	/**
	 * Rol get method
	 * @return String Customer rol ("Admin"/"Client"/"None")
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
	 * Email address get method
	 * @return String Customer email address
	 */
	public String getEmail() {
		return email_;
	}
	/**
	 * Password set method
	 * @param password New password
	 */
	public void setPassword(String password) {
		password_ = password;
	}
	/**
	 * Rol set method
	 * @param rol New Rol
	 */
	public void setRol(Rol rol) {
		rol_ = rol;
	}
	/**
	 * Rol set method
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
	 * Email set method
	 * @param email New email
	 */
	public void setEmail(String email) {
		email_ = email;
	}
}
