package business.inscripciones;

import java.time.LocalDate;

/**
 * Clase abstracta que representa el concepto de inscripcion en el campamento y que se empleará como base en el patrón de diseño Abstract Factory entre inscripciones y registros.
 * @author Francisco José Mellado Ortiz
 * @author Manuel Garcia Obrero
 */
public abstract class Inscripcion {
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
	private String tipo_;
	/**
	 * Identificador de que temporalidad de Inscripcion.
	 */
	private String temporalidad_;
	
	/**
	 * Construye un objeto de la clase inscripción sin información.
	 */
	public Inscripcion(){}
	/**
	 * Construye un objeto de la clase inscripción con los datos dados.
	 * @param idParticipante Identificador del participante.
	 * @param idCampamento Identificador del campamento.
	 * @param fechaInscripcion Fecha de la realización de la inscripción.
	 * @param precio Precio de la inscripción.
	 */
	public Inscripcion(int idParticipante, int idCampamento, LocalDate fechaInscripcion, float precio) {
		this.idParticipante_ = idParticipante;
		this.idCampamento_ = idCampamento;
		this.fechaInscripcion_ = fechaInscripcion;
		this.precio_ = precio;
		this.tipo_= "";
		this.temporalidad_="";
	}
	/**
	 * Construye un objeto de la clase inscripción con los datos dados pero con tipo.
	 * @param idParticipante Identificador del participante.
	 * @param idCampamento Identificador del campamento.
	 * @param fechaInscripcion Fecha de la realización de la inscripción.
	 * @param precio Precio de la inscripción.
	 */
	public Inscripcion(int idParticipante, int idCampamento, LocalDate fechaInscripcion, float precio, String tipo, String temporalidad) {
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
	public String getTipo() {
		return this.tipo_;
	}
	/**
	 * Método que devuelve la temporalidad de la inscripción.
	 */
	public String getTemporalidad() {
		return this.temporalidad_;
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
	public void setTipo(String tipo) {
		this.tipo_ = tipo;
	}
	/**
	 * Método para la modificación de temporalidad.
	 * @param temporalidad Temporalidad de la actividad.
	 */
	public void setTemporalidad(String temporalidad) {
		this.temporalidad_ = temporalidad;
	}
	/**
	 * Método para la creación de un registro en el campamento de forma temprana. Será definido por sus descendientes.
	 * @param fechaInicio Fecha de inicio del campamento.
	 * @throws Exception Error: " + fechaInicio +" es mayor que " + fechaInscripcion + "."
	 */
	public abstract RegistroTemprano crearRegistroTemprano(LocalDate fechaInicio) throws Exception;
	/**
	 * Método para la creación de un registro en el campamento de forma tardía. Será definido por sus descendientes.
	 * @param fechaInicio Fecha de inicio del campamento.
	 * @throws Exception Error: " + fechaInicio +" es menor que " + fechaInscripcion + ".".
	 */
	public abstract RegistroTardio crearRegistroTardio(LocalDate fechaInicio)throws Exception;
	/**
	 * Método que devuelve la información de la inscripción en una cadena. Será definido por sus descendientes.
	 */
	public abstract String toString();
}
