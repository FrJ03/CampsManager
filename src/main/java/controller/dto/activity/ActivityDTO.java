package controller.dto.activity;

import java.io.Serializable;

/**
 * Clase que representa una actividad que se encuentra en un campamento.
 * @author Enrique de los Reyes Montilla
 */
public class ActivityDTO implements Serializable, Comparable<ActivityDTO>{
	/** Metodo para indicar la versión de la clase a serializar
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Identificador de la actividad
	 */
	private int id_;
	/**
	  * Representa el nombre de la actividad
	  * */
	private String name_;
	/**
	  * Representa el nivel educativo de la actividad
	  * */
	private Nivel nivel_;
	/**
	  * Representa el turno en el cual se realiza la actividad. Puede ser tanto mañanas como tardes.
	  * */
	private Turno turno_;
	/**
	  * Representa el numero máximo de participantes
	  * */
	private int participantesMax_;
	/**
	  * Representa el numero máximo de monitores
	  * */
	private int monitoresMax_;
	/**
	  * Construye un objeto vacio.
	  * @return void
	  * */
	public ActivityDTO() {};
	/**
	  * COnstruye un objeto con todos sus datos, a excepción de la lista de monitores que se inicializa vacía.
	  * @return void
	  * */
	public ActivityDTO(int id, String name, Nivel nivel, int participantesMax, 
			int monitoresMax, Turno turno) {
		this.id_ = id;
		this.name_=name;
		this.nivel_=nivel;
		this.participantesMax_=participantesMax;
		this.monitoresMax_=monitoresMax;
		this.turno_=turno;
	};
	/**
	 * Observador del identificador de la actividad.
	 * @return
	 */
	public int getId() {
		return id_;
	}
	/**
	 * Modificador del identificador de la actividad.
	 * @param id Nuevo identificador.
	 */
	public void setId(int id) {
		id_ = id;
	}
	/**
	  * Observador de la varible nombre
	  * @return string
	  * */
	public String getName() {
		return name_;
	}
	/**
	  * Modificador de la variable nombre
	  * @param name Nombre de la actividad
	  * @return void
	  * */
	public void setName(String name) {
		this.name_ = name;
	}
	/**
	  * Observador de la variable participantesMax
	  * @return int
	  * */
	public int getParticipantesMax() {
		return participantesMax_;
	}
	/**
	  * Modificador de la variable participantesMax.
	  * @param participantesMax Numero de participantes maximmos en la actividad
	  * @return void
	  * */
	public void setParticipantesMax(int participantesMax) {
		this.participantesMax_ = participantesMax;
	}
	/**
	  * Observador de la variable monitoresMax.
	  * @return int
	  * */
	public int getMonitoresMax() {
		return monitoresMax_;
	}
	/**
	  * Modificador de la variable monitoresMax.
	  * @param monitoresMax Número de monitores maximos en la actividad
	  * */
	public void setMonitoresMax(int monitoresMax) {
		this.monitoresMax_ = monitoresMax;
	}
	/**
	  * Observador de la variable nivel.
	  * @return nivel
	  * */
	public Nivel getNivel() {
		return nivel_;
	}
	/**
	  * Modificador de la variable nivel.
	  * @param nivel Enumeración que india el nivel de la actividad
	  * @return void
	  * */
	public void setNivel(Nivel nivel) {
		this.nivel_=nivel;
	}
	/**
	  * Observador de la variable turno.
	  * @return turno
	  * */
	public Turno getTurno() {
		return turno_;
	}
	/**
	  * Modificador de la variable turno.
	  * @param turno Enumeración que india el turno de la actividad
	  * @return void
	  * */
	public void setTurno(Turno turno) {
		this.turno_=turno;
	}
	/**
	  * Método que devuelve un string con la información de la actividad.
	  * @return String
	  * */
	public String toString(){
		String aux = "Información de la Actividad\n\tId: " + this.id_ + "\n\tNombre:" + this.name_ + "\n\tNivel Academico:"+ this.nivel_ + 
				"\n\tTurno:" + this.turno_ + "\n\tNumero maximo de participantes:" + this.participantesMax_ + 
				"\n\tNumero máximo de monitores:" + this.monitoresMax_ + "\n";
		return aux;
	}
	@Override
    public int compareTo(ActivityDTO a) {
		if(a.getId() > this.getId())
			return -1;
		else if(a.getId() > this.getId()) 
			return 1;
		else 
			return 0;
   	 
    }
}
