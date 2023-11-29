package controller.dto.registration;

import java.time.LocalDate;

/**
 * Clase abstracta que representa el concepto de inscripcion en el campamento y que se empleará como base en el patrón de diseño Abstract Factory entre inscripciones y registros.
 * @author Francisco José Mellado Ortiz
 * @author Manuel Garcia Obrero
 */
public class RegistrationDTO {
	/**
	 * Identificador del participante que se desea inscribir.
	 */
	private int idParticipante_;
	/**
	 * Identificador del campamento al que el participante desea inscribirse.
	 */
	private int idCampamento_;
	/**
	 * Fecha de inscripción en el campamento
	 * */
	private LocalDate fechaInscripcion_;
	/**
	 * Precio de la inscripción.
	 */
	private float precio_;
	/**
	 * Identificador de que tipo de Inscripcion(Completa o Parcial).
	 */
	private Tipo tipo_;
	/**
	 * Identificador de que temporalidad de Inscripcion.
	 */
	private Temporalidad temporalidad_;
	
	/**
	 * Construye un objeto de la clase inscripción sin información.
	 */
	public RegistrationDTO(){}
	/**
	 * Construye un objeto de la clase inscripción con los datos dados.
	 * @param idParticipante Identificador del participante.
	 * @param idCampamento Identificador del campamento.
	 * @param fechaInscripcion Fecha de la realización de la inscripción.
	 * @param precio Precio de la inscripción.
	 */
	public RegistrationDTO(int idParticipante, int idCampamento, LocalDate fechaInscripcion, float precio) {
		this.idParticipante_ = idParticipante;
		this.idCampamento_ = idCampamento;
		this.fechaInscripcion_ = fechaInscripcion;
		this.precio_ = precio;
		this.tipo_= Tipo.None;
		this.temporalidad_= Temporalidad.None;
	}
	/**
	 * Construye un objeto de la clase inscripción con los datos dados pero con tipo.
	 * @param idParticipante Identificador del participante.
	 * @param idCampamento Identificador del campamento.
	 * @param fechaInscripcion Fecha de la realización de la inscripción.
	 * @param precio Precio de la inscripción.
	 */
	public RegistrationDTO(int idParticipante, int idCampamento, LocalDate fechaInscripcion, float precio, Tipo tipo, Temporalidad temporalidad) {
		this.idParticipante_ = idParticipante;
		this.idCampamento_ = idCampamento;
		this.fechaInscripcion_ = fechaInscripcion;
		this.precio_ = precio;
		this.tipo_= tipo;
		this.temporalidad_=temporalidad;
	}
	/**
	 * Método que devuelve el identificador del participante.
	 */
	public int getIdParticipante() {
		return this.idParticipante_;
	}
	/**
	 * Método que devuelve el identificador del campamento.
	 */
	public int getIdCampamento() {
		return this.idCampamento_;
	}
	/**
	 * Método que devuelve la fecha de inscripción.
	 */
	public LocalDate getFechaInscripcion() {
		return this.fechaInscripcion_;
	}
	/**
	 * Método que devuelve el precio de la inscripción.
	 */
	public float getPrecio() {
		return this.precio_;
	}
	/**
	 * Método que devuelve el tipo de la inscripción.
	 */
	public Tipo getTipo() {
		return this.tipo_;
	}
	/**
	 * Method that returns the registration type
	 * @return
	 */
	public String getTipoName() {
		return this.tipo_.toString();
	}
	/**
	 * Método que devuelve la temporalidad de la inscripción.
	 */
	public Temporalidad getTemporalidad() {
		return this.temporalidad_;
	}
	/**
	 * Method that returns the registration timing
	 * @return
	 */
	public String getTemporalidadName() {
		return this.temporalidad_.toString();
	}
	/**
	 * Método para la modificación del identificador del participante.
	 * @param id Identificador del participante.
	 */
	public void setIdParticipante(int id) {
		this.idParticipante_ = id;
	}
	/**
	 * Método para la modificación del identificador del campamento.
	 * @param id Identificador del campamento.
	 */
	public void setIdCampamento(int id) {
		this.idCampamento_ = id;
	}
	/**
	 * Método para la modificación de la fecha de inscripción.
	 * @param fecha Fecha de inscripción en el campamento.
	 */
	public void setFechaInscripcion(LocalDate fecha) {
		this.fechaInscripcion_ = fecha;
	}
	/**
	 * Método para la modificación del precio.
	 * @param precio Precio de la actividad.
	 */
	public void setPrecio(float precio) {
		this.precio_ = precio;
	}
	/**
	 * Método para la modificación de tipo.
	 * @param tipo Tipo de la actividad.
	 */
	public void setTipo(Tipo tipo) {
		this.tipo_ = tipo;
	}
	/**
	 * Merhod that modify the registration type
	 * @param tipo New registration type
	 * @return
	 */
	public boolean setTipo(String tipo) {
		if(tipo.equalsIgnoreCase("completa")) {
			tipo_ = Tipo.Completa;
			return true;
		}
		else if(tipo.contentEquals("parcial")) {
			tipo_ = Tipo.Parcial;
			return true;
		}
		else
			return false;
	}
	/**
	 * Método para la modificación de temporalidad.
	 * @param temporalidad Temporalidad de la actividad.
	 */
	public void setTemporalidad(Temporalidad temporalidad) {
		this.temporalidad_ = temporalidad;
	}
	/**
	 * Merhod that modify the registration type
	 * @param tipo New registration type
	 * @return
	 */
	public boolean setTemporalidad(String temporalidad) {
		if(temporalidad.equalsIgnoreCase("temprano")) {
			temporalidad_ = Temporalidad.Temprano;
			return true;
		}
		else if(temporalidad.contentEquals("tardio")) {
			temporalidad_ = Temporalidad.Tardio;
			return true;
		}
		else
			return false;
	}
	/**
	 * Método para la creación de un registro en el campamento de forma temprana. Será definido por sus descendientes.
	 * @param fechaInicio Fecha de inicio del campamento.
	 * @throws Exception Error: " + fechaInicio +" es mayor que " + fechaInscripcion + "."
	 */
	/*public RegistroTemprano crearRegistroTemprano(LocalDate fechaInicio) {
		
	}*/
	/**
	 * Método para la creación de un registro en el campamento de forma tardía. Será definido por sus descendientes.
	 * @param fechaInicio Fecha de inicio del campamento.
	 * @throws Exception Error: " + fechaInicio +" es menor que " + fechaInscripcion + ".".
	 */
	/*public boolean crearRegistroTardio(LocalDate fechaInicio) {
		LocalDate fechaInscripcion = LocalDate.now();
		//RegistroTemprano r = new RegistroTemprano();
		
		Period periodo = fechaInscripcion.until(fechaInicio);
		if(periodo.getDays() >= 15)
			//r.registro(fechaInscripcion, fechaInicio);
			return true;
		else
			//throw new Exception("Error: Faltan menos de 15 dias para el inicio del campamento.");
			return false;
	}*/
	/**
	 * Método que devuelve la información de la inscripción en una cadena. Será definido por sus descendientes.
	 */
	public String toString() {
		String aux = "";
		if(tipo_ == Tipo.Completa)
			aux = "Full Registration:\n\tAssistant: ";
		else if(tipo_ == Tipo.Parcial)
			aux = "Partial Registration:\n\tAssistant: ";
		else
			aux = "Registration:\n\tAssistant: ";
		
		aux += this.getIdParticipante() + "\n\tCamp: " + this.getIdCampamento() + "\n\tRegistration Date: " + this.getFechaInscripcion().toString() + "\n\tPrice: " + this.getPrecio() + "\n";
		
		return aux;
	}
}
