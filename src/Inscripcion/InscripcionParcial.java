package Inscripcion;

import java.time.LocalDate;

import Registro.RegistroTemprano;
import Registro.RegistroTardio;

/**
 * Clase que representa una inscripción en las actividades matinales del campamento. Se trata de una clase concreta del patrón de diseño Abstract Factory.
 * @author Francisco José Mellado Ortiz
 */
public class InscripcionParcial{
	/**
	 * Construye un objeto de la clase InscripcParcial sin información.
	 */
	public InscripcionParcial() {
		super();
	}
	/**
	 * Construye un objeto de la clase InscripcionParcial con la información dada.
	 * @param idParticipante Identificador del participante.
	 * @param idCampamento Identificador del campamento.
	 * @param fechaInscripcion Fecha de la realización de la inscripción.
	 * @param precio Precio de la inscripción.
	 */
	public InscripcionParcial(int idParticipante, int idCampamento, LocalDate fechaInscripcion, float precio) {
		super(idParticipante, idCampamento, fechaInscripcion, precio);
	}
	
	/**
	 * Método para la creación de un registro en el campamento de forma temprana.
	 */
	@Override public RegistroTemprano crearRegistroTemprano() {
		RegistroTemprano r = new RegistroTemprano();
		r.registro();
		return r;
	}
	/**
	 * Método para la creación de un registro en el campamento de forma tardía.
	 */
	@Override public RegistroTardio crearRegistroTardio() {
		RegistroTardio r = new RegistroTardio();
		r.registro();
		return r;
	}
	/**
	 * Método que devuelve la información de la inscripción en una cadena. Será definido por sus descendientes.
	 * @return La estrucutra de la cadena es:
	 * Inscripción a tiempo parcial\n\t
	 * Participante: (idParticipante)\n\t
	 * Campamento: (idCampamento)\n\t
	 * Fecha de Inscripción: (fechaInscripcion)\n\t
	 * Precio: (precio)\n
	 */
	@Override public String toString() {
		return "Inscripción a tiempo parcial\n\tParticipante: " + this.getIdParticipante() + "\n\tCampamento: " + this.getIdCampamento() + "\n\tFecha de Inscripción: " + this.getFechaInscripcion().toString() + "\n\tPrecio: " + this.getPrecio();
	}
}
