package controller.dto.registration;

import java.time.LocalDate;

/**
 * Class that represent a registration in a camp by an assistant
 * @author Francisco José Mellado Ortiz
 * @author Manuel Garcia Obrero
 */
public class RegistrationDTO {
	/**
	 * Assistant identifier
	 */
	private int idParticipante_;
	/**
	 * Camp identifier
	 */
	private int idCampamento_;
	/**
	 * Registration date
	 * */
	private LocalDate fechaInscripcion_;
	/**
	 * Price
	 */
	private float precio_;
	/**
	 * Registration type
	 */
	private Tipo tipo_;
	/**
	 * Registration temporality
	 */
	private Temporalidad temporalidad_;
	
	/**
	 * Empty constructor
	 */
	public RegistrationDTO(){}
	/**
	 * Constructor
	 * @param idParticipante Assistant identifier
	 * @param idCampamento Camp identifier
	 * @param fechaInscripcion Registration date
	 * @param precio Price
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
	 * Constructor
	 * @param idParticipante Assistant identifier
	 * @param idCampamento Camp identifier
	 * @param fechaInscripcion Registration date
	 * @param precio Price
	 * @param tipo Registration type
	 * @param temporalidad Registration temporality
	 */
	public RegistrationDTO(int idParticipante, int idCampamento, LocalDate fechaInscripcion, float precio, Tipo tipo, Temporalidad temporalidad) {
		this.idParticipante_ = idParticipante;
		this.idCampamento_ = idCampamento;
		this.fechaInscripcion_ = fechaInscripcion;
		this.precio_ = precio;
		this.tipo_= tipo;
		this.temporalidad_=temporalidad;
	}
   /** Constructor
	 * @param idParticipante Assistant identifier
	 * @param idCampamento Camp identifier
	 * @param fechaInscripcion Registration date
	 * @param precio Price
	 * @param tipo Registration type
	 * @param temporalidad Registration temporality
	 */
	public RegistrationDTO(int idParticipante, int idCampamento, LocalDate fechaInscripcion, float precio, String tipo, String temporalidad) {
		this.idParticipante_ = idParticipante;
		this.idCampamento_ = idCampamento;
		this.fechaInscripcion_ = fechaInscripcion;
		this.precio_ = precio;
		this.setTipo(tipo);
		this.setTemporalidad(temporalidad);
	}
	/**
	 * Assistant id get method
	 * @return int Assistant id
	 */
	public int getIdParticipante() {
		return this.idParticipante_;
	}
	/**
	 * Camp id get method
	 * @return int Camp id
	 */
	public int getIdCampamento() {
		return this.idCampamento_;
	}
	/**
	 * Registration date get method
	 * @return LocalDate Registration date
	 */
	public LocalDate getFechaInscripcion() {
		return this.fechaInscripcion_;
	}
	/**
	 * Price get method
	 * @return float Price
	 */
	public float getPrecio() {
		return this.precio_;
	}
	/**
	 * Type get method
	 * @return Tipo Registration type
	 */
	public Tipo getTipo() {
		return this.tipo_;
	}
	/**
	 * Type get method
	 * @return String Registration type
	 */
	public String getTipoName() {
		return this.tipo_.toString();
	}
	/**
	 * Temporality get method
	 * @return Temporalidad Registration temporality
	 */
	public Temporalidad getTemporalidad() {
		return this.temporalidad_;
	}
	/**
	 * Temporality get method
	 * @return String Registration temporality
	 */
	public String getTemporalidadName() {
		return this.temporalidad_.toString();
	}
	/**
	 * Assistant id set method
	 * @param id New assistant id
	 */
	public void setIdParticipante(int id) {
		this.idParticipante_ = id;
	}
	/**
	 * Camp id set method
	 * @param id New camp id
	 */
	public void setIdCampamento(int id) {
		this.idCampamento_ = id;
	}
	/**
	 * Registration date set method
	 * @param fecha New registration date
	 */
	public void setFechaInscripcion(LocalDate fecha) {
		this.fechaInscripcion_ = fecha;
	}
	/**
	 * Price set method
	 * @param precio New price
	 */
	public void setPrecio(float precio) {
		this.precio_ = precio;
	}
	/**
	 * Type set method
	 * @param tipo New Type
	 */
	public void setTipo(Tipo tipo) {
		this.tipo_ = tipo;
	}
	/**
	 * Type set method
	 * @param tipo New Type (Completa/Parcial)
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
	 * Temporality set method
	 * @param temporalidad New temporality
	 */
	public void setTemporalidad(Temporalidad temporalidad) {
		this.temporalidad_ = temporalidad;
	}
	/**
	 * Temporality set method
	 * @param temporalidad New temporality (Temprano/Tardio)
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
	 * Method that returns a string with registration data
	 * @return String The structure is:
	 * [Full /Partial ]Registration:\n\t
	 * Assistant: (assistant.id)\n\t
	 * Camp: (camp.id)\n\t
	 * Registration Date: (registration date)\n\t
	 * Price: (price)\n\t
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
