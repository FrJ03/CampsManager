package controller.gestores;

import controller.dto.admin.AdminDTO;
import model.dao.AdminDAO;
/**
 * Class that manage administrators implemented using Singleton design pattern
 * @author Enrique de los Reyes Montilla
 */
public class GestorAdmin {
	/**
	 * Singleton private instance
	 */
	private static GestorAdmin instance_ = null;
	/**
	 * Properties path
	 */
	private String dir_;
	/**
	 * Singleton private constructor
	 * @param dir Properties path
	 */
	private GestorAdmin(String dir) {
		dir_=dir;
	};
	/**
	 * Instance access method
	 * @return GestorAdmin Instance
	 */
	public static GestorAdmin getInstance(String dir) {
		if(instance_ == null ) {
			instance_ = new GestorAdmin(dir);
		}
		return instance_;
	}
	/**
	 * Method that add an admin
	 * @param AdminDTO Admin to add.
	 * @return boolean True if the assistant has been added correctly, false otherwise
	 */
	public boolean darAltaAdmin(AdminDTO Admin) {	
		AdminDAO db = AdminDAO.getInstance(dir_);
		return db.create(Admin);
	}
	/**
	 * Method that read an admin
	 * @param email Email to search for the admin
	 * @return AdminDTO Admin if the admin exists, null otherwise
	 */
	public AdminDTO leerAdmin(String email) {	
		AdminDAO db = AdminDAO.getInstance(dir_);
		return db.read(email);
	}
	/**
	 * Method that add an admin
	 * @param email Admin email
	 * @param nombre Admin name
	 * @param apellidos Admin lastname
	 * @return boolean True if the assistant has been added correctly, false otherwise
	 */
	public boolean darAltaAdmin(String email, String nombre, String apellidos){
		AdminDAO db = AdminDAO.getInstance(dir_);
		AdminDTO aux = new AdminDTO();
		
		aux.setEmail(email);
		aux.setNombre(nombre);
		aux.setApellidos(apellidos);
		
		return db.create(aux);
	}
	/**
	 * Method that modified an admin
	 * @param id Admin id to modify
	 * @param email New email
  	 * @param nombre New name
  	 * @param apellidos New lastname
	 * @return boolean true if the admin has been modified, false otherwise
	 */
	public boolean modificarAdmin(int id, String email, String nombre, String apellidos){
		AdminDAO db = AdminDAO.getInstance(dir_);
        
		return db.update(new AdminDTO(id, email, nombre, apellidos));
	}
}
