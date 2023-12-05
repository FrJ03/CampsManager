package controller.gestores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.dao.AsistenteDAO;
import controller.dto.assistant.*;
/**
 * Clase que gestiona la información de los asistentes al campamento
 * @author Manuel García Obrero
 */

public class GestorAsistentes {
	/**
	 * Variable privada Singleton.
	 */
	private static GestorAsistentes instance_ = null;

	/**
	 * Constructor privado que crea una lista de asistente vacia.
	 */
	private GestorAsistentes() {};
	/**
	 * Metodo que sirve de acceso a la instancia.
	 * @return Instancia de la clase GestorAsistentes.
	 */
	public static GestorAsistentes getInstance() {
		if(instance_ == null ) {
			instance_ = new GestorAsistentes();
		}
		return instance_;
	}
	/**
	 * Método que devuelve la lista de asistentes del gestor.
	 * @return ArrayList<Asistente>.
	 */
	public ArrayList<AssistantDTO> getListaAsistente() {
		AsistenteDAO db = AsistenteDAO.getInstance();
		return db.readAll();
	}
	/**
	 * Metodo que devuelve un asistente de la base de datos
	 * @param email Email del asistente que va a ser leido.
	 * @return AssistantDTO
	 */
	public AssistantDTO leerAsistente(String email) {	
		AsistenteDAO da = AsistenteDAO.getInstance();
		return da.read(email);
	}
	/**
	 * Metodo que añade a la lista de asistente un nuevo asistente pasado como argumento, comprobando si no estaba registrado. Si estaba registrado devuelve false y si no lo estaba true
	 * @param AssistantDTO asistente que se va a añadir a la lista de asistente.
	 * @return boolean(false error, true no error).
	 */
	public boolean darAltaAsistente(AssistantDTO asistente) {	
		AsistenteDAO db = AsistenteDAO.getInstance();
		return db.create(asistente);
	}
	/**
	 * Metodo Modificar toda la información de un asistente identificado por su id.
	 * @param id Identificador único del asistente que se desea modificar.
	 * @param email Email del asistente.
  	 * @param nombre Nombre del asistente.
  	 * @param apellidos Apellidos del asistente.
  	 * @param fechaNacimiento Representa la fecha de nacimiento del asistente.
  	 * @param especial Indica si en asistente pertenece a un grupo especial (true) o no (false).
	 * @return boolean.
	 */
	public boolean modificarAsistente(int id, String email, String nombre, String apellidos, String fechaNacimiento, String especial){
		AsistenteDAO db = AsistenteDAO.getInstance();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate fechaTrans = LocalDate.parse(fechaNacimiento, formatter);
        
		return db.update(new AssistantDTO(id, email, nombre, apellidos, fechaTrans, Boolean.parseBoolean(especial)));
	}
	/**
	 * Metodo para dar de alta un asistente.
	 * @param email Email del asistente.
  	 * @param nombre Nombre del asistente.
  	 * @param apellidos Apellidos del asistente.
  	 * @param fechaNacimiento Representa la fecha de nacimiento del asistente.
  	 * @param especial Indica si en asistente pertenece a un grupo especial (true) o no (false).
	 * @return boolean.
	 */
	public boolean darAltaAsistente(String email, String nombre, String apellidos, String fechaNacimiento, String especial){
		AsistenteDAO db = AsistenteDAO.getInstance();
		AssistantDTO aux = new AssistantDTO();
		
		aux.setEmail(email);
		aux.setNombre(nombre);
		aux.setApellidos(apellidos);
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate fechaTrans = LocalDate.parse(fechaNacimiento, formatter);
        
		aux.setFechaNacimiento(fechaTrans);
		aux.setEspecial(Boolean.parseBoolean(especial));
		
		return db.create(aux);
	}
	/**
	 * Metodo que lista a los asistentes actualmente registrados.
	 * @return String String con la información de los participantes registrados o, en caso de que no haya asistentes registrados, la cadena vacía.
	 */
	public String listaAsistencia(){
		AsistenteDAO db = AsistenteDAO.getInstance();
		ArrayList<AssistantDTO> listaAsistente = db.readAll();	
		String infoAsistentes = "";
		
		if(listaAsistente.size() > 0) {
			for(AssistantDTO asis : listaAsistente ) {
				infoAsistentes = asis.toString();
			}
		}
		return infoAsistentes;
	}
}
