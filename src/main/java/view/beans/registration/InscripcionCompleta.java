package view.beans.registration;

import java.time.LocalDate;
import java.time.Period;

/**
 * Clase que representa una inscripción en todas las actividades del campamento (mañana y tarde). Se trata de una clase concreta del patrón de diseño Abstract Factory.
 * @author Francisco José Mellado Ortiz
 */
public class InscripcionCompleta extends Inscripcion{
	
	/**
	 * Construye un objeto de la clase InscripcionCompleta sin información.
	 */
	public InscripcionCompleta() {
		super();
	}
	/**
	 * Construye un objeto de la clase InscripcionCompleta con la información dada.
	 * @param idParticipante Identificador del participante.
	 * @param idCampamento Identificador del campamento.
	 * @param fechaInscripcion Fecha de la realización de la inscripción.
	 * @param precio Precio de la inscripción.
	 */
	public InscripcionCompleta(int idParticipante, int idCampamento, LocalDate fechaInscripcion, float precio) {
		super(idParticipante, idCampamento, fechaInscripcion, precio);
	}
	/**
	 * Construye un objeto de la clase InscripcionCompleta con la información dada.
	 * @param idParticipante Identificador del participante.
	 * @param idCampamento Identificador del campamento.
	 * @param fechaInscripcion Fecha de la realización de la inscripción.
	 * @param precio Precio de la inscripción.
	 * @param tipo Tipo de la inscripción.
	 * @param temporalidad Temporalidad de la inscripción.
	 */
	public InscripcionCompleta(int idParticipante, int idCampamento, LocalDate fechaInscripcion, float precio, String tipo, String temporalidad) {
		super(idParticipante, idCampamento, fechaInscripcion, precio, tipo, temporalidad);
	}
	
	
	/**
	 * Método para la creación de un registro en el campamento de forma temprana.
	 * @param fechaInicio Fecha de inicio del campamento.
	 * @throws Exception Error: Faltan menos de 15 dias para el inicio del campamento.
	 */
	@Override public RegistroTemprano crearRegistroTemprano(LocalDate fechaInicio) throws Exception {
		LocalDate fechaInscripcion = LocalDate.now();
		RegistroTemprano r = new RegistroTemprano();
		
		Period periodo = fechaInscripcion.until(fechaInicio);
		if(periodo.getDays() >= 15)
			r.registro(fechaInscripcion, fechaInicio);
		else
			throw new Exception("Error: Faltan menos de 15 dias para el inicio del campamento.");
		return r;
	}
	/**
	 * Método para la creación de un registro en el campamento de forma tardía.
	 * @param fechaInicio Fecha de inicio del campamento.
	 * @throws Exception Error: Faltan mas de 15 días o menos de 2 dias para el inicio..
	 */
	@Override public RegistroTardio crearRegistroTardio(LocalDate fechaInicio) throws Exception {
		LocalDate fechaInscripcion = LocalDate.now();
		RegistroTardio r = new RegistroTardio();
		
		Period periodo = fechaInscripcion.until(fechaInicio);
		if(periodo.getDays() >= 2 && periodo.getDays() < 15)
			r.registro(fechaInscripcion, fechaInicio);
		else
			throw new Exception("Error: Faltan mas de 15 días o menos de 2 dias para el inicio.");
		return r;
	}
	/**
	 * Método que devuelve la información de la inscripción en una cadena. Será definido por sus descendientes.
	 * @return La estrucutra de la cadena es:
	 * Inscripción a tiempo completo\n\t
	 * Participante: (idParticipante)\n\t
	 * Campamento: (idCampamento)\n\t
	 * Fecha de Inscripción: (fechaInscripcion)\n\t
	 * Precio: (precio)\n
	 */
	@Override public String toString() {
		return "Inscripción a tiempo completo\n\tParticipante: " + this.getIdParticipante() + "\n\tCampamento: " + this.getIdCampamento() + "\n\tFecha de Inscripción: " + this.getFechaInscripcion().toString() + "\n\tPrecio: " + this.getPrecio() + "\n";
	}
}
