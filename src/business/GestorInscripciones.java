package business;

import java.util.ArrayList;

import data.dao.CampamentoDAO;
import data.dao.InscripcionCompletaDAO;
import data.dao.InscripcionParcialDAO;

import java.time.Period;
import java.time.LocalDate;
/**
 * Clase que implementa el patrón Singleton para poder ser utilizada en la validación de Inscripciones.
 * @author Enrique de los Reyes Montilla
 * @author Manuel Garcia Obrero
 */
public class GestorInscripciones {
	/**
	 * Variable privada Singleton.
	 */
	private static GestorInscripciones instance_= null;
	/**
	 * Variable privada que representa una lista de inscripciones parciales..
	 */
	private ArrayList<InscripcionParcial> listaInscripcionParcial_;
	/**
	 * Variable privada representa una lista de inscripciones completas.
	 */
	private ArrayList<InscripcionCompleta> listaInscripcionCompleta_;
	/**
	 * Metodo que sirve de acceso a la instancia.
	 * @return Instancia de la clase GestorCampamentos.
	 */
	public static GestorInscripciones getInstance() {
		if(instance_ == null) {
			return new GestorInscripciones();
		}
		return instance_;
	}
	private GestorInscripciones() {
		this.listaInscripcionCompleta_ = null;
		this.listaInscripcionParcial_ = null;
	}
	/**
	 * Método que devuelve la lista de InscripcionesParciales del gestor.
	 * @return ArrayList<InscripcionParcial>.
	 */
	public ArrayList<InscripcionParcial> getListaInscripcionParcial() {
		if(listaInscripcionParcial_ == null) {
			InscripcionParcialDAO db = InscripcionParcialDAO.getInstance();
			listaInscripcionParcial_ = db.readAll();
		}
		return listaInscripcionParcial_;
	}
	/**
	 * Método que establece la lista de inscripcionesParciales del gestor como aquella lista pasada como argumento.
	 * @param ArrayList<InscripcionParcial> listaInscripcionParcial
	 * @return void.
	 */
	public void setListaInscripcionParcial(ArrayList<InscripcionParcial> listaInscripcionParcial) {
		this.listaInscripcionParcial_ = listaInscripcionParcial;
	}
	/**
	 * Método que devuelve la lista de campamentos del gestor.
	 * @return ArrayList<InscripcionCompleta>.
	 */
	public ArrayList<InscripcionCompleta> getListaInscripcionCompleta() {
		if(listaInscripcionCompleta_ == null) {
			InscripcionCompletaDAO db = InscripcionCompletaDAO.getInstance();
			listaInscripcionCompleta_ = db.readAll();
		}
		return listaInscripcionCompleta_;
	}
	/**
	 * Método que establece la lista de inscripcionesCompletas del gestor como aquella lista pasada como argumento.
	 * @param ArrayList<InscripcionCompleta> listaInscripcionCompleta
	 * @return void.
	 */
	public void setListaInscripcionCompleta(ArrayList<InscripcionCompleta> listaInscripcionCompleta) {
		this.listaInscripcionCompleta_ = listaInscripcionCompleta;
	}
	/**
	 * Método que añade una inscripción a la lista de inscripciones completas.
	 * @param InscripcionCompleta inscripcion.
	 * @param fechaInicioCamp fecha de inicio del campamento.
	 * @param listaAsistentes Lista de asistentes registrados.
	 * @return boolean Devuelve tre si se ha realizado correctamente la inserción
	 */
	public boolean realizarRegistro(InscripcionCompleta inscripcion, LocalDate fechaInicioCamp,  ArrayList<Asistente> listaAsistentes){		
		this.listaInscripcionCompleta_ = null;
		// Calcular la diferencia entre las dos fechas
		inscripcion.setFechaInscripcion(LocalDate.now());
		Period periodo = inscripcion.getFechaInscripcion().until(fechaInicioCamp);

		// Obtener el número de días de la diferencia
		int diferenciaDias = periodo.getDays();
		        
		InscripcionCompletaDAO db = InscripcionCompletaDAO.getInstance();
		if(diferenciaDias >= 15 )
			return db.createTemprano(inscripcion);
		else if(diferenciaDias >= 2)
			return db.createTardio(inscripcion);
		else 
			return false;
	}
	/**
	 * Método que añade una inscripción a la lista de inscripciones parciales.
	* @param InscripcionCompleta inscripcion.
	 * @param fechaInicioCamp fecha de inicio del campamento.
	 * @param listaAsistentes Lista de asistentes registrados.
	 * @return boolean.
	 * @throws Exception 
	 */
	public boolean realizarRegistro(InscripcionParcial inscripcion, LocalDate fechaInicioCamp, ArrayList<Asistente> listaAsistentes) throws Exception {
		listaInscripcionParcial_ = null;	
		// Calcular la diferencia entre las dos fechas
		inscripcion.setFechaInscripcion(LocalDate.now());
        Period periodo = inscripcion.getFechaInscripcion().until(fechaInicioCamp);

        // Obtener el número de días de la diferencia
        int diferenciaDias = periodo.getDays();
        
        InscripcionCompletaDAO db = InscripcionCompletaDAO.getInstance();
		if(diferenciaDias >= 15 )
			return db.createTemprano(inscripcion);
		else if(diferenciaDias >= 2)
			return db.createTardio(inscripcion);
		else
			return false;
	}
	/**
	 * Método que calcula el precio de la inscripción y lo devuelve como un int. Si el resultado es -1,  no existe el campamento al que está inscrito
	 * @param inscripcion InscripcionCompleta.
	 * @param ListaCampamentos
	 * @return precio. 
	 */
	
	private float calcularPrecio(InscripcionCompleta inscripcion, ArrayList<Campamento> ListaCampamentos) {
		int precio=300;//Precio base sin actividades
		for(int aux=0;aux<ListaCampamentos.size();aux++) {
			if(ListaCampamentos.get(aux).getId()== inscripcion.getIdCampamento()) {
				ArrayList<Actividad> Listaactividades =ListaCampamentos.get(aux).getListaActividad();
				if(Listaactividades.size()==0) {
					return precio;
				}
				else {
					return Listaactividades.size()*20 + precio;
				}
			}
		}	
		return -1;//Error campamento no encontrado
	}
	/**
	 * Método que calcula el precio de la inscripción y lo devuelve como un int. Si el resultado es -1,  no existe el campamento al que está inscrito
	 * @param inscripcion InscripcionParcial.
	 * @param ListaCampamentos
	 * @return precio. 
	 */
	
	private float calcularPrecio(InscripcionParcial inscripcion, ArrayList<Campamento> ListaCampamentos) {
		float precio=100;//Precio base sin actividades
		for(int aux=0;aux<ListaCampamentos.size();aux++) {
			if(ListaCampamentos.get(aux).getId() == inscripcion.getIdCampamento()) {
				ArrayList<Actividad> Listaactividades =ListaCampamentos.get(aux).getListaActividad();
				if(Listaactividades.size()==0) {
					return precio;
				}
				else {
					return Listaactividades.size()*20 + precio;
				}
			}
		}	
		return -1;//Error campamento no encontrado
	}
	/**
	 * Método que establece el precio a una incripción pasado su id. Si se incorpora correctamente devuelve 0 y si el id no existe, se devulve un -1
	 * @param idcampamento
	 * @param idpersona
	 * @param ListaCampamentos
	 * @return precio. 
	 */
	public int asignarPrecio(int idcampamento, int idpersona, ArrayList<Campamento> listaCampamentos) {
		//Miramos si esa id de inscripción pertenece a la listaInscripcionParcial_ o listaInscripcionCompleta_
		for(InscripcionParcial ins : this.listaInscripcionParcial_) {
			if(ins.getIdParticipante() == idpersona && ins.getIdCampamento() == idcampamento) {
				float precio = calcularPrecio(ins ,listaCampamentos);
				if(precio!=-1) {
					ins.setPrecio(precio);
					return 0;//Incorporado correctamente
				}
			}
		}
		for(InscripcionCompleta ins : this.listaInscripcionCompleta_) {
			if(ins.getIdParticipante() == idpersona && ins.getIdCampamento() == idcampamento) {
				float precio = calcularPrecio(ins ,listaCampamentos);
				if(precio!=-1) {
					ins.setPrecio(precio);
					return 0;//Incorporado correctamente
				}
			}
		}
		return -1;//Error id no encontrado
	}
	/**
	 * Método que devuelve una lista con todos los campamentos disponibles.
	 * @param listaCampamentos
	 * @return ArrayList<Campamento>. 
	 * @throws Exception 
	 */
	public String obtenerCampamentosDisponibles(){
		CampamentoDAO db = CampamentoDAO.getInstance();
		ArrayList<Campamento> disponibles = db.readAllAvailable();
		
		String listaAux = "";
		
		if(disponibles.size() > 0) 		
			for(Campamento cam : disponibles) 
				listaAux += cam.toString();
		
		return listaAux;
	}
	
	
}