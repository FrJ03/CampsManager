package controller.dto.activity;

import java.io.Serializable;

/**
 * Class that represents an activity.
 * @author Enrique de los Reyes Montilla
 */
public class ActivityDTO implements Serializable, Comparable<ActivityDTO>{
	/** Serial version attribute
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Activity identifier
	 */
	private int id_;
	/**
	 * Activity name
	 */
	private String name_;
	/**
	 * Activity level
	 */
	private Nivel nivel_;
	/**
	 * Activity turn
	 */
	private Turno turno_;
	/**
	 * Max number of assistants
	 */
	private int participantesMax_;
	/**
	 * Max number of monitors
	 */
	private int monitoresMax_;
	/**
	 * Empty constructor
	 */
	public ActivityDTO() {};
	/**
	 * Constructor
	 * @param id Identifier
	 * @param name Activity name
	 * @param nivel Activity level
	 * @param participantesMax Max number of assistants
	 * @param monitoresMax Max number of monitors
	 * @param turno Activity turn
	 */
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
	 * Id get method.
	 * @return int Identifier
	 */
	public int getId() {
		return id_;
	}
	/**
	 * Id set method
	 * @param id New identifier.
	 */
	public void setId(int id) {
		id_ = id;
	}
	/**
	  * Name get method
	  * @return String Activity name
	  */
	public String getName() {
		return name_;
	}
	/**
	  * Name set method
	  * @param name New name
	  */
	public void setName(String name) {
		this.name_ = name;
	}
	/**
	  * Max number of assistants get method
	  * @return int Max number of assistantss
	  */
	public int getParticipantesMax() {
		return participantesMax_;
	}
	/**
	  * Max number of assistant set method
	  * @param participantesMax New max number of assistants
	  */
	public void setParticipantesMax(int participantesMax) {
		this.participantesMax_ = participantesMax;
	}
	/**
	  * Max number of monitors get method
	  * @return int Max number of monitors
	  */
	public int getMonitoresMax() {
		return monitoresMax_;
	}
	/**
	  * Max number of monitors set method
	  * @param monitoresMax New max number of monitors
	  */
	public void setMonitoresMax(int monitoresMax) {
		this.monitoresMax_ = monitoresMax;
	}
	/**
	  * Level get method
	  * @return Nivel Activity level
	  */
	public Nivel getNivel() {
		return nivel_;
	}
	/**
	  * Level set method
	  * @param nivel New level
	  */
	public void setNivel(Nivel nivel) {
		this.nivel_=nivel;
	}
	/**
	  * Turn get method
	  * @return Turno Activity turn
	  * */
	public Turno getTurno() {
		return turno_;
	}
	/**
	  * Turn set method
	  * @param turno New turn
	  */
	public void setTurno(Turno turno) {
		this.turno_=turno;
	}
	/**
	  * Method that returns all activity data
	  * @return String String that contents the activity data
	  * */
	public String toString(){
		String aux = "Información de la Actividad\n\tId: " + this.id_ + "\n\tNombre:" + this.name_ + "\n\tNivel Academico:"+ this.nivel_ + 
				"\n\tTurno:" + this.turno_ + "\n\tNumero maximo de participantes:" + this.participantesMax_ + 
				"\n\tNumero máximo de monitores:" + this.monitoresMax_ + "\n";
		return aux;
	}
	/**
	 * Method that compare an activity with this activity
	 * @param a Activity to be compared
	 * @return int 0 if the activities has the same id, -1 if the id number of a is greater than this number or 1 otherwise
	 */
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
