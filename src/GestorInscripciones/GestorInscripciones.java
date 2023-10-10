package GestorInscripciones;

import java.util.ArrayList;
import Inscripcion.InscripcionCompleta;
import Inscripcion.InscripcionParcial;
import java.time.Period;
import java.time.LocalDate;
/**
 * Clase que implementa el patrón Singleton para poder ser utilizada en la validación de Inscripciones.
 * @author Enrique de los Reyes Montilla
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
	/**
	 * Método que devuelve la lista de InscripcionesParciales del gestor.
	 * @return ArrayList<InscripcionParcial>.
	 */
	public ArrayList<InscripcionParcial> getListaInscripcionParcial_() {
		return listaInscripcionParcial_;
	}
	/**
	 * Método que establece la lista de inscripcionesParciales del gestor como aquella lista pasada como argumento.
	 * @param ArrayList<InscripcionParcial> listaInscripcionParcial
	 * @return void.
	 */
	public void setListaInscripcionParcial_(ArrayList<InscripcionParcial> listaInscripcionParcial) {
		this.listaInscripcionParcial_ = listaInscripcionParcial;
	}
	/**
	 * Método que devuelve la lista de campamentos del gestor.
	 * @return ArrayList<InscripcionCompleta>.
	 */
	public ArrayList<InscripcionCompleta> getListaInscripcionCompleta_() {
		return listaInscripcionCompleta_;
	}
	/**
	 * Método que establece la lista de inscripcionesCompletas del gestor como aquella lista pasada como argumento.
	 * @param ArrayList<InscripcionCompleta> listaInscripcionCompleta
	 * @return void.
	 */
	public void setListaInscripcionCompleta_(ArrayList<InscripcionCompleta> listaInscripcionCompleta) {
		this.listaInscripcionCompleta_ = listaInscripcionCompleta;
	}
	/**
	 * Método que añade una inscripción a la lista de inscripciones completas.
	 * @param InscripcionCompleta inscripcion.
	 * @param fechaInicioCamp fecha de inicio del campamento.
	 * @return void.
	 * @throws Exception 
	 */
	public void realizarRegistro(InscripcionCompleta inscripcion, LocalDate fechaInicioCamp) throws Exception {
		
		for(InscripcionCompleta ins : this.listaInscripcionCompleta_) {
			
			if(ins.getIdParticipante() == inscripcion.getIdParticipante() && ins.getIdCampamento() == inscripcion.getIdCampamento()) {
				throw new Exception("Error: Inscripción del participante" +  inscripcion.getIdParticipante()+" ya ha sido realizada en el campamento  " + inscripcion.getIdCampamento() + ".");
			}
			
		}
		
		for(InscripcionParcial ins : this.listaInscripcionParcial_) {
			
			if(ins.getIdParticipante() == inscripcion.getIdParticipante() && ins.getIdCampamento() == inscripcion.getIdCampamento()) {
				throw new Exception("Error: Inscripción del participante" +  inscripcion.getIdParticipante()+" ya ha sido realizada en el campamento  " + inscripcion.getIdCampamento() + ".");
			}
			
		}
		
		// Calcular la diferencia entre las dos fechas
		inscripcion.setFechaInscripcion(LocalDate.now());
		Period periodo = inscripcion.getFechaInscripcion().until(fechaInicioCamp);

		// Obtener el número de días de la diferencia
		int diferenciaDias = periodo.getDays();
		        
		if(diferenciaDias > 2 ) {
			inscripcion.crearRegistroTemprano(fechaInicioCamp);
		}
		else {
			inscripcion.crearRegistroTardio(fechaInicioCamp);
		}
				
		this.listaInscripcionCompleta_.add(inscripcion);
		
	}
	/**
	 * Método que añade una inscripción a la lista de inscripciones parciales.
	 * @param InscripcionParcial inscripcion.
	 * @param fechaInicioCamp fecha de inicio del campamento.
	 * @return void.
	 * @throws Exception 
	 */
	public void realizarRegistro(InscripcionParcial inscripcion, LocalDate fechaInicioCamp) throws Exception {
		
		for(InscripcionCompleta ins : this.listaInscripcionCompleta_) {
			
			if(ins.getIdParticipante() == inscripcion.getIdParticipante() && ins.getIdCampamento() == inscripcion.getIdCampamento()) {
				throw new Exception("Error: Inscripción del participante" +  inscripcion.getIdParticipante()+" ya ha sido realizada en el campamento  " + inscripcion.getIdCampamento() + ".");
			}
			
		}
		
		for(InscripcionParcial ins : this.listaInscripcionParcial_) {
			
			if(ins.getIdParticipante() == inscripcion.getIdParticipante() && ins.getIdCampamento() == inscripcion.getIdCampamento()) {
				throw new Exception("Error: Inscripción del participante" +  inscripcion.getIdParticipante()+" ya ha sido realizada en el campamento  " + inscripcion.getIdCampamento() + ".");
			}
			
		}
		
		// Calcular la diferencia entre las dos fechas
		inscripcion.setFechaInscripcion(LocalDate.now());
        Period periodo = inscripcion.getFechaInscripcion().until(fechaInicioCamp);

        // Obtener el número de días de la diferencia
        int diferenciaDias = periodo.getDays();
        
		if(diferenciaDias > 2 ) {
			inscripcion.crearRegistroTemprano(fechaInicioCamp);
		}
		else {
			inscripcion.crearRegistroTardio(fechaInicioCamp);
		}
		
		this.listaInscripcionParcial_.add(inscripcion);
		
	}
	
}
