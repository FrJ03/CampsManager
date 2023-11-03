package business;

import java.time.LocalDate;
import java.util.ArrayList;
import data.dao.AsistenteDAO;

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
	 * Lista de asistentes disponibles.
	 */
	private ArrayList<Asistente> listaAsistente_;

	/**
	 * Constructor privado que crea una lista de asistente vacia.
	 */
	private GestorAsistentes() {
		this.listaAsistente_ = null;
	};
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
	public ArrayList<Asistente> getListaAsistente() {
		if(listaAsistente_ == null) {
			AsistenteDAO db;
			db = AsistenteDAO.getInstance();
			listaAsistente_ = db.readAll();
		}
		return listaAsistente_;
	}
	/**
	 * Método que establece la lista de asistente del gestor.
	 * @param listaAsistentes
	 * @return void.
	 */
	public void setListaAsistentes(ArrayList<Asistente> listaAsistentes){
		this.listaAsistente_ = listaAsistentes;
	}
	/**
	 * Metodo que añade a la lista de asistente un nuevo asistente pasado como argumento, comprobando si no estaba registrado. Si estaba registrado devuelve false y si no lo estaba true
	 * @param Asistente asistente que se va a añadir a la lista de asistente.
	 * @return boolean(false error, true no error).
	 */
	public boolean darAltaAsistente(Asistente asistente) {	
		listaAsistente_ = null;
		AsistenteDAO db = AsistenteDAO.getInstance();
		return db.create(asistente);
	}
	/**
	 * Metodo Modificar toda la información de un asistente identificado por su id.
	 * @param id Identificador único del asistente que se desea modificar.
  	 * @param nombre Nombre del asistente.
  	 * @param apellidos Apellidos del asistente.
  	 * @param fechaNacimiento Representa la fecha de nacimiento del asistente.
  	 * @param especial Indica si en asistente pertenece a un grupo especial (true) o no (false).
	 * @return void.
	 */
	public boolean modificarAsistente(int id, String nombre, String apellidos, LocalDate fechaNacimiento, boolean especial){
		listaAsistente_ = null;
		AsistenteDAO db = AsistenteDAO.getInstance();
		return db.update(new Asistente(id, nombre, apellidos, fechaNacimiento, especial));
	}
	/**
	 * Metodo que lista a los asistentes actualmente registrados.
	 * @return String String con la información de los participantes registrados o, en caso de que no haya asistentes registrados, la cadena vacía.
	 */
	public String listaAsistencia(){
		if(listaAsistente_ == null) {
			AsistenteDAO db;
			db = AsistenteDAO.getInstance();
			listaAsistente_ = db.readAll();
		}		
		String infoAsistentes = "";
		
		if(listaAsistente_.size() > 0) {
			for(Asistente asis : listaAsistente_ ) {
				infoAsistentes = asis.toString();
			}
		}
		return infoAsistentes;
	}
}
