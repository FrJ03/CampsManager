package Inscripcion;

import java.util.Date;
import RegistroTemprano.RegistroTemprano;
import RegistroTardio.RegistroTardio;

public abstract class Inscripcion {
	private int idParticipante;
	private int idCampamento;
	private Date fechaInscripcion;
	private float precio;
	
	public Inscripcion(){}
	public Inscripcion(int idParticipante, int idCampamento, Date fechaInscripcion, float precio) {
		this.idParticipante = idParticipante;
		this.idCampamento = idCampamento;
		this.fechaInscripcion = fechaInscripcion;
		this.precio = precio;
	}
	
	public int getIdParticipante() {
		return this.idParticipante;
	}
	public int getIdCampamento() {
		return this.idCampamento;
	}
	public Date getFechaInscripcion() {
		return this.fechaInscripcion;
	}
	public float getPrecio() {
		return this.precio;
	}
	public void setIdParticipante(int id) {
		this.idParticipante = id;
	}
	public void setIdCampamento(int id) {
		this.idCampamento = id;
	}
	public void setFechaInscripcion(Date fecha) {
		this.fechaInscripcion = fecha;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public abstract RegistroTemprano crearRegistroTemprano();
	public abstract RegistroTardio crearRegistroTardio();
	public abstract String toString();
}
