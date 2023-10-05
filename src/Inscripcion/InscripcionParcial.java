package Inscripcion;

import java.util.Date;

import Registro.RegistroTemprano;
import Registro.RegistroTardio;

public class InscripcionParcial {
	public InscripcionCompleta() {
		super();
	}
	public InscripcionCompleta(int idParticipante, int idCampamento, Date fechaInscripcion, float precio) {
		super(idParticipante, idCampamento, fechaInscripcion, precio);
	}
	
	
	@Override public RegistroTemprano crearRegistroTemprano() {
		RegistroTemprano r = new RegistroTemprano();
		r.registro();
		return r;
	}
	@Override public RegistroTardio crearRegistroTardio() {
		RegistroTardio r = new RegistroTardio();
		r.registro();
		return r;
	}
	@Override public String toString() {
		return "Inscripción a tiempo parcial\n\tParticipante: " + this.getIdParticipante() + "\n\tCampamento: " + this.getIdCampamento() + "\n\tFecha de Inscripción: " + this.getFechaInscripcion().toString() + "\n\tPrecio: " + this.getPrecio();
	}
}
