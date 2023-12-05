package controller.gestores;

import java.time.LocalDate;
import controller.dto.admin.AdminDTO;
import controller.dto.customer.CustomerDTO;
import model.dao.AdminDAO;
import model.dao.CustomerDAO;


public class GestorAdmin {
	/**
	 * Variable privada Singleton.
	 */
	private static GestorAdmin instance_ = null;

	/**
	 * Constructor privado que crea una lista de Admin vacia.
	 */
	private GestorAdmin() {};
	/**
	 * Metodo que sirve de acceso a la instancia.
	 * @return Instancia de la clase GestorAdmins.
	 */
	public static GestorAdmin getInstance() {
		if(instance_ == null ) {
			instance_ = new GestorAdmin();
		}
		return instance_;
	}
	/**
	 * Metodo que añade a la lista de Admin un nuevo Admin pasado como argumento, comprobando si no estaba registrado. Si estaba registrado devuelve false y si no lo estaba true
	 * @param AdminDTO Admin que se va a añadir a la lista de Admin.
	 * @return boolean(false error, true no error).
	 */
	public boolean darAltaAdmin(AdminDTO Admin) {	
		AdminDAO db = AdminDAO.getInstance();
		return db.create(Admin);
	}
	/**
	 * Metodo que devuelve un admin de la base de datos
	 * @param email Email del admin que va a ser leido.
	 * @return CustomerDTO
	 */
	public CustomerDTO readCustomer(String email) {	
		CustomerDAO db = CustomerDAO.getInstance();
		return db.read(email);
	}
	/**
	 * Metodo Modificar toda la información de un Admin identificado por su id.
	 * @param id Identificador único del Admin que se desea modificar.
	 * @param email Email del Admin.
  	 * @param nombre Nombre del Admin.
  	 * @param apellidos Apellidos del Admin.
  	 * @param fechaNacimiento Representa la fecha de nacimiento del Admin.
  	 * @param especial Indica si en Admin pertenece a un grupo especial (true) o no (false).
	 * @return void.
	 */
	public boolean modificarAdmin(int id, String email, String nombre, String apellidos, LocalDate fechaNacimiento, boolean especial){
		AdminDAO db = AdminDAO.getInstance();
		return db.update(new AdminDTO(id, email, nombre, apellidos, fechaNacimiento, especial));
	}
}
