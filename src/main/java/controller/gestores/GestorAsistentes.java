package controller.gestores;

import java.time.LocalDate;
import java.util.ArrayList;
import model.dao.AsistenteDAO;
import controller.dto.assistant.*;
/**
 * Class that manage assistants
 * @implSpec Singleton Design Pattern
 * @author Manuel García Obrero
 */

public class GestorAsistentes {
	/**
	 * Singleton private instance
	 */
	private static GestorAsistentes instance_ = null;

	/**
	 * Properties path
	 */
	private String dir_;
	/**
	 * Singleton private constructor
	 * @param dir Properties path
	 */
	private GestorAsistentes(String dir) {
		dir_=dir;
	};
	/**
	 * Instance access method
	 * @return GestorAsistentes Instance
	 */
	public static GestorAsistentes getInstance(String dir) {
		if(instance_ == null ) {
			instance_ = new GestorAsistentes(dir);
		}
		return instance_;
	}
	/**
	 * Method that returns all assistants
	 * @return ArrayList<Asistente> List of assistants
	 */
	public ArrayList<AssistantDTO> getListaAsistente() {
		AsistenteDAO db = AsistenteDAO.getInstance(dir_);
		return db.readAll();
	}
	/**
	 * Method that read an assistant using the email address
	 * @param email Assistant email
	 * @return AssistantDTO Assistant if exist an assistant with the email, null otherwise
	 */
	public AssistantDTO leerAsistente(String email) {	
		AsistenteDAO da = AsistenteDAO.getInstance(dir_);
		return da.read(email);
	}
	/**
	 * Method that add a new assistant
	 * @param AssistantDTO Assistant to add
	 * @return boolean True if the assistant has been added, false otherwise
	 */
	public boolean darAltaAsistente(AssistantDTO asistente) {	
		AsistenteDAO db = AsistenteDAO.getInstance(dir_);
		return db.create(asistente);
	}
	/**
	 * Method that modify an assistant
	 * @param id Assistant id
	 * @param email New email
  	 * @param nombre New name
  	 * @param apellidos New lastname
  	 * @param fechaNacimiento New birth date
  	 * @param especial New special requirements. True if the assistant needs special requirements, els otherwise
	 * @return boolean.
	 */
	public boolean modificarAsistente(int id, String email, String nombre, String apellidos, String fechaNacimiento, String especial){
		AsistenteDAO db = AsistenteDAO.getInstance(dir_);
		
        LocalDate fechaTrans = LocalDate.parse(fechaNacimiento);
        
		return db.update(new AssistantDTO(id, email, nombre, apellidos, fechaTrans, Boolean.parseBoolean(especial)));
	}
	/**
	 * Method that modify an assistant
	 * @param id Assistant id
	 * @param email New email
  	 * @param nombre New name
  	 * @param apellidos New lastname
  	 * @param fechaNacimiento New birth date
  	 * @param especial New special requirements. True if the assistant needs special requirements, els otherwise
	 * @return boolean.
	 */
	public boolean modificarAsistente(int id, String email, String nombre, String apellidos, LocalDate fechaNacimiento, boolean especial){
		AsistenteDAO db = AsistenteDAO.getInstance(dir_);
        
		return db.update(new AssistantDTO(id, email, nombre, apellidos, fechaNacimiento, especial));
	}
	/**
	 * Method that add a new assistant
	 * @param email Assistant email
  	 * @param nombre Assistant name
  	 * @param apellidos Assistant lastname
  	 * @param fechaNacimiento Assistant birth date
  	 * @param especial Special requirements. True if the assistant needs special requirements, els otherwise
	 * @return boolean True if the assistant has been added, false otherwise
	 */
	public boolean darAltaAsistente(String email, String nombre, String apellidos, String fechaNacimiento, String especial){
		AsistenteDAO db = AsistenteDAO.getInstance(dir_);
		AssistantDTO aux = new AssistantDTO();
		
		aux.setEmail(email);
		aux.setNombre(nombre);
		aux.setApellidos(apellidos);
		
		LocalDate fechaTrans = LocalDate.parse(fechaNacimiento);
        
		aux.setFechaNacimiento(fechaTrans);
		aux.setEspecial(Boolean.parseBoolean(especial));
		
		return db.create(aux);
	}
	/**
	 * Read all assistant
	 * @return String String with all the assistant registered
	 */
	public String listaAsistencia(){
		AsistenteDAO db = AsistenteDAO.getInstance(dir_);
		ArrayList<AssistantDTO> listaAsistente = db.readAll();	
		String infoAsistentes = "";
		
		if(listaAsistente.size() > 0) {
			for(AssistantDTO asis : listaAsistente ) {
				infoAsistentes += asis.toString();
			}
		}
		return infoAsistentes;
	}
}
