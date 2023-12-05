package controller.gestores;

import controller.dto.customer.CustomerDTO;
import controller.dto.customer.Rol;
import model.dao.CustomerDAO;
/**
 * Clase que gestiona la información de los customer al campamento
 * @author Enrique de los Reyes Montilla
 */
public class GestorCustomer {
	/**
	 * Variable privada Singleton.
	 */
	private static GestorCustomer instance_ = null;

	/**
	 * Constructor privado que crea una lista de customer vacia.
	 */
	private GestorCustomer() {};
	/**
	 * Metodo que sirve de acceso a la instancia.
	 * @return Instancia de la clase GestorCustomer.
	 */
	public static GestorCustomer getInstance() {
		if(instance_ == null ) {
			instance_ = new GestorCustomer();
		}
		return instance_;
	}
	/**
	 * Metodo que añade a la lista de customer un nuevo customer pasado como argumento, comprobando si no estaba registrado. Si estaba registrado devuelve false y si no lo estaba true
	 * @param CustomerDTO customer que se va a añadir a la lista de customer.
	 * @return boolean(false error, true no error).
	 */
	public boolean darAltaCustomer(CustomerDTO customer) {	
		CustomerDAO db = CustomerDAO.getInstance();
		return db.create(customer);
	}
	/**
	 * Metodo que devuelve un Customer de la base de datos
	 * @param email Email del customer que va a ser leido.
	 * @return CustomerDTO
	 */
	public CustomerDTO readCustomer(String email) {	
		CustomerDAO db = CustomerDAO.getInstance();
		return db.read(email);
	}
	/**
	 * Metodo Modificar toda la información de un customer identificado por su id.
	 * @param email Identificador único del customer que se desea modificar.
  	 * @param password Password del customer.
  	 * @param rol Rol del customer.
	 * @return void.
	 */
	public boolean modificarCustomer(String email, String password,String rol ){
		CustomerDAO db = CustomerDAO.getInstance();
		return db.update(new CustomerDTO(email, password, Rol.valueOf(rol)));
	}
}
