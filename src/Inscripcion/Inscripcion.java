package Inscripcion;

import java.time.LocalDate;
import Registro.RegistroTemprano;
import Registro.RegistroTardio;
/**
 * Clase abstracta que representa el concepto de inscripcion en el campamento y que se empleará como base en el patrón de diseño Abstract Factory entre inscripciones y registros.
 * @author Francisco José Mellado Ortiz
 */
public abstract class Inscripcion {
	/**
	 * Identificador del participante que se desea inscribir.
	 */
	private int idParticipante;
	/**
	 * Identificador del campamento al que el participante desea inscribirse.
	 */
	private int idCampamento;
	/**
	 * Fecha de inscripción en el campamento
	 * */
	private LocalDate fechaInscripcion;
	/**
	 * Precio de la inscripción.
	 */
	private float precio;
	
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
		this.idParticipante = idParticipante;
		this.idCampamento = idCampamento;
		this.fechaInscripcion = fechaInscripcion;
		this.precio = precio;
	}
	/**
	 * Método que devuelve el identificador del participante.
	 */
	public int getIdParticipante() {
		return this.idParticipante;
	}
	/**
	 * Método que devuelve el identificador del campamento.
	 */
	public int getIdCampamento() {
		return this.idCampamento;
	}
	/**
	 * Método que devuelve la fecha de inscripción.
	 */
	public LocalDate getFechaInscripcion() {
		return this.fechaInscripcion;
	}
	/**
	 * Método que devuelve el precio de la inscripción.
	 */
	public float getPrecio() {
		return this.precio;
	}
	/**
	 * Método para la modificación del identificador del participante.
	 * @param id Identificador del participante.
	 */
	public void setIdParticipante(int id) {
		this.idParticipante = id;
	}
	/**
	 * Método para la modificación del identificador del campamento.
	 * @param id Identificador del campamento.
	 */
	public void setIdCampamento(int id) {
		this.idCampamento = id;
	}
	/**
	 * Método para la modificación de la fecha de inscripción.
	 * @param fecha Fecha de inscripción en el campamento.
	 */
	public void setFechaInscripcion(LocalDate fecha) {
		this.fechaInscripcion = fecha;
	}
	/**
	 * Método para la modificación del precio.
	 * @param precio Precio de la actividad.
	 */
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	/**
	 * Método para la creación de un registro en el campamento de forma temprana. Será definido por sus descendientes.
	 */
	public abstract RegistroTemprano crearRegistroTemprano();
	/**
	 * Método para la creación de un registro en el campamento de forma tardía. Será definido por sus descendientes.
	 */
	public abstract RegistroTardio crearRegistroTardio();
	/**
	 * Método que devuelve la información de la inscripción en una cadena. Será definido por sus descendientes.
	 */
	public abstract String toString();
}
