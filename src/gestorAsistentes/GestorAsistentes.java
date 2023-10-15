package gestorAsistentes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import asistente.Asistente;

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
		this.listaAsistente_ = new ArrayList<Asistente>();
		//this.listaAsistente_.clear();
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
		if(this.listaAsistente_.size() > 0)
			asistente.setId(this.listaAsistente_.get(this.listaAsistente_.size()).getId() + 1);
		else
			asistente.setId(1);
		this.listaAsistente_.add(asistente);
		Collections.sort(this.listaAsistente_);
		return true;//Se ha registrado correctamente ya que no estaba inscrito
	}
	/**
	 * Metodo Modificar toda la información de un asistente identificado por su id.
	 * @param id_antiguo Identificador antiguo único del asistente.
	 * @param id_nuevo Identificador nuevo único del asistente.
      	 * @param nombre Nombre del asistente.
      	 * @param apellidos Apellidos del asistente.
      	 * @param fechaNacimiento Representa la fecha de nacimiento del asistente.
      	 * @param especial Indica si en asistente pertenece a un grupo especial (true) o no (false).
	 * @return void.
	 */
	public void modificarAsistente(int id_antiguo, int id_nuevo, String nombre, String apellidos, Date fechaNacimiento,boolean especial){
		for(int aux=0;aux<listaAsistente_.size();aux++) {
			if(listaAsistente_.get(aux).getId()==id_antiguo) {
				listaAsistente_.get(aux).setId(id_nuevo);
				listaAsistente_.get(aux).setNombre(nombre);
				listaAsistente_.get(aux).setApellidos(apellidos);
				listaAsistente_.get(aux).setFechaNacimiento(fechaNacimiento);
				listaAsistente_.get(aux).setEspecial(especial);
				aux=listaAsistente_.size();
			}
		}
	}
	/**
	 * Metodo que lista a los asistentes actualmente registrados.
	 * @return String String con la información de los participantes registrados.
	 * @throws Exception 
	 */
	public String listaAsistencia() throws Exception {
		
		String infoAsistentes = "";
		
		if(listaAsistente_.size()==0) {
			throw new Exception("Error: No hay asistentes registrados.");
		}
		else{
			
			for(Asistente asis : listaAsistente_ ) {
				
				infoAsistentes = asis.toString();
				
			}
		}
		return infoAsistentes;
	}
}
