package GestorAsistentes;

import java.util.ArrayList;
import java.util.Date;
import Asistente.Asistente;

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
		this.listaAsistente_.clear();
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
	public ArrayList<Asistente> getListaAsistente_() {
		return listaAsistente_;
	}
	/**
	 * Método que establece la lista de asistente del gestor.
	 * @param listaAsistentes
	 * @return void.
	 */
	public void setListaAsistentes_(ArrayList<Asistente> listaAsistentes){
		this.listaAsistente_=listaAsistentes;
	}
	/**
	 * Metodo que añade a la lista de asistente un nuevo asistente pasado como argumento, comprobando si no estaba registrado. Si estaba registrado devuelve false y si no lo estaba true
	 * @param Asistente asistente que se va a añadir a la lista de asistente.
	 * @return boolean(false error, true no error).
	 */
	public boolean DarAltaAsistente(Asistente asistente) {	
		for(int aux=0;aux<listaAsistente_.size();aux++) {
			if(listaAsistente_.get(aux).get_id()==asistente.get_id()) {
				return false;//Error, el asistente ya está inscrito
			}		
		}
		this.listaAsistente_.add(asistente);
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
	public void ModificarAsistente(int id_antiguo, int id_nuevo, String nombre, String apellidos, Date fechaNacimiento,boolean especial){
		for(int aux=0;aux<listaAsistente_.size();aux++) {
			if(listaAsistente_.get(aux).get_id()==id_antiguo) {
				listaAsistente_.get(aux).set_id(id_nuevo);
				listaAsistente_.get(aux).set_nombre(nombre);
				listaAsistente_.get(aux).set_apellidos(apellidos);
				listaAsistente_.get(aux).setFechaNacimiento(fechaNacimiento);
				listaAsistente_.get(aux).setEspecial(especial);
				aux=listaAsistente_.size();
			}
		}
	}
	/**
	 * Metodo que lista a los asistentes actualmente registrados.
	  * @return void.
	 */
	public void ListaAsistencia() {
		if(listaAsistente_.size()==0) {
			System.out.println("Todavia no existe ningun asistente");
		}
		else{
			for(int aux=0;aux<listaAsistente_.size();aux++) {
				String informacion = listaAsistente_.get(aux).toString();
				System.out.println("Asistente numero\n" + aux + ": \n" + informacion);
			}
		}
	}
}
